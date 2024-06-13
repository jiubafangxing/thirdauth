package www.thirdauth.com.thirdparty.workweixin.resp.permanentcode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class WorkWeixinPrivilege implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("level")
    private int level;

    @JsonProperty("allow_party")
    private List<Integer> allowParty;

    @JsonProperty("allow_user")
    private List<String> allowUser;

    @JsonProperty("allow_tag")
    private List<Integer> allowTag;

    @JsonProperty("extra_party")
    private List<Integer> extraParty;

    @JsonProperty("extra_user")
    private List<String> extraUser;

    @JsonProperty("extra_tag")
    private List<Integer> extraTag;
}
