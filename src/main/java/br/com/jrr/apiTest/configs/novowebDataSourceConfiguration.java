package br.com.jrr.apiTest.configs;

import br.com.jrr.apiTest.domain.Player.AccountRiot;
import com.zaxxer.hikari.HikariDataSource;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.jrr.apiTest.Repository",
        entityManagerFactoryRef = "novowebEntityManagerFactory",
        transactionManagerRef = "novowebTransactionManager")
public class novowebDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.novoweb")
    public DataSourceProperties novowebDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.novoweb.configuration")
    public DataSource novowebDataSource(){
        return novowebDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "novowebEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean novowebEntityManagerFactory(
            EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(novowebDataSource())
                .packages( AccountRiot.class)
                .build();
    }

    @Primary
    @Bean(name = "novowebTransactionManager")
    public PlatformTransactionManager novawebTransactionManager(
            final @Qualifier("novowebEntityManagerFactory") LocalContainerEntityManagerFactoryBean novowebEntityManagerFactory) {
        return new JpaTransactionManager(novowebEntityManagerFactory.getObject());
    }

}

