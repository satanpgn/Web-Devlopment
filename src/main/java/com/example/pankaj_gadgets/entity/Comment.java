package com.example.pankaj_gadgets.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "comment_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;



}