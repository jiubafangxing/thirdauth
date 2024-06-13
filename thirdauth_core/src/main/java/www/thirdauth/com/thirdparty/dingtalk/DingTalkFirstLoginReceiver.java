package www.thirdauth.com.thirdparty.dingtalk;

import lombok.extern.slf4j.Slf4j;
import www.thirdauth.com.thirdparty.*;
import www.thirdauth.com.thirdparty.mark.LoginMsg;
import www.thirdauth.com.thirdparty.mark.ThirdUserIdInfo;

/**
 * 钉钉平台首次登录是触发的用户绑定
 */
@Slf4j
public abstract class DingTalkFirstLoginReceiver extends FirstLoginReceiver {

    DingTalkFeignClient dingTalkFeignClient;


    DingTalkAccessTokenHolder dingTalkAccessTokenHolder;

    @Override
    protected ThirdUserIdInfo reqUserInfo(LoginMsg loginMsg) {
        DingTalkCallbackLoginMsg dingTalkLoginMsg= (DingTalkCallbackLoginMsg) loginMsg;
        DingTalkUserInfoReq dingTalkUserInfoReq = new DingTalkUserInfoReq();
        dingTalkUserInfoReq.setCode(dingTalkUserInfoReq.getCode());
        CorpTokenReq corpTokenReq = new CorpTokenReq();
        corpTokenReq.setAuthCorpId(dingTalkLoginMsg.getAuthCorpId());
        String accessToken = dingTalkAccessTokenHolder.getAccessToken(corpTokenReq);
        DingTalkBaseResp<DingTalkBaseUserInfo> userInfoResp = dingTalkFeignClient.getuserinfo(dingTalkUserInfoReq, accessToken);
        if(userInfoResp.getErrcode() == 0){
            return userInfoResp.getResult();
        }
        return null;
    }

    @Override
    protected String queryThirdParty() {
        return "dingTalk";
    }



}
