package www.thirdauth.com.thirdparty.workweixin;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.workweixin.aes.xml.AesException;
import www.thirdauth.com.thirdparty.workweixin.aes.xml.WXBizMsgCrypt;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.GenericDeclaration;
import java.util.concurrent.*;

/**
 * 用来接受企业微信的推送消息
 */
@Slf4j
@Data
public class WorkWeixinPushContainer {
    CacheAble<String,String> cacheAble;


    WXBizMsgCrypt suitWxBizMsgCrypt;

    WXBizMsgCrypt corpWxBizMsgCrypt;


    ExecutorService executorService  ;

    PermanentCodeAccessor permanentCodeAccessor;

    String suiteId;

    String corpId;

    String encodingAESKey;

    String token;




    public WorkWeixinPushContainer(String suiteId,String corpId, String encodingAESKey, String token) throws AesException {
        suitWxBizMsgCrypt = new WXBizMsgCrypt(token,encodingAESKey,suiteId);
        corpWxBizMsgCrypt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
        WXBizMsgCrypt corpWxBizMsgCrypt;
        // 获取可用处理器数量
        int numOfProcessors = Runtime.getRuntime().availableProcessors();

        // 创建一个固定大小的线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                numOfProcessors,
                numOfProcessors,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100) // 设置队列容量
        );
        this.executorService = executorService;
    }

    public  String  receiveFromWorkWeixinByGet(String msgSignature, String timestamp,String nonce, String echostr){
            try {
                return corpWxBizMsgCrypt.VerifyURL(msgSignature, timestamp, nonce,echostr);
            }catch (Exception e){
                log.error("VerifyURL fail", e);
                return "fail";
            }
    }

    public String receiveFromWorkWeixinByPost(String msgSignature, String timestamp,String nonce, String reqData){
        try {
            String sMsg = suitWxBizMsgCrypt.DecryptMsg(msgSignature, timestamp, nonce, reqData);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(sMsg);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);
            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Content");
            String  content = nodelist1.item(0).getTextContent();
            Document  pushContent = db.parse(content);
            NodeList infoTypeNode = pushContent.getElementsByTagName("InfoType");
            String textContent = infoTypeNode.item(0).getTextContent();
            if(textContent.contains("suite_ticket")){
                String suiteTicket = getElementValue("SuiteTicket", root);
                cacheAble.put("suiteticket",suiteTicket,30*60*1000);
            }else if (textContent.contains("create_auth")){
                String authCode = getElementValue("AuthCode", root);
                executorService.submit(() -> permanentCodeAccessor.permanentCode(authCode));
            }
            return "success";
        } catch (AesException e) {
            log.error("AesException",e);
        } catch (ParserConfigurationException e) {
            log.error("ParserConfigurationException",e);
        } catch (IOException e) {
            log.error("IOException",e);
        } catch (SAXException e) {
            log.error("SAXException",e);
        }
        return "fail";
    }



    public String receiveFromWorkWeixinByPostForCommand(String msgSignature, String timestamp,String nonce, String reqData){
        String result = "fail";
        try{
            String sMsg = suitWxBizMsgCrypt.DecryptMsg(msgSignature, timestamp, nonce, reqData);
            System.out.println("after encrypt sEncrytMsg: " + sMsg);
            // 加密成功
            // TODO: 解析出明文xml标签的内容进行处理
            // For example:
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(sMsg);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList infoTypeNode = root.getElementsByTagName("InfoType");
            String infoType = infoTypeNode.item(0).getTextContent();
            log.info(infoType);
            switch (infoType){
                case "suite_ticket" :
                    NodeList nodelist = root.getElementsByTagName("SuiteTicket");
                    String suiteticketResult = nodelist.item(0).getTextContent();;
                    cacheAble.put("suiteticket",suiteticketResult,30*60*1000);
                    break;
                case "create_auth":
                    //获取auth_code
                    NodeList authcodeNode = root.getElementsByTagName("AuthCode");
                    String authcode = authcodeNode.item(0).getTextContent();
                    log.info("auth code:"+authcode);
                    executorService.submit(() -> permanentCodeAccessor.permanentCode(authcode));
                    ;
                    break;
                case "change_auth":
                    ;
                    break;
                case "cancel_auth":
                    //获取corp_id
                    NodeList authCorpNode = root.getElementsByTagName("AuthCorpId");
                    String corpId = authCorpNode.item(0).getTextContent();
//                    deleteCompany(corpId);
                    ;
                    break;
                case "register_corp":
                    NodeList stateNode = root.getElementsByTagName("state");
                    String state = stateNode.item(0).getTextContent();
                    log.info("state :"+state);
                    break;
                case "batch_job_result":
                    //通讯录id转译异步任务回调  https://open.work.weixin.qq.com/api/doc/90001/90143/91875
                    break;
                default:
                    log.info(infoType);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            // 加密失败
            return result;
        }
        result = "success";
        return result;
    }



    private static String getElementValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            NodeList subList = nodeList.item(0).getChildNodes();
            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }

}
