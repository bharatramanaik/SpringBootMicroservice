package com.msproject.companyms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {
    private String message;
    private CompanyDTO data;
}
