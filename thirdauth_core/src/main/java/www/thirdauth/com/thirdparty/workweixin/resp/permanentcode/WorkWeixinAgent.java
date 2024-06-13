package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinAgent implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("agentid")
    private int agentid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("round_logo_url")
    private String roundLogoUrl;

    @JsonProperty("square_logo_url")
    private String squareLogoUrl;

    @JsonProperty("appid")
    private int appid;

    @JsonProperty("auth_mode")
    private int authMode;

    @JsonProperty("is_customized_app")
    private boolean isCustomizedApp;

    @JsonProperty("auth_from_thirdapp")
    private boolean authFromThirdapp;

    @JsonProperty("privilege")
    private WorkWeixinPrivilege privilege;

    @JsonProperty("shared_from")
    private WorkWeixinSharedFrom sharedFrom;
}