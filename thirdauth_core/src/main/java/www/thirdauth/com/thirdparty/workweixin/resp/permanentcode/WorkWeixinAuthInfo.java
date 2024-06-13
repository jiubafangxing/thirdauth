package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WorkWeixinAuthInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("agent")
    private List<WorkWeixinAgent> agent;
}