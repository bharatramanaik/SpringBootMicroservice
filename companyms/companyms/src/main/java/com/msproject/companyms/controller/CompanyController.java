package com.msproject.companyms.controller;

import com.msproject.companyms.dto.CompanyDTO;
import com.msproject.companyms.dto.CompanyResponse;
import com.msproject.companyms.dto.MessageResponse;
import com.msproject.companyms.model.Company;
import com.msproject.companyms.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getallcompanies")
    public ResponseEntity<List<CompanyDTO>> getallcompanies(){
        return companyService.findAll();
    }

    @PostMapping("/createcompany")
    public ResponseEntity<MessageResponse> createjob(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @GetMapping("/getonecompany/{id}")
    public ResponseEntity<CompanyDTO> getcompanybyid(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @PutMapping("/updatecompany")
    public ResponseEntity<MessageResponse> updatecompany(@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/deletecompany/{id}")
    public ResponseEntity<MessageResponse> deletecompany(@PathVariable Long id){
        return companyService.deleteCompanyById(id);
    }



}
