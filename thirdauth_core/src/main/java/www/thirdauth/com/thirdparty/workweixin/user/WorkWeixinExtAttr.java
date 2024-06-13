package www.thirdauth.com.thirdparty.workweixin.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WorkWeixinExtAttr implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("attrs")
    private List<WorkWeixinAttr> attrs;
}