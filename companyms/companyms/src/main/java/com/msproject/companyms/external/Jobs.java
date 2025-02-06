package com.msproject.companyms.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jobs {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
}
