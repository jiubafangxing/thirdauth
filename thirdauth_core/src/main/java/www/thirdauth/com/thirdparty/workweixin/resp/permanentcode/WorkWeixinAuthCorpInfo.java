package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class WorkWeixinAuthCorpInfo implements Serializable {
    @JsonProperty("corpid")
    private String corpid;

    @JsonProperty("corp_name")
    private String corpName;

    @JsonProperty("corp_type")
    private String corpType;

    @JsonProperty("corp_square_logo_url")
    private String corpSquareLogoUrl;

    @JsonProperty("corp_user_max")
    private int corpUserMax;

    @JsonProperty("corp_full_name")
    private String corpFullName;

    @JsonProperty("verified_end_time")
    private long verifiedEndTime;

    @JsonProperty("subject_type")
    private int subjectType;

    @JsonProperty("corp_wxqrcode")
    private String corpWxqrcode;

    @JsonProperty("corp_scale")
    private String corpScale;

    @JsonProperty("corp_industry")
    private String corpIndustry;

    @JsonProperty("corp_sub_industry")
    private String corpSubIndustry;
}
