package urbondev.jobappbe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMatchResumeEndpoint() throws Exception {
        // Create a dummy file to upload.
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "testResume.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Java Spring React".getBytes()
        );

        // Perform a multipart POST request to /resume/match.
        mockMvc.perform(multipart("/resume/match").file(file))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

