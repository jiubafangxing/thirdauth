package www.thirdauth.com.thirdparty;

import www.thirdauth.com.thirdparty.mark.LoginMsg;
import www.thirdauth.com.thirdparty.mark.ThirdUserIdInfo;
import www.thirdauth.com.thirdparty.mark.UserDetail;

/**
 * 首次登录是触发的用户绑定
 */
public  abstract  class FirstLoginReceiver {



    public ThirdUserIdInfo receiveMsg(LoginMsg loginMsg){
        ThirdUserIdInfo thirdUserIdInfo = reqUserInfo(loginMsg);
        if(null != thirdUserIdInfo) {
            Integer exist = queryExist(thirdUserIdInfo);
            //如果不存在则注册用户
            if(exist == 0) {
                saveThird(thirdUserIdInfo);
            }
        }
        return thirdUserIdInfo;
    }

    private  void saveThird(ThirdUserIdInfo thirdUserIdInfo){
        UserDetail userDetail = queryUserDetail(thirdUserIdInfo);
        if(null != userDetail){
            register(userDetail);
        }
    }

    protected abstract void register(UserDetail userDetail) ;

    /**
     * @param thirdUserIdInfo 包含用户唯一标识的实体
     * @return 查询对应用户是否存在于系统之中
     */
    protected abstract Integer queryExist(ThirdUserIdInfo thirdUserIdInfo);

    /***
     * 从对接渠道查询用户信息
     * @param loginMsg 包含成员授权获取到的code
     * @return 返回用户的唯一标识
     */
    protected abstract ThirdUserIdInfo reqUserInfo(LoginMsg loginMsg);

    /**
     * @return 查询当前属于哪种渠道
     */
    protected abstract String queryThirdParty();

    /**
     * @param thirdUserIdInfo 包含用户唯一标识的实体
     * @return 返回用户的完备信息，姓名，手机号（是否返回完整取决于授权是否完整）
     */
    protected abstract UserDetail queryUserDetail(ThirdUserIdInfo thirdUserIdInfo) ;

}
