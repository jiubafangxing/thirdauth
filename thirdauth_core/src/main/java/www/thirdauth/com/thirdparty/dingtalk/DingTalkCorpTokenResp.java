package www.thirdauth.com.thirdparty.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DingTalkCorpTokenResp implements Serializable {
    @JsonProperty("errcode")
    private int errCode;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("expires_in")
    private int expiresIn;
}
