package com.usaywhat.spocktestcontainersexample.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.usaywhat.spocktestcontainersexample.AbstractSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

class GetAllUsersSpec extends AbstractSpecification {

    @Autowired
    private MockMvc mvc

    def "Check that all users are retrieved from /user/all endpoint"() {
        given: "we have two new users in our database"
        User jane = new User();
        jane.setFirstName("Jane")
        jane.setLastName("Woods")
        jane.setUsername("JWoods3")
        postUser(jane)

        User rick = new User();
        rick.setFirstName("Rick")
        rick.setLastName("Island")
        rick.setUsername("RIsland4")
        postUser(rick)

        when: "all users are retrieved from the database"
        String allUsersJSON = mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString;

        then: "all our given users are retrieved"
        assert allUsersJSON != null
        assert allUsersJSON.contains("RIsland4")
        assert allUsersJSON.contains("JWoods3")
        //there can be a lot more users in this retrieved list
        // because on every run the database is shared between all tests
        // and this means that users from other tests can also show up in this list.

    }

    def postUser(User user) {
        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())

    }
}
