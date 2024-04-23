import app.CatApplication;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CatApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewCatTest() throws Exception {
        mockMvc.perform(post("/cats").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"kot\",\"dateOfBirth\":\"02.02.2002\",\"color\": \"grey\", \"breed\": \"unknown\", \"ownerName\": \"nik\"}")).andExpect(status().isOk());

    }

    @Test
    void addNewOwnerTest() throws Exception {
        mockMvc.perform(post("/owners").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"q\",\"dateOfBirth\":\"02.02.2002\"}")).andExpect(status().isOk());
    }

    @Test
    void deleteCatTest() throws Exception {
        mockMvc.perform(delete("/cats/kot")).andExpect(status().isOk());
    }

    @Test
    void deleteOwnerTest() throws Exception {
        mockMvc.perform(delete("/owners/q")).andExpect(status().isOk());
    }

    @Test
    void getAllCatsTest() throws Exception {
        mockMvc.perform(get("/cats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));
    }

    @Test
    void getAllOwnersTest() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getCatByNameTest() throws Exception {
        mockMvc.perform(get("/cats/lutik"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("lutik"))
                .andExpect(jsonPath("$.dateOfBirth").value("01.01.2021"))
                .andExpect(jsonPath("$.breed").value("bengal"))
                .andExpect(jsonPath("$.color").value("ginger"))
                .andExpect(jsonPath("$.ownerName").value("lisa"));
    }

    @Test
    void getOwnerByNameTest() throws Exception {
        mockMvc.perform(get("/owners/lisa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("lisa"))
                .andExpect(jsonPath("$.dateOfBirth").value("28.10.2004"));
    }

    @Test
    void setFriendshipTest() throws Exception {
        mockMvc.perform(put("/cats/make-friends-chernish-fred"))
                .andExpect(status().isOk());
    }
}
