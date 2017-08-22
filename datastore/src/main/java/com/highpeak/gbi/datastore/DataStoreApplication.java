package com.highpeak.gbi.datastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.highpeak.gbi.datastore")

public class DataStoreApplication {
    public static void main(String[] args) {

        SpringApplication.run(DataStoreApplication.class, args);
    }
}
