package www.thirdauth.com.thirdparty;

import www.thirdauth.com.thirdparty.mark.CorpInfo;

public interface CorpRepository {
    CorpInfo queryCorpInfo(String corpId, String thirdPartyCode);

    void saveCorpInfo(CorpInfo resp);
}
