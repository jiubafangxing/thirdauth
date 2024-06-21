package www.thirdauth.com.thirdparty.workweixin.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WorkWiexinPermanentCodeReq {

    @JsonProperty("auth_code")
    private String authcode;
}
