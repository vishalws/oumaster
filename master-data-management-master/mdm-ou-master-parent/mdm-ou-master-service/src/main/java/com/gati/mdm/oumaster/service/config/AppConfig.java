package com.gati.mdm.oumaster.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gati.mdm.common.config.AvroSchemaConfig;
import com.gati.mdm.common.config.CommonLibConfig;
import com.gati.mdm.common.config.ConfluentSchemaRegistryConfiguration;
import com.gati.mdm.common.config.JaversConfig;
import com.gati.mdm.common.config.JpaConfig;
import com.gati.mdm.oumaster.common.config.RepositoryConfig;

@Configuration
@Import({ AvroSchemaConfig.class, ConfluentSchemaRegistryConfiguration.class, RepositoryConfig.class,
        JaversConfig.class, JpaConfig.class, CommonLibConfig.class })
@ComponentScan(basePackages = "com.gati.mdm.oumaster")
@EnableAutoConfiguration
public class AppConfig {

}
