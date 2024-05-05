import app.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CatApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void signUpTest() throws Exception {
        mockMvc.perform(
                        post("/auth/sign-up").contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"qwerty\",\"dateOfBirth\":\"02.02.2002\",\"password\":\"123456\",\"role\": \"\",\"cats\": []}"))
                .andExpect(status().isOk());
    }

    @Test
    public void signInFailTest() throws Exception {
        mockMvc.perform(post("/auth/sign-in").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"qwerty\",\"password\":\"123456\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void signInSuccessTest() throws Exception {
        mockMvc.perform(post("/auth/sign-in").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test_user2\",\"password\":\"123456\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void addCatSuccess() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test_user1\",\"password\":\"123456\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseContent);
        String token = jsonNode.get("token").asText();

        mockMvc.perform(get("/cats/lutik")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"cattt\",\"dateOfBirth\": \"02.02.2002\",\"breed\": \"maine-coon\",\"color\": \"white\",\"ownerName\": \"test_user1\",\"friends\": []}"))
                .andExpect(status().isOk());
    }

    @Test
    public void addCatFail() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test_user1\",\"password\":\"123456\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseContent);
        String token = jsonNode.get("token").asText();

        mockMvc.perform(get("/cats/lutik")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"cat2\",\"dateOfBirth\": \"02.02.2002\",\"color\": \"white\",\"breed\": \"maine-coon\",\"ownerName\": \"test_user2\",\"friends\": []}"))
                .andExpect(status().isOk());
    }

    @Test
    public void giveAdminFailTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test_user1\",\"password\":\"123456\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String token = objectMapper.readTree(result.getResponse().getContentAsString()).get("token").asText();

        mockMvc.perform(put("/give-admin/test_user1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void giveAdminSuccessTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String token = objectMapper.readTree(result.getResponse().getContentAsString()).get("token").asText();

        mockMvc.perform(put("/give-admin/test_user1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void catByBreedAdminTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String token = objectMapper.readTree(result.getResponse().getContentAsString()).get("token").asText();

        mockMvc.perform(get("/cats/breed/bengal")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void catByBreedUserTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test_user1\",\"password\":\"123456\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String token = objectMapper.readTree(result.getResponse().getContentAsString()).get("token").asText();

        mockMvc.perform(get("/cats/breed/bengal")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
