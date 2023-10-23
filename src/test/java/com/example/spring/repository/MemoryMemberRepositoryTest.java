package com.example.spring.repository;

import com.example.spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    } //메서드가 끝날때마다 동작하는 일종의 콜백함수

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result =  repository.findById(member.getId()).get();
        //System.out.println("result = "+(result == member));
        //Assertions.assertEquals(member, result); 기대하는 값을 쓰면 성공이 되고 예를 들어 (member, null)을 쓰면 기대한 값이 아니라는 에러 메세지가 뜬다) 방향이 다른거일수도
        assertThat(member).isEqualTo(result); //Assertions는 static으로 사용해서 assertThat만 써도 사용할 수 있다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
