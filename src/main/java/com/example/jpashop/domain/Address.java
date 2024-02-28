package com.example.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/*
JPA 스펙상 엔티티나 임베디드 타입( @Embeddable )은 자바 기본 생성자(default constructor)를 public 또는 protected 로 설정해야 한다.
public 으로 두는 것 보다는 protected 로 설정하는 것이 그나마 더 안전하다.
JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.
 */
@Embeddable // jpa 내장 타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // reflection, proxy 같은 기술을 써야 될때가 많다. 기본 생성자가 없으면 못하기에 필요하다.
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
