package www.thirdauth.com.thirdparty.dingtalk;

import www.thirdauth.com.thirdparty.mark.TokenReq;

public interface DingTalkAccessTokenHolder {
    /**
     * @return 返回accessToken
     */
    String getAccessToken(TokenReq tokenReq);
}
