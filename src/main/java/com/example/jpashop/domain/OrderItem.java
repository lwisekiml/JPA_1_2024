package com.example.jpashop.domain;

import com.example.jpashop.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id") // 외래키 이름이 order_id가 된다.
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량
}
