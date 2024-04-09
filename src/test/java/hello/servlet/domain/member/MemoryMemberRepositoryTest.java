package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemoryMemberRepositoryTest {
   private MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();

   @AfterEach
   void afterEach(){
       memberRepository.clearStore();
   }

    @Test
    void saveMember(){
        //given
        Member member = new Member("테스트", 35);


        //when
        Member savedMember = memberRepository.save(member);


        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(savedMember.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void findALl(){
       //given
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> all = memberRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(member1,member2);
    }
}