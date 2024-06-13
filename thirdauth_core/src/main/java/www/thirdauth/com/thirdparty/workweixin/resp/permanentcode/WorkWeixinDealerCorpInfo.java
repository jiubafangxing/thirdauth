package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WorkWeixinDealerCorpInfo {
    @JsonProperty("corpid")
    private String corpid;

    @JsonProperty("corp_name")
    private String corpName;
}
