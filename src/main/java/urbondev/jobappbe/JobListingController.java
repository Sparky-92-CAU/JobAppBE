package urbondev.jobappbe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import urbondev.jobappbe.service.JobListingService;

@RestController
@RequestMapping("/jobs")
public class JobListingController {

    private final JobListingService jobListingService;

    // Constructor-based dependency injection
    @Autowired
    public JobListingController(JobListingService jobListingService) {
        this.jobListingService = jobListingService;
    }

    /**
     * GET endpoint to simulate fetching job listings from a job site.
     * Example URL: http://localhost:8080/jobs/list?site=Indeed
     *
     * @param site The job site name or identifier.
     * @return Dummy job listings data.
     */
    @GetMapping("/list")
    public ResponseEntity<String> listJobListings(@RequestParam("site") String site) {
        // Fetch dummy job listings from the service
        String listings = jobListingService.fetchRealJobListings(site);
        return ResponseEntity.ok(listings);
    }

}
