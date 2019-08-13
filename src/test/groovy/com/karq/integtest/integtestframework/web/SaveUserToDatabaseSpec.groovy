package com.karq.integtest.integtestframework.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class SaveUserToDatabaseSpec extends AbstractSpecification {

    @Autowired
    private MockMvc mvc

    def "Check that user is added to database"(){
        given: "we have a user"
        User user = new User();
        user.setFirstName("Kunnar")
        user.setLastName("mets")
        user.setUsername("kunnar2")

        when: "a post request is sent with user data to /user endpoint"
        String postUserStr = mvc.perform(post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString;
        User postUser = new ObjectMapper().readValue(postUserStr, User.class);

        then: "user is saved to database"
        String getUserStr = mvc.perform(get("/user/"+postUser.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString;
        User getUser = new ObjectMapper().readValue(getUserStr, User.class);
        Assert.assertNotNull(getUser)
        Assert.assertEquals(postUser, getUser);

    }
}
