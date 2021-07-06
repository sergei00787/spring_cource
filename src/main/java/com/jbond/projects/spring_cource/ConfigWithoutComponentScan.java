package com.jbond.projects.spring_cource;

import com.jbond.projects.spring_cource.employeesPkg.Programmer;
import com.jbond.projects.spring_cource.employeesPkg.ProjectManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigWithoutComponentScan {
    @Bean
    public ProjectManager projectManagerBean(){
        return new ProjectManager();
    }

    @Bean
    public Programmer programmerBean(){
        return new Programmer();
    }

}
