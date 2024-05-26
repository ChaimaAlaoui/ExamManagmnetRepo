package com.ensah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ensah"}) // Scan the package containing controllers
public class ProjetExamPlanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetExamPlanApplication.class, args);
        
//		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//		String password= bCryptPasswordEncoder.encode("password123");//encrypt password
//		System.out.println("the password is "+password);
    }

}