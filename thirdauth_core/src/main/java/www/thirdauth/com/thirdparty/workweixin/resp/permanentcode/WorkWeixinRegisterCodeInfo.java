package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class WorkWeixinRegisterCodeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("register_code")
    private String registerCode;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("state")
    private String state;
}