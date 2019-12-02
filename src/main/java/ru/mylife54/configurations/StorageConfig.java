package ru.mylife54.configurations;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    Tika getTika(){
        return new Tika();
    }

}
