package com.msproject.jobms.service.imple;

import com.msproject.jobms.clients.CompanyClient;
import com.msproject.jobms.clients.ReviewClient;
import com.msproject.jobms.dto.JobDTO;
import com.msproject.jobms.external.Company;
import com.msproject.jobms.external.Review;
import com.msproject.jobms.mapper.JobMapper;
import com.msproject.jobms.model.Jobs;
import com.msproject.jobms.repository.JobRepository;
import com.msproject.jobms.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;


    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;

    }

    @Override
    public ResponseEntity<List<JobDTO>> findAll() {
        List<Jobs> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOs = jobs.stream().map(this::convertToDTO).toList();

        return new ResponseEntity<>(
                jobDTOs
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Jobs>> findJobsOfOneCompany(Long companyId) {
        List<Jobs> jobs = jobRepository.findByCompanyId(companyId);
        return new ResponseEntity<>(jobRepository.findByCompanyId(companyId), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> createJob(Jobs jobs) {
        Company company = companyClient.getCompany(jobs.getCompanyId());
        if (company == null){
            return new ResponseEntity<>("Company not exists", HttpStatus.NOT_FOUND);
        }
        jobRepository.save(jobs);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<JobDTO> getJobById(Long id) {
        Jobs job = jobRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        return new ResponseEntity<>(convertToDTO(job), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteJobById(Long id) {
        Jobs oldjob = jobRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        jobRepository.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateJob(Jobs jobs) {
        Long jobid = jobs.getId();
        Jobs oldjob = jobRepository.findById(jobid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        jobRepository.save(jobs);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    private JobDTO convertToDTO(Jobs job){

//        Company company = restTemplate.getForObject(
//                "http://COMPANYMS:8081/company/getonecompany/"+job.getCompanyId(),
//                Company.class);
//
//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                "http://REVIEWMS:8083/reviews/getallreviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                }
//        );
//        List<Review> reviews = reviewResponse.getBody();

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        return JobMapper.mapToJobDTO(job, company, reviews);

    }
}
