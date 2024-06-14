package www.thirdauth.com.thirdparty.workweixin;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

public class WorkWeixinFeignConfig {

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }
}
