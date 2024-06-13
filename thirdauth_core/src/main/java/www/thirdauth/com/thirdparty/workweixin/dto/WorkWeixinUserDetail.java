package www.thirdauth.com.thirdparty.workweixin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import www.thirdauth.com.thirdparty.mark.UserDetail;

@Data
public class WorkWeixinUserDetail implements UserDetail {

    @JsonProperty("corpid")
    private String corpId;

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("open_userid")
    private String openUserId;

    private String mobile;

    private String name;


}
