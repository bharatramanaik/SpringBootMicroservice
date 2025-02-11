package com.msproject.reviewms.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private Long companyId;
    private String name;
    private String description;
    private Double rating;
}
