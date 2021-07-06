package com.jbond.projects.spring_cource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.jbond.projects.spring_cource")
@EnableAspectJAutoProxy
public class Config {

}
