package urbondev.jobappbe.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobMatchingService {

    @Autowired
    private StanfordNLPService nlpService;

    /**
     * Processes resume text and returns job matches based on extracted lemmas.
     *
     * @param resumeText The resume text.
     * @return A map of job categories to dummy job listings.
     */
    public Map<String, List<String>> matchJobs(String resumeText) {
        // Extract lemmas from the resume text
        List<String> lemmas = nlpService.extractLemmas(resumeText);

        // For debugging, you might log the lemmas
        System.out.println("Extracted Lemmas: " + lemmas);

        // Implement basic matching logic using the extracted lemmas
        Map<String, List<String>> matches = new HashMap<>();
        if (lemmas.contains("java") || lemmas.contains("spring")) {
            matches.put("Java Jobs", List.of(
                    "Java Developer at ABC Corp",
                    "Spring Boot Engineer at XYZ Inc",
                    "Senior Java Software Engineer at FooTech"
            ));
        } else if (lemmas.contains("react")) {
            matches.put("Frontend Jobs", List.of(
                    "React Developer at WebWorks",
                    "UI Engineer at CreativeApps"
            ));
        } else {
            matches.put("General Jobs", List.of(
                    "Software Engineer at Generic Corp",
                    "Full-Stack Developer at Startup Hub"
            ));
        }
        return matches;
    }
}
