package com.example.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M") // 선언하지 않으면 클래스 이름이 기본값으로 들어가게 된다.
@Getter @Setter
public class Movie extends Item {

    private String director;
    private String actor;
}
