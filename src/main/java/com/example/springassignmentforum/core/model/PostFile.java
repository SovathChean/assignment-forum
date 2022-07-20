package com.example.springassignmentforum.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="PostFiles")
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = PostModel.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    private PostModel post;
    @ManyToOne(targetEntity = FileModel.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    private FileModel file;
}
