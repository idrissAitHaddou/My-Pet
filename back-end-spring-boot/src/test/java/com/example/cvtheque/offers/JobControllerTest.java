package com.example.cvtheque.offers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith({MockitoExtension.class})
class JobControllerTest {
    @Mock
    private OfferService jobService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private JobServiceTest jobServiceTest;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        jobServiceTest = new JobServiceTest();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        // return result job service with mockito
        Mockito.doReturn(true).when(jobService).addJob(Mockito.any());
        Mockito.doReturn(this.jobServiceTest.createJobObject()).when(jobService).getAllJobs();
    }

    @Test
    @DisplayName(value = "testing controller create a new job")
    public void addJob() throws Exception {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String JsonRequest = objectMapper.writeValueAsString(this.jobServiceTest.createJobObject().get(0));
        MvcResult result = mockMvc.perform(post("/v1/api/job/add").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();
//      Boolean response = objectMapper.readValue(resultContext,);
        Assertions.assertTrue(true);
    }

    @Test
    void getJobByUserEmail() {
    }

    @Test
    void testAddJob() {
    }

    @Test
    void testGetJobByUserEmail() {
    }

    @Test
    @DisplayName(value = "testing to get all jobs")
    void getAllJobs() throws Exception {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String JsonRequest = objectMapper.writeValueAsString(this.jobServiceTest.createJobObject());
        MvcResult result = mockMvc.perform(get("/v1/api/job/get/all").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String resultContext = result.getResponse().getContentAsString();
        System.out.println("result context");
        System.out.println(resultContext);
    }

    @Test
    void getJobsByTitle() {
    }

    public OfferDto createJobDtooBject() {
        OfferDto job = new OfferDto();
        job.setId(1L);
        job.setDescription("test descriptio");
        job.setCampany("campnay 1");
        job.setUser(jobServiceTest.createUserObject().get(0));
        return job;
    }
}