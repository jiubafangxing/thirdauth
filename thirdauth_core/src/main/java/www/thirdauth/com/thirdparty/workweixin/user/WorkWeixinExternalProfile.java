package www.thirdauth.com.thirdparty.workweixin.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WorkWeixinExternalProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("external_corp_name")
    private String externalCorpName;

    @JsonProperty("wechat_channels")
    private WorkWeixinWechatChannels wechatChannels;

    @JsonProperty("external_attr")
    private List<WorkWeixinAttr> externalAttr;
}
