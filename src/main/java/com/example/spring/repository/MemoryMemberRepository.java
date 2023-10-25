package com.example.spring.repository;

import com.example.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) { //새로운 멤버가 등록되면
        member.setId(++sequence); //sequence num을 1올려줘서 저장한다
        store.put(member.getId(), member); //id의 값과 멤버 객체를 넣어서 저장한다.
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //map에서 루프를 돌면서
                .filter(member -> member.getName().equals(name)) //member에 저장 된 name이랑 매개변수로 들어온 name이 같으면
                .findAny(); //필터링을 거쳐 출력된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
