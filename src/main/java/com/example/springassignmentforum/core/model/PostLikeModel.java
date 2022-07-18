package com.example.springassignmentforum.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="PostLikes")
public class PostLikeModel {
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    private PostModel post;
    private Integer likes;
}
