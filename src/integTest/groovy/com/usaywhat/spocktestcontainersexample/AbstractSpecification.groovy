package com.usaywhat.spocktestcontainersexample


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import javax.annotation.PreDestroy

@AutoConfigureMockMvc
@Testcontainers
@ComponentScan("com.usaywhat.spocktestcontainersexample")
@SpringBootTest
@ActiveProfiles("integTest")
abstract class AbstractSpecification extends Specification {


    @PreDestroy
    void destroy(){
        println "test";
    }
}
