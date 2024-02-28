package com.example.jpashop.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository_ {

    @PersistenceContext
    EntityManager em;

    /**
     * 멤법를 반환하지 않는 이유
     * 커맨드와 쿼리를 분리하라 라는 원칙에 의해 저장을 하고 나면 가급적이면
     * 이것은 사이드 이펙트를 일으키는 커맨드이기 때문에 리턴값을 거의 안 만든다.
     * 대신 아이디가 있으면 다음에 다시 조회할 수 있어서 아이디 정도만 리턴한다.
     */
    public Long save(Member_ menber) {
        em.persist(menber);
        return menber.getId();
    }

    public Member_ find(Long id) {
        return em.find(Member_.class, id);
    }
}
