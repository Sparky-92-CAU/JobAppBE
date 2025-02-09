package urbondev.jobappbe;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import urbondev.jobappbe.service.MatchingService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    // Tika instance for parsing files.
    private final Tika tika = new Tika();

    // MatchingService injected via constructor.
    private final MatchingService matchingService;

    @Autowired
    public ResumeController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    /**
     * Existing endpoint: extracts text from the uploaded resume and returns a preview.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }
        try {
            String fileContent = tika.parseToString(file.getInputStream());
            return ResponseEntity.ok("Extracted resume text: " +
                    fileContent.substring(0, Math.min(200, fileContent.length())) + "...");
        } catch (IOException | TikaException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to parse resume: " + e.getMessage());
        }
    }

    /**
     * New endpoint: parses the resume and returns dummy job matches.
     * URL Example: POST http://localhost:8080/resume/match?file=...
     */
    @PostMapping("/match")
    public ResponseEntity<?> matchResume(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }
        try {
            // Extract the text from the resume.
            String resumeText = tika.parseToString(file.getInputStream());
            // Get job matches from the matching service.
            Map<String, java.util.List<String>> jobMatches = matchingService.matchJobs(resumeText);
            // Return the job matches as JSON.
            return ResponseEntity.ok(jobMatches);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to process resume: " + e.getMessage());
        }
    }
}
