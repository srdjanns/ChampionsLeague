package com.devact.projects.championsleague.config;

import com.devact.projects.championsleague.model.ModelComponents;
import com.devact.projects.championsleague.repository.RepositoryComponents;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Properties;


/**
 * @author Srdjan Simidzija
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = RepositoryComponents.class)
@PropertySource({"classpath:application.properties"})
public class PersistenceConfiguration {

    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(environment.getRequiredProperty("jdbc.driver-class-name"));
        ds.setUrl(environment.getRequiredProperty("jdbc.url"));
        ds.setUsername(environment.getRequiredProperty("jdbc.username"));
        ds.setPassword(environment.getRequiredProperty("jdbc.password"));
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setPackagesToScan(ModelComponents.ENTITY_PACKAGE);
        emf.setJpaProperties(hibProperties());
        return emf;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.improved_naming_strategy",
                environment.getRequiredProperty("hibernate.improved_naming_strategy"));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

}