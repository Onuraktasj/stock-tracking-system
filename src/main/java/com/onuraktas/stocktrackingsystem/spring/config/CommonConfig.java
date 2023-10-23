package com.onuraktas.stocktrackingsystem.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(value = "com.onuraktas.stocktrackingsystem")
@EnableJpaRepositories(value = "com.onuraktas.stocktrackingsystem.repository")
@EntityScan(value = "com.onuraktas.stocktrackingsystem.entity")
public class CommonConfig {
}
