package com.guia.bandeiras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GuiaDasBandeirasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuiaDasBandeirasApplication.class, args);
    }
}
