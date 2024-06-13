package www.thirdauth.com.thirdparty.workweixin;

import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.CorpRepository;
import www.thirdauth.com.thirdparty.mark.CorpInfo;
import www.thirdauth.com.thirdparty.workweixin.init.WorkWeixinProperties;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinCorpTokenReq;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinSuiteTokenReq;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWeixinAccessTokenResponse;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWiexinSuiteTokenResp;
import www.thirdauth.com.thirdparty.workweixin.resp.permanentcode.WorkWeixinCorpInfo;

public class WorkWeixinActiveParamAccessor  {
    WorkWeixinFeignClient workWeixinFeignClient;

    WorkWeixinProperties workWeixinProperties;

    CacheAble<String,String> cacheAble;

    CorpRepository corpRepository;

    public String getSuiteToken() {
        String suiteToken = cacheAble.get("workWexin:suiteToken");
        if(null == suiteToken || suiteToken.equals("")) {
            String suiteTicket = getSuiteticket();
            WorkWeixinSuiteTokenReq workWeixinSuiteTokenReq = new WorkWeixinSuiteTokenReq();
            workWeixinSuiteTokenReq.setSuiteSecret(workWeixinProperties.getSuiteSecret());
            workWeixinSuiteTokenReq.setSuiteId(workWeixinProperties.getSuiteId());
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
            CorpInfo corpInfo = corpRepository.queryCorpInfo(corpId);
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
