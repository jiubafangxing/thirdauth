package www.thirdauth.com.thirdparty;

import www.thirdauth.com.thirdparty.mark.CorpInfo;

public interface CorpRepository {
<<<<<<< Updated upstream
    CorpInfo queryCorpInfo(String corpId);
=======
    CorpInfo queryCorpInfo(String corpId, String thirdPartyCode);
>>>>>>> Stashed changes

    void saveCorpInfo(CorpInfo resp);
}
