package md4.bid_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class )
public class BidProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BidProjectApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
