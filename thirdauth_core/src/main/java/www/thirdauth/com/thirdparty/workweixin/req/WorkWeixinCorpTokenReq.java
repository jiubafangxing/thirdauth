package www.thirdauth.com.thirdparty.workweixin.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinCorpTokenReq implements Serializable {

    @JsonProperty("auth_corpid")
    private String authCorpid;

    @JsonProperty("permanent_code")
    private String permanentCode;
}
