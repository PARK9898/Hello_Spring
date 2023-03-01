package Spring_Study.Spring_Study.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Spring_Study.Spring_Study.repository.MemberRepository;
import Spring_Study.Spring_Study.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {

	@Bean // 멤버 서비스를 스프링 빈에 직접 등록하는 어노테이션
	public MemberService memberService(){
		return new MemberService(memberRepository()); // 스프링 빈에 등록되어있는 멤버 리포지토리를 넣어줌 autowired랑 비슷함
	}
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
