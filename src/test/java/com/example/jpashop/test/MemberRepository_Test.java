//package com.example.jpashop.test;
//
//import com.example.jpashop.test.MemberRepository_;
//import com.example.jpashop.test.Member_;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MemberRepository_Test {
//
//    @Autowired
//    MemberRepository_ memberRepository;
//
//    @Test
//    @Transactional // 엔티티 매니저를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이루어져야 하므로 필요
//    @Rollback(value = false) // @Test는 기본적으로 롤백이다. false로 설정하여 롤백을 안 하도록 함
//    public void testMember() {
//        // given
//        Member_ member = new Member_();
//        member.setUsername("memberA");
//
//        // when
//        Long saveId = memberRepository.save(member);
//        Member_ findMember = memberRepository.find(saveId);
//
//        // then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
//}