package www.thirdauth.com.thirdparty.workweixin.resp;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinAccessTokenResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("errcode")
    private Integer errcode;

    @JsonProperty("errmsg")
    private String errmsg;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;
}