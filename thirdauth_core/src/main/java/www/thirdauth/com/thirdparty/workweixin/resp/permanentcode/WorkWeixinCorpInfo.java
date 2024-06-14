package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import www.thirdauth.com.thirdparty.mark.CorpInfo;

@Data
public class WorkWeixinCorpInfo implements CorpInfo {
    @JsonProperty("errcode")
    private int errcode;

    @JsonProperty("errmsg")
    private String errmsg;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("permanent_code")
    private String permanentCode;

    @JsonProperty("dealer_corp_info")
    private WorkWeixinDealerCorpInfo dealerCorpInfo;

    @JsonProperty("auth_corp_info")
    private WorkWeixinAuthCorpInfo authCorpInfo;

    @JsonProperty("auth_info")
    private WorkWeixinAuthInfo authInfo;

    @JsonProperty("auth_user_info")
    private WorkWeixinAuthUserInfo authUserInfo;

    @JsonProperty("register_code_info")
    private WorkWeixinRegisterCodeInfo registerCodeInfo;

    @JsonProperty("state")
    private String state;

    @Override
    public String getCorpId() {
        return authCorpInfo.getCorpid();
    }

    @Override
    public String getThirdPartyCode() {
        return "workweixin";
    }
}
