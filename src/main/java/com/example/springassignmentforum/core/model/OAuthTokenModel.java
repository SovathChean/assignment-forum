package com.example.springassignmentforum.core.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="OAuthTokens")
public class OAuthTokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false, unique = true)
    private String uniqueKey;
    private LocalDateTime requestAt;
}
