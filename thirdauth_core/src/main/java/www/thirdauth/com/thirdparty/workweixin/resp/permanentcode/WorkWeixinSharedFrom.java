package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class WorkWeixinSharedFrom implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("corpid")
    private String corpid;

    @JsonProperty("share_type")
    private int shareType;
}
