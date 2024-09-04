package com.ksoussane.customerservice;

import com.ksoussane.customerservice.config.GlobalConfig;
import com.ksoussane.customerservice.entities.Customer;
import com.ksoussane.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {

            List<Customer> customerList= List.of(
                    Customer.builder()
                            .firstName("userfirst")
                            .lastName("userlast")
                            .email("user@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("khalil ")
                            .lastName("soussane")
                            .email("ksoussane@gmail.com")
                            .build()

            );
            customerRepository.saveAll(customerList);
        };
    }


}
