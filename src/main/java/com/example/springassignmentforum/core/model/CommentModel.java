package com.example.springassignmentforum.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="Comments")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_id")
    private PostModel posts;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private UserModel users;
    @OneToOne(orphanRemoval = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parent_id")
    private CommentModel parent;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="updatedAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
