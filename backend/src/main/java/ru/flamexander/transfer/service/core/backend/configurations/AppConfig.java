package ru.flamexander.transfer.service.core.backend.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import ru.flamexander.transfer.service.core.backend.configurations.RestClientFactory;

@Configuration
@EnableConfigurationProperties({
        AppProperties.class,
        ClientsInfoServiceProperties.class,
        LimitsServiceProperties.class
})
public class AppConfig {
    private final RestClientFactory restClientFactory = new RestClientFactory();
    //    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "clientsInfoClient")
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestClient clientsInfoClient(ClientsInfoServiceProperties properties) {
        return restClientFactory.createRestClient(properties);
    }
    @Bean(name="limitsClient")
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestClient limitsClient(LimitsServiceProperties properties) {
        return restClientFactory.createRestClient(properties);
    }
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        return filter;
    }
}