package com.usaywhat.spocktestcontainersexample.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.usaywhat.spocktestcontainersexample.AbstractSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class SaveUserToDatabaseSpec extends AbstractSpecification {

    @Autowired
    private MockMvc mvc

    def "Check that user is added to database"(){
        given: "we have a user"
        User user = new User();
        user.setFirstName("John")
        user.setLastName("Smith")
        user.setUsername("JSmith2")

        when: "a request is sent with user data to /user endpoint"
        String savedUserJSON = mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString;
        User savedUser = new ObjectMapper().readValue(savedUserJSON, User.class);

        then: "user is saved to database"
        String getUserJSON = mvc.perform(get("/user/"+savedUser.getId()))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString;
        User getUser = new ObjectMapper().readValue(getUserJSON, User.class);
        assert getUser != null
        assert getUser == savedUser

    }
}
