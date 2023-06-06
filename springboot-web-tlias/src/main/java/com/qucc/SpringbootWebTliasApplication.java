package com.qucc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//开启对Servle组件的支持
@SpringBootApplication
public class SpringbootWebTliasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebTliasApplication.class, args);
    }

}
