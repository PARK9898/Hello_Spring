package Spring_Study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Spring_Study.aop.TimeTraceAop;
import Spring_Study.repository.JpaMemberRepository;
import Spring_Study.repository.MemberRepository;

@Configuration
public class SpringConfig {

	// private EntityManager em;
	// @Autowired
	// public SpringConfig(EntityManager em){
	// 	this.em = em;
	// }
	private final MemberRepository memberRepository;

	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean // 멤버 서비스를 스프링 빈에 직접 등록하는 어노테이션
	public MemberService memberService(){
		return new MemberService(memberRepository); // 스프링 빈에 등록되어있는 멤버 리포지토리를 넣어줌 autowired랑 비슷함
	}

	// @Bean
	// public MemberRepository memberRepository() {
	// 	return new JpaMemberRepository(em);
	// }
	//정형화된 컨트롤러 서비스 리포지토리 같은 경우는 컴포넌트 스캔을 사용하지만 정형화 되지 않거나 ㅏㅇ황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
	//예를 들면 memorymemberrepository가 디비가 선정이 되면 앞에 memory 부분만 고쳐줘도 동작함 이러한 점이 직접 빈에 등록할 때 유용한 점이라 할 수 있다
}
