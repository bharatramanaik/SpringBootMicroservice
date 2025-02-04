package com.msproject.reviewms.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Entity
@Table(name = "review_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;

}
