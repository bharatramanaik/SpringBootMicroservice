package com.msproject.companyms.model;


import com.msproject.companyms.external.Jobs;
import com.msproject.companyms.external.Review;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

import java.util.List;

@Data
@Entity
@Table(name = "company_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String name;
    private String description;


}
