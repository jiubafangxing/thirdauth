package www.thirdauth.com.thirdparty.workweixin;

import lombok.extern.slf4j.Slf4j;
import www.thirdauth.com.thirdparty.*;
import www.thirdauth.com.thirdparty.mark.LoginMsg;
import www.thirdauth.com.thirdparty.mark.ThirdUserIdInfo;
import www.thirdauth.com.thirdparty.mark.UserDetail;
import www.thirdauth.com.thirdparty.workweixin.dto.WorkWeixinCallbackLoginMsg;
import www.thirdauth.com.thirdparty.workweixin.dto.WorkWeixinUserDetail;
import www.thirdauth.com.thirdparty.workweixin.resp.WorkWeixinUserInfoResp;
import www.thirdauth.com.thirdparty.workweixin.user.WorkWeixinUserResponse;

/**
 * 企业微信平台首次登录是触发的用户绑定
 */
@Slf4j
public abstract   class WorkWeixinFirstLoginReceiver extends FirstLoginReceiver {


<<<<<<< Updated upstream
    private WorkWeixinFeignClient workWeixinFeignClient;

    private WorkWeixinActiveParamAccessor activeParamAccessor;


=======
    protected  WorkWeixinFeignClient workWeixinFeignClient;

    protected  WorkWeixinActiveParamAccessor activeParamAccessor;


    public void setWorkWeixinFeignClient(WorkWeixinFeignClient workWeixinFeignClient) {
        this.workWeixinFeignClient = workWeixinFeignClient;
    }

    public void setActiveParamAccessor(WorkWeixinActiveParamAccessor activeParamAccessor) {
        this.activeParamAccessor = activeParamAccessor;
    }

>>>>>>> Stashed changes
    @Override
    protected ThirdUserIdInfo reqUserInfo(LoginMsg loginMsg) {
        WorkWeixinCallbackLoginMsg workWeixinCallbackLoginMsg = (WorkWeixinCallbackLoginMsg) loginMsg;
        String suiteAccessToken =   activeParamAccessor.getSuiteToken();
        WorkWeixinUserInfoResp workWeixinUserInfoResp = workWeixinFeignClient.getuserinfo3rd(suiteAccessToken, workWeixinCallbackLoginMsg.getCode());
        return workWeixinUserInfoResp;
    }

    @Override
    protected String queryThirdParty() {
        return "workweixin";
    }

    @Override
    protected UserDetail queryUserDetail(ThirdUserIdInfo thirdUserIdInfo) {
        WorkWeixinUserInfoResp workWeixinUserInfoResp = (WorkWeixinUserInfoResp) thirdUserIdInfo;
        String accessToken = activeParamAccessor.getAccessToken(workWeixinUserInfoResp.getCorpId());
        WorkWeixinUserDetail workWeixinUserDetail = new WorkWeixinUserDetail();
        workWeixinUserDetail.setUserId(workWeixinUserInfoResp.getUserId());
        workWeixinUserDetail.setOpenUserId(workWeixinUserInfoResp.getOpenUserId());
        if(null == accessToken || accessToken.equals("")){
            return workWeixinUserDetail;
        }else{
            WorkWeixinUserResponse user = workWeixinFeignClient.getUser(accessToken, ((WorkWeixinUserInfoResp) thirdUserIdInfo).getUserId());
            workWeixinUserDetail.setName(user.getName());
            workWeixinUserDetail.setMobile(user.getMobile());
            return workWeixinUserDetail;
        }
    }

}
