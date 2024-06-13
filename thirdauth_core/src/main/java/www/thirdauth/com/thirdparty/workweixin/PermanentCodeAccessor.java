package www.thirdauth.com.thirdparty.workweixin;

import www.thirdauth.com.thirdparty.CorpRepository;
import www.thirdauth.com.thirdparty.workweixin.resp.permanentcode.WorkWeixinCorpInfo;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWiexinPermanentCodeReq;

public class PermanentCodeAccessor {

    WorkWeixinFeignClient workWeixinFeignClient;

    CorpRepository corpRepository;

    WorkWeixinActiveParamAccessor workWeixinActiveParamAccessor;

    public void permanentCode(String authCode){
        WorkWiexinPermanentCodeReq workWiexinPermanentCodeReq = new WorkWiexinPermanentCodeReq();
        workWiexinPermanentCodeReq.setAuthcode(authCode);
        WorkWeixinCorpInfo permanentCode = workWeixinFeignClient.getPermanentCode(workWeixinActiveParamAccessor.getSuiteToken(), workWiexinPermanentCodeReq);
        corpRepository.saveCorpInfo(permanentCode);
    }
}
