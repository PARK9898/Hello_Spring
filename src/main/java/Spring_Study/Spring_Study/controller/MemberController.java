package Spring_Study.Spring_Study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Spring_Study.Spring_Study.service.MemberService;

@Controller // 이 어노테이션이 있으면 membercontroller 객체를 생성해서 스프링 컨테이너에 넣어둔다. // 컨포넌트 스캔임 ! Controller 안에 컨포넌트 어노테이션 설정이 되어있음
			// 컨포넌트로 di를 할 수도 있고 자바 코드로 직접 빈을 등록할 수 있음 이거는 스프링 컨테이너에 등록하는것임 
public class MemberController { //멤버 컨트롤러가 멤버 서비스에 의존한다
	private final MemberService memberService;

	@Autowired // 이거 해주면 스프링컨테이너에있는 memberservice를 가져다가 연결을 시켜준다 그래서 memberservice 자체를 스프링 컨테이너에 넣어줘야한다 의존성 주입 선을 연결
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
}
