package www.thirdauth.com.thirdparty.dingtalk;

import lombok.Data;

import java.io.Serializable;

/**
 * 钉钉用户查询请求
 */
@Data
public class DingTalkUserInfoReq implements Serializable {
    //免登授权码
    private String code;
}
