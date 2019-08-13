package com.karq.integtest.integtestframework.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class XyGetUserFromDatabaseSpec extends AbstractSpecification {

    @Autowired
    private MockMvc mvc

    def "Check that user is added to database"(){
        given: "we have a user id"
        Long userId = 1;

        when: "a GET request is sent with id to /user endpoint"
        String getUserStr = mvc.perform(get("/user/"+userId))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString;
        User getUser = new ObjectMapper().readValue(getUserStr, User.class);

        then: "user retrieved from database"
            Assert.assertNotNull(getUser)


    }
}
