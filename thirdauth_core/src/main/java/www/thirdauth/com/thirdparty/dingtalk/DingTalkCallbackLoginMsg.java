package www.thirdauth.com.thirdparty.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import www.thirdauth.com.thirdparty.mark.LoginMsg;

import java.io.Serializable;

@Data
public class DingTalkCallbackLoginMsg implements LoginMsg,Serializable {
    //钉钉回调参数，之后通过其（免登码）获取用户信息
    private String requestAuthCode;

    @JsonProperty("auth_corpid")
    private String authCorpId;

}
