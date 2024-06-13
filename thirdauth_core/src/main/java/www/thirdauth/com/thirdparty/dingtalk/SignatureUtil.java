package www.thirdauth.com.thirdparty.dingtalk;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class SignatureUtil implements Serializable {


    public static String dingTalkFormatSignature(String suiteSecret , String timestamp, String suiteTicket){
        String toSign = timestamp + "\n" + suiteTicket;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(suiteSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] signData = mac.doFinal(toSign.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(signData);
        } catch (NoSuchAlgorithmException e) {
            log.error("HmacSHA256 algorithm not found", e);
        } catch (InvalidKeyException e) {
            log.error("Invalid key: {}", suiteSecret, e);
        } catch (Exception e) {
            log.error("Unexpected error during signature generation", e);
        }
        return null;
    }

    public static String urlEncode(String value, String encoding) {
        if (value == null) {
            return "";
        }
        try {
            String encoded = URLEncoder.encode(value, encoding);
            return encoded.replace("+", "%20").replace("*", "%2A")
                    .replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("FailedToEncodeUri", e);
        }
    }
}
