package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinAuthUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("open_userid")
    private String openUserid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar")
    private String avatar;
}