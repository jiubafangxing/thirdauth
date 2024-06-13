package www.thirdauth.com.thirdparty.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import www.thirdauth.com.thirdparty.mark.TokenReq;

import java.io.Serializable;

@Data
public class CorpTokenReq implements TokenReq ,Serializable {
    @JsonProperty("auth_corpid")
    private String authCorpId;
}
