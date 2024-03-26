package com.example.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // 지연 로딩이여서 DB에서 안 끌고 온다. 그래서 order를 호출할 때 멤버를 상속받아 proxy 멤법 객체를 생성해서 넣어둔다.
    @JoinColumn(name = "member_id") // 외래키 이름이 member_id 가 된다.
    private Member member; // Order가 Member와의 연관관계 주인이다. 값을 세팅하면 member_id 외래키 값이 다른 멤버로 변경된다.

    // cascade
    // 모든 엔티티는 기본적으로 persist를 다 저장하고 싶으면 각자 해주어야 하는데
    // delivery 에 값만 세팅을 해두고 Order만 persist하면 delivery도 같이 persist가 호출 된다.
    // cascade는 예를 들어서 부모 엔티티를 저장할 때 자식엔티티도 한번에 함께 저장하는 기능
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //==연관관계 메서드(양쪽 세팅을 원자적으로 한 코드로 해결할 수 있어 양방향 일때 쓰면 좋다.)==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    // 원래는 아래와 같이 해야 하지만 위와 같이 메서드를 만들면
    // 아래 코드에서 member.getOrders().add(order); 부분은 없어도 된다.
//    public static void main(String[] args) {
//        Member member = new Member();
//        Order order = new Order();
//
//        member.getOrders().add(order);
//        order.setMember(member);
//    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();

//        int totalPrice = 0;
//        for (OrderItem orderItem : orderItems) {
//            totalPrice += orderItem.getTotalPrice();
//        }
//        return totalPrice;
    }
}
