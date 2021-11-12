package uz.pdp.sololearnuzversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

@SpringBootApplication
public class SololearnUzVersionApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SololearnUzVersionApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
