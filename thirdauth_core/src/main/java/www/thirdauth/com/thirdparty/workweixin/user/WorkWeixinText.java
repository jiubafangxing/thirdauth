package www.thirdauth.com.thirdparty.workweixin.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinText implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("value")
    private String value;
}