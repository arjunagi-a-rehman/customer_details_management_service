package com.arjunagi.customer_details_management_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"occupation", "dob", "customer_group"})
})
@EntityListeners(AuditingEntityListener.class)
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    private LocalDate dob;
    @Enumerated(value = EnumType.STRING)
    private Occupation occupation;
    @Enumerated(value = EnumType.STRING)
    private CustomerGroup customerGroup;
    @Column(updatable = false)

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;
}
