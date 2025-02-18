package urbondev.jobappbe;



import urbondev.jobappbe.service.JobMatchingService;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private JobMatchingService jobMatchingService;

    @PostMapping("/match")
    public ResponseEntity<?> matchResume(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        Tika tika = new Tika();
        try {
            String resumeText = tika.parseToString(file.getInputStream());
            Map<String, List<String>> jobMatches = jobMatchingService.matchJobs(resumeText);
            return ResponseEntity.ok(jobMatches);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing resume: " + e.getMessage());
        }
    }
}
