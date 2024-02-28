package com.example.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Order에 있는 member에 매핑 되어진 진다. 그래서 여기값이 바뀌어도 외래키값이 변경되지 않는다.
    private List<Order> orders;
}
