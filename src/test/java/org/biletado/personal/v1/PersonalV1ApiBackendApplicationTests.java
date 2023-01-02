
package org.biletado.personal.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.biletado.personal.v1.api.PersonalApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc()
class PersonalV1ApiBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStatusApiCall() throws Exception {
        //Setup Mock Behavior
        this.mockMvc.perform(get("/personal/status/"))
                .andExpect(status().isOk());
    }

}
