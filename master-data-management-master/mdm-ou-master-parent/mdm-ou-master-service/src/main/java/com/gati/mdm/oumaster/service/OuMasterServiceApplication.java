package com.gati.mdm.oumaster.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class OuMasterServiceApplication {

    public static final String SERVICE_ID = "800";

    public static void main(String[] args) {
        SpringApplication.run(OuMasterServiceApplication.class, args);
    }
}
