package www.thirdauth.com.thirdparty.workweixin;

<<<<<<< Updated upstream
import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.CorpRepository;
import www.thirdauth.com.thirdparty.mark.CorpInfo;
import www.thirdauth.com.thirdparty.workweixin.init.WorkWeixinProperties;
=======
import lombok.Data;
import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.CorpRepository;
import www.thirdauth.com.thirdparty.mark.CorpInfo;
>>>>>>> Stashed changes
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinCorpTokenReq;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinSuiteTokenReq;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWeixinAccessTokenResponse;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWiexinSuiteTokenResp;
import www.thirdauth.com.thirdparty.workweixin.resp.permanentcode.WorkWeixinCorpInfo;

<<<<<<< Updated upstream
public class WorkWeixinActiveParamAccessor  {
    WorkWeixinFeignClient workWeixinFeignClient;

    WorkWeixinProperties workWeixinProperties;

    CacheAble<String,String> cacheAble;

    CorpRepository corpRepository;
=======
@Data
public class WorkWeixinActiveParamAccessor  {
    protected WorkWeixinFeignClient workWeixinFeignClient;

//    protected WorkWeixinProperties workWeixinProperties;

    protected CacheAble<String,String> cacheAble;

    protected CorpRepository corpRepository;

    //以ww或wx开头应用id（对应于旧的以tj开头的套件id）
    protected String suiteId;
    //应用secret
    protected String suiteSecret;




    public WorkWeixinActiveParamAccessor(
            WorkWeixinFeignClient workWeixinFeignClient,
            CacheAble<String, String> cacheAble,
            CorpRepository corpRepository,
            String suiteId,
            String suiteSecret) {
        this.workWeixinFeignClient = workWeixinFeignClient;
        this.cacheAble = cacheAble;
        this.corpRepository = corpRepository;
        this.suiteId =suiteId;
        this.suiteSecret = suiteSecret;
    }
>>>>>>> Stashed changes

    public String getSuiteToken() {
        String suiteToken = cacheAble.get("workWexin:suiteToken");
        if(null == suiteToken || suiteToken.equals("")) {
            String suiteTicket = getSuiteticket();
            WorkWeixinSuiteTokenReq workWeixinSuiteTokenReq = new WorkWeixinSuiteTokenReq();
<<<<<<< Updated upstream
            workWeixinSuiteTokenReq.setSuiteSecret(workWeixinProperties.getSuiteSecret());
            workWeixinSuiteTokenReq.setSuiteId(workWeixinProperties.getSuiteId());
=======
            workWeixinSuiteTokenReq.setSuiteSecret(suiteId);
            workWeixinSuiteTokenReq.setSuiteId(suiteSecret);
>>>>>>> Stashed changes
            workWeixinSuiteTokenReq.setSuiteTicket(suiteTicket);
            WorkWiexinSuiteTokenResp suiteTokenResp = workWeixinFeignClient.getSuiteToken(workWeixinSuiteTokenReq);
            if (null != suiteTokenResp && null != suiteTokenResp.getSuiteAccessToken()) {
                cacheAble.put("workWexin:suiteToken", suiteTokenResp.getSuiteAccessToken(), 2 * 60 * 60);
                return suiteTokenResp.getSuiteAccessToken();
            } else {
                throw new RuntimeException("no SuiteAccessToken");
            }
        }else{
            return suiteToken;
        }
    }

    public String getAccessToken(String corpId) {
        String suiteToken = cacheAble.get("workWexin:accessToken:corpId:"+corpId);
        if(null == suiteToken|| suiteToken.equals("") ){
<<<<<<< Updated upstream
            CorpInfo corpInfo = corpRepository.queryCorpInfo(corpId);
=======
            CorpInfo corpInfo = corpRepository.queryCorpInfo(corpId,"workweixin");
>>>>>>> Stashed changes
            if(null == corpInfo){
                return null;
            }
            WorkWeixinCorpInfo workWeixinCorpInfo = (WorkWeixinCorpInfo) corpInfo;
            String permanentCode = workWeixinCorpInfo.getPermanentCode();
            if(null == permanentCode || permanentCode.equals("")){
                return null;
            }else{
                WorkWeixinCorpTokenReq corpTokenReq = new WorkWeixinCorpTokenReq();
                corpTokenReq.setPermanentCode(permanentCode);
                corpTokenReq.setAuthCorpid(corpId);
                WorkWeixinAccessTokenResponse corpToken = workWeixinFeignClient.getCorpToken(suiteToken, corpTokenReq);
                if(null != corpToken.getErrmsg()){
                    return null;
                }else{
                    cacheAble.put("workWexin:accessToken:corpId:"+corpId, corpToken.getAccessToken(), 7200*1000);
                    return corpToken.getAccessToken();
                }
            }
        }
        return null;
    }

    public String getSuiteticket(){
        return cacheAble.get("suiteticket");
    }
}
