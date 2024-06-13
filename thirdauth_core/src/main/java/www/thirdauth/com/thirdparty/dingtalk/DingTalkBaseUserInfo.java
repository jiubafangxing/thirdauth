package www.thirdauth.com.thirdparty.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import www.thirdauth.com.thirdparty.mark.ThirdUserIdInfo;

import java.io.Serializable;

@Data
public class DingTalkBaseUserInfo implements ThirdUserIdInfo, Serializable {
    @JsonProperty("associated_unionid")
    private String associatedUnionid;

    @JsonProperty("unionid")
    private String unionid;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("sys_level")
    private int sysLevel;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sys")
    private boolean sys;

    @JsonProperty("userid")
    private String userId;

    private String bindCode;
}
