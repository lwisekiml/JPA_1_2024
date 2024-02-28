package com.example.jpashop.repository;

import com.example.jpashop.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // jpa에 저장하기 전까지 id가 없다. 즉 id가 없다는 것은 완전히 새로 생성한 객체, 신규등록하는 것이다.
            em.persist(item);
        } else { // DB에 이미 있어서 update 해준다.
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
