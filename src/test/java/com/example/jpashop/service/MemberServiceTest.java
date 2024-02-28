package com.example.jpashop.service;

import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.MemberRepository;
import jakarta.activation.MailcapRegistry;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @RunWith(SpringRunner.class)
 * @SpringBootTest
 * 스프링과 인티게이션해서 스프링 부트를 실제 올려 테스트 하기위함
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 데이터 변경으로 인해 롤백이 되도록 함
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
//    @Autowired EntityManager em; // 2.

    @Test
//    @Rollback(value = false) // 1. 롤백 안함
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);

        // then
//        em.flush(); // 2. insert 쿼리는 볼 수 있으나 롤백 함
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class) // try-catch문이 없어도 된다.
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
//        try {
            memberService.join(member2);
//        } catch (IllegalStateException e) {
//            return;
//        }

        // then
        fail("예외가 발생해야 한다.");
    }

}