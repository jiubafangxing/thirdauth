package www.thirdauth.com.thirdparty;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
@ConfigurationProperties("thirdauth.workweixin")
public class WorkWeixinProperties implements Serializable {
    private String corpId;
    //以ww或wx开头应用id（对应于旧的以tj开头的套件id）
    private String suiteId;
    //应用secret
    private String suiteSecret;
    //encodingAESKey
    private String encodingAESKey;

    private String token;

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public String getSuiteSecret() {
        return suiteSecret;
    }

    public void setSuiteSecret(String suiteSecret) {
        this.suiteSecret = suiteSecret;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}


