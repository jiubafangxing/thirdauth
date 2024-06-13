package www.thirdauth.com.thirdparty.workweixin.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import www.thirdauth.com.thirdparty.mark.ThirdUserIdInfo;

import java.io.Serializable;

@Data
public class WorkWeixinUserInfoResp implements ThirdUserIdInfo,Serializable {
    @JsonProperty("errcode")
    private int errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("corpid")
    private String corpId;

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("user_ticket")
    private String userTicket;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("open_userid")
    private String openUserId;

    private String bindCode;

}
