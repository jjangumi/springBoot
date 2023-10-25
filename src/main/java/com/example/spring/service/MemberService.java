package com.example.spring.service;

import com.example.spring.domain.Member;
import com.example.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); 같은 repository를 참조하지않는다 그래서 이걸 맞추기 위해

    private final MemberRepository memberRepository;

    @Autowired
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
/*      Optional<Member> result = memberRepository.findByName(member.getName()); null이 있을 수 있다면 Optional로 감싸서 Optional안에 Member 객체가 있음으로 Optional안에 여러 메서드를 사용할 수 있다
        result.ifPresent(member1 -> { //멤버에 값이 있다면
            throw new IllegalStateException("이미 존재하는 회원입니다."); 이미 존재한다고 출력한다.
        });
*/
        memberRepository.findByName(member.getName()) //이미 이 구문자체가 Optional가 반환 타입이기에 가능하다.
                 .ifPresent(member1 -> {
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
