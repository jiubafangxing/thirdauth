package www.thirdauth.com.thirdparty;

import feign.Feign;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.thirdauth.com.thirdparty.workweixin.*;
import www.thirdauth.com.thirdparty.workweixin.aes.xml.AesException;

import java.util.jar.JarEntry;

@Configuration
@EnableConfigurationProperties(value =  WorkWeixinProperties.class)
public class ThirdAuthConfig {
    @Autowired
    WorkWeixinProperties workWeixinProperties;
    @Bean
    public WorkWeixinFeignClient workWeixinFeignClient() {
        return Feign.builder()
                .encoder(new ThirdAuthJacksonEncoder())
                .decoder(new ThirdAuthJacksonDecoder())
                .logger(new Slf4jLogger(WorkWeixinFeignClient.class))
                .logLevel(feign.Logger.Level.FULL)
                .contract(new SpringMvcContract())
                .target( WorkWeixinFeignClient.class,"https://qyapi.weixin.qq.com");
    }

    @ConditionalOnMissingBean(value = WorkWeixinActiveParamAccessor.class)
    @ConditionalOnBean(value ={CacheAble.class,CorpRepository.class})
    @Bean
    public WorkWeixinActiveParamAccessor workWeixinActiveParamAccessor(@Autowired CacheAble<String,String> cacheAble,
                                                                       @Autowired CorpRepository corpRepository
                                                                     ){
        WorkWeixinActiveParamAccessor  workWeixinActiveParamAccessor = new WorkWeixinActiveParamAccessor(workWeixinFeignClient(),
                cacheAble,
                corpRepository,
                workWeixinProperties.getSuiteId(),
                workWeixinProperties.getSuiteSecret()
                );
        return workWeixinActiveParamAccessor;
    }
    @ConditionalOnMissingBean(value = PermanentCodeAccessor.class)
    @ConditionalOnBean(value ={CacheAble.class,CorpRepository.class, WorkWeixinActiveParamAccessor.class})
    @Bean
    public PermanentCodeAccessor permanentCodeAccessor(  @Autowired CorpRepository corpRepository,
                                                         @Autowired WorkWeixinActiveParamAccessor workWeixinActiveParamAccessor
                                                         ){
        PermanentCodeAccessor  permanentCodeAccessor = new PermanentCodeAccessor();
        permanentCodeAccessor.setCorpRepository(corpRepository);
        permanentCodeAccessor.setWorkWeixinActiveParamAccessor(workWeixinActiveParamAccessor);
        permanentCodeAccessor.setCorpRepository(corpRepository);
        permanentCodeAccessor.setWorkWeixinFeignClient(workWeixinFeignClient());
        return permanentCodeAccessor;
    }


    @ConditionalOnMissingBean(value = WorkWeixinPushContainer.class)
    @ConditionalOnBean(value ={CorpRepository.class })
    @Bean
    public WorkWeixinPushContainer workWeixinPushContainer(@Autowired CacheAble<String,String> cacheAble,
                                                           @Autowired PermanentCodeAccessor permanentCodeAccessor) throws AesException {
        WorkWeixinPushContainer workWeixinPushContainer = new WorkWeixinPushContainer(workWeixinProperties.getSuiteId(),workWeixinProperties.getCorpId(), workWeixinProperties.getEncodingAESKey(),workWeixinProperties.getToken());
        workWeixinPushContainer.setCacheAble(cacheAble);
//        workWeixinPushContainer.setToken(workWeixinProperties.getToken());
//        workWeixinPushContainer.setSuiteId(workWeixinProperties.getSuiteId());
//        workWeixinPushContainer.setEncodingAESKey(workWeixinProperties.getEncodingAESKey());
        workWeixinPushContainer.setPermanentCodeAccessor(permanentCodeAccessor);
        return workWeixinPushContainer;
    }


}
