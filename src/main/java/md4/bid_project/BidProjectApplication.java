package md4.bid_project;

import md4.bid_project.models.User;
import md4.bid_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BidProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BidProjectApplication.class, args);
    }

}

