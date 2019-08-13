package com.karq.integtest.integtestframework.web

import com.karq.integtest.integtestframework.config.PostgresqlConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import javax.annotation.PreDestroy

@AutoConfigureMockMvc
@Testcontainers
@ComponentScan("com.karq.integtest.integtestframework")
@SpringBootTest
@ActiveProfiles("test")
abstract class AbstractSpecification extends Specification {


    @PreDestroy
    void destroy(){
        println "test";
    }
}
