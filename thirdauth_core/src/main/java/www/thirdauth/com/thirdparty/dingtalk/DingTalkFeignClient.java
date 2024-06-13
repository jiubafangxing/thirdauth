package www.thirdauth.com.thirdparty.dingtalk;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "dingTalkFeignClient", url = "https://oapi.dingtalk.com")
public interface DingTalkFeignClient {


    @PostMapping("/topapi/v2/user/getuserinfo")
    DingTalkBaseResp<DingTalkBaseUserInfo> getuserinfo(@RequestBody DingTalkUserInfoReq DingTalkUserInfoReq ,
                                                       @RequestParam("access_token")String accessToken);
    @GetMapping("/service/get_corp_token")
    DingTalkCorpTokenResp getCorpToken(@RequestParam("accessKey")String accessKey,
                        @RequestParam("timestamp")String timestamp,
                        @RequestParam("suiteTicket")String suiteTicket,
                        @RequestParam("signature")String signature,
                        @RequestBody CorpTokenReq corpTokenReq
                        );
}
