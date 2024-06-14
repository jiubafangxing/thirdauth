package www.thirdauth.com.thirdparty.workweixin;

import www.thirdauth.com.thirdparty.CorpRepository;
import www.thirdauth.com.thirdparty.workweixin.resp.permanentcode.WorkWeixinCorpInfo;
import www.thirdauth.com.thirdparty.workweixin.req.WorkWiexinPermanentCodeReq;

public class PermanentCodeAccessor {

    WorkWeixinFeignClient workWeixinFeignClient;

    CorpRepository corpRepository;

    WorkWeixinActiveParamAccessor workWeixinActiveParamAccessor;

<<<<<<< Updated upstream
=======
    public WorkWeixinFeignClient getWorkWeixinFeignClient() {
        return workWeixinFeignClient;
    }

    public void setWorkWeixinFeignClient(WorkWeixinFeignClient workWeixinFeignClient) {
        this.workWeixinFeignClient = workWeixinFeignClient;
    }

    public CorpRepository getCorpRepository() {
        return corpRepository;
    }

    public void setCorpRepository(CorpRepository corpRepository) {
        this.corpRepository = corpRepository;
    }

    public WorkWeixinActiveParamAccessor getWorkWeixinActiveParamAccessor() {
        return workWeixinActiveParamAccessor;
    }

    public void setWorkWeixinActiveParamAccessor(WorkWeixinActiveParamAccessor workWeixinActiveParamAccessor) {
        this.workWeixinActiveParamAccessor = workWeixinActiveParamAccessor;
    }

>>>>>>> Stashed changes
    public void permanentCode(String authCode){
        WorkWiexinPermanentCodeReq workWiexinPermanentCodeReq = new WorkWiexinPermanentCodeReq();
        workWiexinPermanentCodeReq.setAuthcode(authCode);
        WorkWeixinCorpInfo permanentCode = workWeixinFeignClient.getPermanentCode(workWeixinActiveParamAccessor.getSuiteToken(), workWiexinPermanentCodeReq);
        corpRepository.saveCorpInfo(permanentCode);
    }
}
