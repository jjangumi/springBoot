package com.example.spring;

import com.example.spring.aop.TimeTraceAop;
import com.example.spring.repository.*;
import com.example.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

    /*@Bean
    public TimeTraceAop timeTraceAop(){
        return  new TimeTraceAop();
    }*/

    //@Bean
    //public MemberRepository memberRepository(){

        //return new MemoryMemberRepository(); 원래 메모리에 저장해놨던걸 구현체만 바꿔 설정해 데이터베이스에 저장한다.
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository<M, Number>(em);
   // }
}
