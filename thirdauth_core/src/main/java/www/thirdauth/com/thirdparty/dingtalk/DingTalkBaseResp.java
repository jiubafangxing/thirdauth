package www.thirdauth.com.thirdparty.dingtalk;

import lombok.Data;

import java.io.Serializable;

@Data
public class DingTalkBaseResp<T> implements Serializable {

    private int errcode;

    private String errmsg;

    private T result;
}
