package www.thirdauth.com.thirdparty.workweixin.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinMiniProgram implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("appid")
    private String appid;

    @JsonProperty("pagepath")
    private String pagepath;

    @JsonProperty("title")
    private String title;
}