package com.example.jpashop.domain.item;

import com.example.jpashop.domain.Category;
import com.example.jpashop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

//@BatchSize(size = 100) // 컬렉션이 아닌데 적용하고 싶은 경우 여기에 적는다.
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 싱글 테이블 전략
@DiscriminatorColumn(name = "dtype") // 상속 관계 맵핑을 위함
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
