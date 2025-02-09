package urbondev.jobappbe.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchingService {

    /**
     * Enhances job matching by using simple keyword-based matching.
     *
     * @param resumeText The extracted text from the uploaded resume.
     * @return A Map where each key is a job site name and the value is a list of job recommendations.
     */
    public Map<String, List<String>> matchJobs(String resumeText) {
        // Convert resume text to lowercase for case-insensitive matching.
        String text = resumeText.toLowerCase();
        Map<String, List<String>> matches = new HashMap<>();

        // For Indeed: if the resume mentions "java", return Java-specific listings.
        if (text.contains("java")) {
            matches.put("Indeed", List.of(
                    "Java Developer at ABC Corp",
                    "Senior Java Engineer at XYZ Inc",
                    "Java Backend Developer at FooBar Ltd."
            ));
        } else {
            matches.put("Indeed", List.of(
                    "General Developer Position A",
                    "General Developer Position B",
                    "General Developer Position C"
            ));
        }

        // For LinkedIn: if the resume mentions "react", return React-specific listings.
        if (text.contains("react")) {
            matches.put("LinkedIn", List.of(
                    "React Frontend Developer at ABC Corp",
                    "React Developer at XYZ Inc",
                    "UI/UX Developer with React skills at FooBar Ltd."
            ));
        } else {
            matches.put("LinkedIn", List.of(
                    "General Developer Position D",
                    "General Developer Position E",
                    "General Developer Position F"
            ));
        }

        // For Glassdoor: if the resume mentions "spring", return Spring Boot-specific listings.
        if (text.contains("spring")) {
            matches.put("Glassdoor", List.of(
                    "Spring Boot Developer at ABC Corp",
                    "Spring Framework Engineer at XYZ Inc",
                    "Microservices Developer at FooBar Ltd."
            ));
        } else {
            matches.put("Glassdoor", List.of(
                    "General Developer Position G",
                    "General Developer Position H",
                    "General Developer Position I"
            ));
        }

        return matches;
    }
}
