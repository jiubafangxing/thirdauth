package www.thirdauth.com.thirdparty.workweixin.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WorkWeixinUserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("errcode")
    private int errcode;

    @JsonProperty("errmsg")
    private String errmsg;

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("department")
    private List<Integer> department;

    @JsonProperty("order")
    private List<Integer> order;

    @JsonProperty("position")
    private String position;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("email")
    private String email;

    @JsonProperty("biz_mail")
    private String bizMail;

    @JsonProperty("is_leader_in_dept")
    private List<Integer> isLeaderInDept;

    @JsonProperty("direct_leader")
    private List<String> directLeader;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("thumb_avatar")
    private String thumbAvatar;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("address")
    private String address;

    @JsonProperty("open_userid")
    private String openUserid;

    @JsonProperty("main_department")
    private int mainDepartment;

    @JsonProperty("extattr")
    private WorkWeixinExtAttr extattr;

    @JsonProperty("status")
    private int status;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("external_position")
    private String externalPosition;

    @JsonProperty("external_profile")
    private WorkWeixinExternalProfile externalProfile;
}
