package lk.ijse.gdse68.springpossystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @author : sachini
 * @date : 2024-10-04
 **/
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

    @Bean
    public DataSource dataSource(){
        var dbms = new DriverManagerDataSource();
        dbms.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbms.setUrl("jdbc:mysql://localhost:3306/spring_pos_system?createDatabaseIfNotExist=true");
        dbms.setUsername("root");
        dbms.setPassword("ijse@2001");
        return dbms;
    }
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("lk.ijse.gdse68.springpossystem.entity");
        factoryBean.setDataSource(dataSource());
        return factoryBean;
    }

}

