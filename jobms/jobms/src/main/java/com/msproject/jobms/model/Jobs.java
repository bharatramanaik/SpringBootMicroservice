package com.msproject.jobms.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "job_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;

}
