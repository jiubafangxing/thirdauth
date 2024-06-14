package www.thirdauth.com.thirdparty.workweixin;

<<<<<<< Updated upstream
=======
import lombok.Data;
>>>>>>> Stashed changes
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.workweixin.aes.xml.AesException;
import www.thirdauth.com.thirdparty.workweixin.aes.xml.WXBizMsgCrypt;
<<<<<<< Updated upstream
import www.thirdauth.com.thirdparty.workweixin.init.WorkWeixinProperties;
=======
>>>>>>> Stashed changes

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.*;

/**
 * 用来接受企业微信的推送消息
 */
@Slf4j
<<<<<<< Updated upstream
public class WorkWeixinPushContainer {
    CacheAble<String,String> cacheAble;

    WorkWeixinProperties workWeixinProperties;
=======
@Data
public class WorkWeixinPushContainer {
    CacheAble<String,String> cacheAble;

>>>>>>> Stashed changes

    WXBizMsgCrypt wxBizMsgCrypt;


    ExecutorService executorService  ;

    PermanentCodeAccessor permanentCodeAccessor;

<<<<<<< Updated upstream

    public WorkWeixinPushContainer(WorkWeixinProperties workWeixinProperties) throws AesException {
        this.workWeixinProperties = workWeixinProperties;
        wxBizMsgCrypt = new WXBizMsgCrypt(workWeixinProperties.getToken(), workWeixinProperties.getEncodingAESKey(), workWeixinProperties.getSuiteId());
=======
    String suiteId;

    String encodingAESKey;

    String token;



    public WorkWeixinPushContainer() throws AesException {
        wxBizMsgCrypt = new WXBizMsgCrypt(token,encodingAESKey,suiteId);
>>>>>>> Stashed changes
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
    }

    public String receiveFromWorkWeixinByGet(String msgSignature, String timestamp,String nonce, String echostr){
            try {
                wxBizMsgCrypt.VerifyURL(msgSignature, timestamp, nonce,echostr);
            }catch (Exception e){
                log.error("VerifyURL fail");
                return "fail";
            }
            return "success";
    }

    public String receiveFromWorkWeixinByPost(String msgSignature, String timestamp,String nonce, String reqData){
        try {
            String sMsg = wxBizMsgCrypt.DecryptMsg(msgSignature, timestamp, nonce, reqData);
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
            return sMsg;
        } catch (AesException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
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
