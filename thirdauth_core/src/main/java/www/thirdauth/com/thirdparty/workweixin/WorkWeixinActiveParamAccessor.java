package www.thirdauth.com.thirdparty.workweixin;


import lombok.Data;
import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.CorpRepository;
import www.thirdauth.com.thirdparty.mark.CorpInfo;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinCorpTokenReq;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinSuiteTokenReq;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWeixinAccessTokenResponse;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWiexinSuiteTokenResp;
import www.thirdauth.com.thirdparty.workweixin.resp.permanentcode.WorkWeixinCorpInfo;


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

    public String getSuiteToken() {
        String suiteToken = cacheAble.get("workWexin:suiteToken");
        if(null == suiteToken || suiteToken.equals("")) {
            String suiteTicket = getSuiteticket();
            WorkWeixinSuiteTokenReq workWeixinSuiteTokenReq = new WorkWeixinSuiteTokenReq();
            workWeixinSuiteTokenReq.setSuiteSecret(suiteSecret);
            workWeixinSuiteTokenReq.setSuiteId(suiteId);
            workWeixinSuiteTokenReq.setSuiteTicket(suiteTicket);
            WorkWiexinSuiteTokenResp suiteTokenResp = workWeixinFeignClient.getSuiteToken(workWeixinSuiteTokenReq);
            if (null != suiteTokenResp && null != suiteTokenResp.getSuiteAccessToken()) {
                cacheAble.put("workWexin:suiteToken", suiteTokenResp.getSuiteAccessToken(), 2 * 60 * 60* 1000);
                return suiteTokenResp.getSuiteAccessToken();
            } else {
                throw new RuntimeException("no SuiteAccessToken");
            }
        }else{
            return suiteToken;
        }
    }

    public String getAccessToken(String corpId) {
        String accessToken = cacheAble.get("workWexin:accessToken:corpId:"+corpId);
        if(null == accessToken|| accessToken.equals("") ){
            CorpInfo corpInfo = corpRepository.queryCorpInfo(corpId,"workweixin");
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
                WorkWeixinAccessTokenResponse corpToken = workWeixinFeignClient.getCorpToken(getSuiteToken(), corpTokenReq);
                if(null != corpToken.getErrmsg()){
                    return null;
                }else{
                    cacheAble.put("workWexin:accessToken:corpId:"+corpId, corpToken.getAccessToken(), 7200*1000);
                    return corpToken.getAccessToken();
                }
            }
        }
        return accessToken;
    }

    public String getSuiteticket(){
        return cacheAble.get("suiteticket");
    }
}
