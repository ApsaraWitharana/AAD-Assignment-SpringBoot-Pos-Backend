package lk.ijse.gdse68.springpossystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : sachini
 * @date : 2024-10-04
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lk.ijse.gdse68.springpossystem")
@EnableJpaRepositories
@EnableTransactionManagement
public class WebAppConfig {
}
