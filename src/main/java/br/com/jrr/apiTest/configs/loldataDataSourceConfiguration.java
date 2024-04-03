package br.com.jrr.apiTest.configs;

import br.com.jrr.apiTest.domain.Player.AccountRiot;
import br.com.jrr.apiTest.domain.Player.AccountRiot2;
import com.zaxxer.hikari.HikariDataSource;
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

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.jrr.apiTest.repo2",
        entityManagerFactoryRef = "loldataEntityManagerFactory",
        transactionManagerRef = "loldataTransactionManager")
public class loldataDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.loldata")
    public DataSourceProperties loldataDataSourceProperties(){
        return new DataSourceProperties();
    }




    @Bean
    @ConfigurationProperties("spring.datasource.loldata.configuration")
    public DataSource loldataDataSource(){
        return loldataDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }


    @Bean(name = "loldataEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean loldataEntityManagerFactory(
            EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(loldataDataSource())
                .packages(AccountRiot2.class)
                .build();
    }


    @Bean(name = "loldataTransactionManager")
    public PlatformTransactionManager loldataTransactionManager(
            final @Qualifier("loldataEntityManagerFactory") LocalContainerEntityManagerFactoryBean loldataEntityManagerFactory) {
        return new JpaTransactionManager(loldataEntityManagerFactory.getObject());
    }

}
