package www.thirdauth.com.thirdparty.workweixin.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class WorkWiexinSuiteTokenResp implements Serializable {

    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("suite_access_token")
    private String suiteAccessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

}
