package urbondev.jobappbe.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class JobListingService {

    private final RestTemplate restTemplate;

    public JobListingService() {
        // Initialize RestTemplate for making HTTP requests.
        this.restTemplate = new RestTemplate();
    }

    /**
     * Simulates fetching dummy job listings from a predefined external API.
     * For demonstration purposes, we use JSONPlaceholder.
     *
     * @param site The job listing site name (dummy parameter in this example).
     * @return A String representing job listings fetched from the external API.
     */
    public String fetchRealJobListings(String site) {
        // Use a dummy endpoint to simulate a real API call.
        // (In a real integration, replace this URL with the actual job API endpoint.)
        String url = "https://jsonplaceholder.typicode.com/posts";

        // Fetch data from the dummy API.
        // This returns an array of JSON objects (dummy posts).
        String apiResponse = restTemplate.getForObject(url, String.class);

        // For this demonstration, return a short snippet of the API response.
        if(apiResponse != null && apiResponse.length() > 200) {
            return apiResponse.substring(0, 200) + "...";
        }
        return apiResponse;
    }

    /**
     * Existing dummy method to simulate job listings.
     */
    public Map<String, List<String>> matchJobs(String resumeText) {
        Map<String, List<String>> matches = new HashMap<>();
        matches.put("Indeed", List.of("Job A from Indeed", "Job B from Indeed", "Job C from Indeed"));
        matches.put("LinkedIn", List.of("Job A from LinkedIn", "Job B from LinkedIn", "Job C from LinkedIn"));
        matches.put("Glassdoor", List.of("Job A from Glassdoor", "Job B from Glassdoor", "Job C from Glassdoor"));
        return matches;
    }
}
