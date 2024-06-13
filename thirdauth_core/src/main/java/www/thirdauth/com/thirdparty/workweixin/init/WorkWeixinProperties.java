package www.thirdauth.com.thirdparty.workweixin.init;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkWeixinProperties implements Serializable {

    //以ww或wx开头应用id（对应于旧的以tj开头的套件id）
    private String suiteId;
    //应用secret
    private String suiteSecret;
    //encodingAESKey
    private String encodingAESKey;

    private String token;
}
