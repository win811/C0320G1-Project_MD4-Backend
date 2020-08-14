package md4.bid_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BidProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BidProjectApplication.class, args);
    }

}

