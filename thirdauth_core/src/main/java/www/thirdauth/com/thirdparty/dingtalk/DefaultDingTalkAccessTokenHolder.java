package www.thirdauth.com.thirdparty.dingtalk;

import www.thirdauth.com.thirdparty.CacheAble;
import www.thirdauth.com.thirdparty.mark.TokenReq;

public class DefaultDingTalkAccessTokenHolder implements DingTalkAccessTokenHolder{

    DingTalkFeignClient dingTalkFeignClient;

    DingTalkProperties dingTalkProperties;

    CacheAble<String,String> cacheAble;


    @Override
    public String getAccessToken(TokenReq tokenReq) {
//        CorpTokenReq corpTokenReq = (CorpTokenReq)tokenReq;
//        String accessToken = cacheAble.get("com.thirdparty.dingtalk.accessToken"+":"+corpTokenReq.getAuthCorpId());
//        Long timeStamps = System.currentTimeMillis();
//        if(null == accessToken  || "".equals(accessToken.trim())){
//            Map<String, String> unStableParamMap = unStableParamAccessor.getParam();
//            String suiteTicket = unStableParamMap.get("suiteTicket");
//            String signature = SignatureUtil.dingTalkFormatSignature(dingTalkProperties.getAccessSecret(),
//                    timeStamps.toString(),
//                    suiteTicket);
//            String urlEncodeSignature = SignatureUtil.urlEncode(signature,"utf-8");
//            DingTalkCorpTokenResp corpToken = dingTalkFeignClient.getCorpToken(dingTalkProperties.getAccessKey(),
//                    timeStamps.toString(),
//                    suiteTicket,
//                    urlEncodeSignature,
//                    corpTokenReq
//            );
//            if(corpToken.getErrCode() == 0){
//                cacheAble.put("com.thirdparty.dingtalk.accessToken"+":"+corpTokenReq.getAuthCorpId(), corpToken.getAccessToken(),7200000);
//                return corpToken.getAccessToken();
//            }
//        }
        return null;
    }



}
