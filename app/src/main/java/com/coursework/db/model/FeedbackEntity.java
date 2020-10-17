package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "feedback")
@NoArgsConstructor
public class FeedbackEntity extends BaseEntity {

    @Column(name = "text")
    String text;

    @Column(name = "rating")
    Double rating;

    @ManyToOne
    @JoinColumn(name = "parent_feedback_id")
    FeedbackEntity parentFeedback;

    @OneToMany(mappedBy = "parentFeedback", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FeedbackEntity> childFeedbackList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;
}
