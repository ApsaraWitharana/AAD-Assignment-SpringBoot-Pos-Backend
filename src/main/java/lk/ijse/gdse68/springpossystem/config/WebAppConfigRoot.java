package lk.ijse.gdse68.springpossystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lk.ijse.gdse68.springpossystem")
@EnableJpaRepositories(basePackages = "lk.ijse.gdse68.springpossystem")
@EnableTransactionManagement
public class WebAppConfigRoot {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

