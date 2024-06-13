package www.thirdauth.com.thirdparty.dingtalk;

import lombok.Data;

import java.io.Serializable;

@Data
public class DingTalkProperties implements Serializable {
        //如果是定制应用，输入定制应用的CustomKey，可在钉钉开发者后台的应用详情页获取。
        //如果是第三方企业应用，输入第三方企业应用的SuiteKey，可在钉钉开发者后台的应用详情页获取。
        private String accessKey;
        //如果是定制应用，输入定制应用的CustomSecret，可在钉钉开发者后台的应用详情页获取。
        //如果是第三方企业应用，输入第三方企业应用的SuiteSecret，可在钉钉开发者后台的应用详情页获取。
        private String accessSecret;

}
