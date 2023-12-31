package pl.joboffers.infrastructure.offersfetcher.httpclient;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.joboffers.domain.offersfetcher.OffersFetchable;

import java.time.Duration;

@Configuration
public class OffersFetcherHttpClientConfig {


    @Bean
    public HttpClientResponseErrorHandler restTemplateResponseErrorHandler() {
        return new HttpClientResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(OffersFetcherHttpClientConfigurationProperties properties) {
        return new RestTemplateBuilder().errorHandler(restTemplateResponseErrorHandler()).setConnectTimeout(Duration.ofMillis(properties.connectTimeout())).setReadTimeout(Duration.ofMillis(properties.readTimeout())).build();
    }

    @Bean
    public OffersFetchable offersFetcherHttpClient(RestTemplate restTemplate, OffersFetcherHttpClientConfigurationProperties properties) {
        return new OffersFetcherHttpClient(restTemplate, properties.uri(), properties.port(), properties.service());
    }
}
