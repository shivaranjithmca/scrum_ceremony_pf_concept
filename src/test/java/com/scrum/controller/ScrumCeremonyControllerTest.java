package com.scrum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrum.persistence.entity.Retrospective;
import com.scrum.service.ScrumCeremonyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ScrumCeremonyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ScrumCeremonyService scrumCeremonyService;

    @Test
    public void create_without_appropriate_retrospective() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create")
                        .content(new ObjectMapper().writeValueAsString(new Retrospective()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void create_with_appropriate_retrospective() throws Exception {
        Mockito.when(scrumCeremonyService.createRetrospective(Mockito.any(Retrospective.class)))
                .thenReturn(mockRetrospective());

        Retrospective retrospective = new Retrospective();
        retrospective.setName("Retrospective 1");
        retrospective.setDate("2023-01-01");
        retrospective.setParticipants(Collections.singletonList("test1, test2"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create")
                        .content(new ObjectMapper().writeValueAsString(retrospective))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    private Retrospective mockRetrospective() {
        Retrospective retrospective = new Retrospective();
        retrospective.setId(1L);
        return retrospective;
    }
}
