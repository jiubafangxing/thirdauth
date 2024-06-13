package www.thirdauth.com.thirdparty.workweixin.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinSuiteTokenReq implements Serializable {

    @JsonProperty("suite_id")
    private String suiteId;

    @JsonProperty("suite_secret")
    private String suiteSecret;

    @JsonProperty("suite_ticket")
    private String suiteTicket;
}
