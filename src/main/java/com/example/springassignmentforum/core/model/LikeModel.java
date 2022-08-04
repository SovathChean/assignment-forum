package com.example.springassignmentforum.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Likes")
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = UserModel.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private UserModel users;
    @ManyToOne(targetEntity =  PostModel.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_id")
    private PostModel posts;
    private Boolean isLike;
    @Column(name="createdAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
