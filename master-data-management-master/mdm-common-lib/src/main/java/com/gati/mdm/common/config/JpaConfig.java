package com.gati.mdm.common.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class JpaConfig {
    private EntityManager entityManager;

    @Autowired
    public JpaConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public JPAQueryFactory getJpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
