package www.thirdauth.com.thirdparty.workweixin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWeixinAccessTokenResponse;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWeixinUserInfoResp;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWiexinSuiteTokenResp;
import www.thirdauth.com.thirdparty.workweixin.resp.permanentcode.WorkWeixinCorpInfo;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinCorpTokenReq;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWeixinSuiteTokenReq;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWiexinPermanentCodeReq;
import www.thirdauth.com.thirdparty.workweixin.user.WorkWeixinUserResponse;

//@FeignClient(value = "workWeixinFeignClient", url = "https://qyapi.weixin.qq.com")
public interface WorkWeixinFeignClient {

    @PostMapping("/cgi-bin/service/auth/getuserinfo3rd")
    WorkWeixinUserInfoResp getuserinfo3rd(@RequestParam("suite_access_token") String  suiteAccessToken , @RequestParam("code")String code);

    @PostMapping("/cgi-bin/service/get_suite_token")
    WorkWiexinSuiteTokenResp getSuiteToken(@RequestBody WorkWeixinSuiteTokenReq suiteTokenReq);
    @PostMapping("/cgi-bin/service/get_permanent_code")
    WorkWeixinCorpInfo getPermanentCode(@RequestParam("suite_access_token") String suiteAccessToken, @RequestBody WorkWiexinPermanentCodeReq workWiexinPermanentCodeReq);

    @PostMapping("/cgi-bin/service/get_corp_token")
    WorkWeixinAccessTokenResponse getCorpToken(@RequestParam("suite_access_token") String suiteAccessToken, @RequestBody WorkWeixinCorpTokenReq corpTokenReq);

    @GetMapping("/cgi-bin/user/get")
    WorkWeixinUserResponse getUser(@RequestParam("access_token")String accessToken, @RequestParam("userid")String userId);

}
