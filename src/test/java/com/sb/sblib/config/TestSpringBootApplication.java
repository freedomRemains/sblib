package com.sb.sblib.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "com.sb.sblib"
})
@MapperScan({
    "com.sb.sblib.mapper",
})
public class TestSpringBootApplication {
}
