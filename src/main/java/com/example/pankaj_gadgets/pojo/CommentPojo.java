package com.example.pankaj_gadgets.pojo;

import com.example.pankaj_gadgets.entity.Comment;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentPojo {
    private  Integer id;
    private  String comment;
    private  int user_id;
    private  int product_id;
    public CommentPojo(Comment comment){
        this.id=comment.getId();
        this.user_id=comment.getUser_id().getId();
        this.product_id=comment.getProduct_id().getId();
        this.comment=comment.getComment();
    }
}
