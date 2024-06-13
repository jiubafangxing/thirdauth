package www.thirdauth.com.thirdparty.workweixin.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinWeb implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("url")
    private String url;

    @JsonProperty("title")
    private String title;
}
