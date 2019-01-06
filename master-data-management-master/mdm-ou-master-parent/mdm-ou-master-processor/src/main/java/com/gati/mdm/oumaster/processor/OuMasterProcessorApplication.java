package com.gati.mdm.oumaster.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class OuMasterProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OuMasterProcessorApplication.class, args);
    }
}
