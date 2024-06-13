package www.thirdauth.com.thirdparty.workweixin.dto;

import lombok.Data;
import www.thirdauth.com.thirdparty.mark.LoginMsg;

import java.io.Serializable;

/**
 * 企业微信
 */
@Data
public class WorkWeixinCallbackLoginMsg implements LoginMsg, Serializable {
    //成员授权code
    private String code;
    private String state;
}
