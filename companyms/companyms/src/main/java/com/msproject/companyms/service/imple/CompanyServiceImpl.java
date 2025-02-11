package com.msproject.companyms.service.imple;

import com.msproject.companyms.clients.JobClient;
import com.msproject.companyms.clients.ReviewClient;
import com.msproject.companyms.dto.CompanyDTO;
import com.msproject.companyms.dto.CompanyResponse;
import com.msproject.companyms.dto.MessageResponse;
import com.msproject.companyms.dto.ReviewMessage;
import com.msproject.companyms.external.Jobs;
import com.msproject.companyms.external.Review;
import com.msproject.companyms.mapper.CompanyMapper;
import com.msproject.companyms.model.Company;
import com.msproject.companyms.repository.CompanyRepository;
import com.msproject.companyms.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    private JobClient jobClient;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient, JobClient jobClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
        this.jobClient = jobClient;
    }

    @Override
    public ResponseEntity<List<CompanyDTO>> findAll() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOs = companies.stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(companyDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> createCompany(Company company) {
        companyRepository.save(company);
        return new ResponseEntity<>(
                CompanyMapper.mapToMessageResponse("Company created", HttpStatus.CREATED.value())
                , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CompanyDTO> getCompanyById(Long id) {

        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        CompanyDTO companyDTO = convertToDTO(company);
        CompanyResponse companyResponse = CompanyMapper.mapToCompanyResponse(companyDTO, "Company " + id, HttpStatus.OK.value());
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<MessageResponse> deleteCompanyById(Long companyId) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
            );
            companyRepository.delete(company);
            deleteJobsAndReviews(companyId);

            return new ResponseEntity<>(
                    CompanyMapper.mapToMessageResponse("Company deleted", HttpStatus.OK.value())
                    , HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(
                    CompanyMapper.mapToMessageResponse("Company not exists", HttpStatus.NOT_FOUND.value())
                    , HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateCompany(Company company) {
        try {
            Long companyId = company.getCompanyId();
            Company oldcompany = companyRepository.findById(companyId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
            );
            companyRepository.save(company);
            return new ResponseEntity<>(
                    CompanyMapper.mapToMessageResponse("Company updated", HttpStatus.OK.value())
                    , HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    CompanyMapper.mapToMessageResponse("Company not exists", HttpStatus.OK.value())
                    , HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company oldcompany = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        Double avgRating = reviewClient.getAverageRating(reviewMessage.getCompanyId());
        if (avgRating == 0){
            oldcompany.setRating(reviewMessage.getRating());
        }else {
            oldcompany.setRating(avgRating);
        }
        companyRepository.save(oldcompany);

    }

    private CompanyDTO convertToDTO(Company company){
//        ResponseEntity<List<Jobs>> jobresponse = restTemplate.exchange(
//                "http://JOBMS:8082/jobs/getjobsofonecompany?companyId=" + company.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Jobs>>() {
//                }
//        );
//        ResponseEntity<List<Review>> reviewresponse = restTemplate.exchange(
//                "http://REVIEWMS:8083/reviews/getallreviews?companyId=" + company.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                }
//        );

        List<Jobs> jobsList = jobClient.getJobs(company.getCompanyId());
        List<Review> reviewList = reviewClient.getReview(company.getCompanyId());

        return CompanyMapper.mapToCompanyDTO(company, jobsList, reviewList);
    }


    private void deleteJobsAndReviews(Long companyId){
        List<Jobs> jobsList = jobClient.getJobs(companyId);
        List<Review> reviewList = reviewClient.getReview(companyId);
        for (Jobs job: jobsList) {
            jobClient.deleteJobs(job.getId());
        }

        for (Review review: reviewList){
            reviewClient.deleteReviews(review.getId());
        }

    }

}
