package www.thirdauth.com.thirdparty.workweixin.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinAttr implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("type")
    private int type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("text")
    private WorkWeixinText text;

    @JsonProperty("web")
    private WorkWeixinWeb web;

    @JsonProperty("miniprogram")
    private WorkWeixinMiniProgram miniprogram;
}
