package com.example.spring.service;

import com.example.spring.domain.Member;
import com.example.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); 같은 repository를 참조하지않는다 그래서 이걸 맞추기 위해

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //직접 new로 생성하는것이 아니라 외부에 넣어주도록 변경한다.
    }


    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X

        //Optional와 같지만 다른 버전
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        memberRepository.findByName(member.getName()) //이미 이 구문자체가 Optional가 반환 타입이기에 가능하다.
                 .ifPresent(member1 ->  {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
