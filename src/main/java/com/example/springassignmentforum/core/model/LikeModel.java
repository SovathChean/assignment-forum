package com.example.springassignmentforum.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Likes")
public class LikeModel {
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    private UserModel user;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    private PostModel post;
    private boolean like;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
