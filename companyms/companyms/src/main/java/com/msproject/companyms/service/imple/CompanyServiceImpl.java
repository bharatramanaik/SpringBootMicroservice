package com.msproject.companyms.service.imple;

import com.msproject.companyms.clients.JobClient;
import com.msproject.companyms.clients.ReviewClient;
import com.msproject.companyms.dto.CompanyDTO;
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

import java.util.ArrayList;
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
        return new ResponseEntity<>(companyDTOs,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createCompany(Company company) {
        companyRepository.save(company);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CompanyDTO> getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        CompanyDTO companyDTO = convertToDTO(company);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        companyRepository.delete(company);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCompany(Company company) {
        Long compid = company.getCompanyId();
        Company oldcompany = companyRepository.findById(compid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found")
        );
        companyRepository.save(company);
        return new ResponseEntity<>("updated", HttpStatus.OK);
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
}
