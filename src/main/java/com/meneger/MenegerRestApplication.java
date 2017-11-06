package com.meneger;


import com.meneger.repositories.base.BaseRepositoryFactoryBean;
import com.meneger.repositories.base.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, repositoryBaseClass = BaseRepositoryImpl.class)
@EnableAutoConfiguration
@EnableEntityLinks
@EnableJpaAuditing
@EnableCaching
public class MenegerRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MenegerRestApplication.class,args);
    }
}
