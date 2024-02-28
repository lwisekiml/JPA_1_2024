package com.example.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A") // 선언하지 않으면 클래스 이름이 기본값으로 들어가게 된다.
@Getter @Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
