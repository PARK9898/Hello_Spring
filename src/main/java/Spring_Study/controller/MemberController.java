package Spring_Study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Spring_Study.domain.Member;
import Spring_Study.service.MemberService;

@Controller // 이 어노테이션이 있으면 membercontroller 객체를 생성해서 스프링 컨테이너에 넣어둔다. // 컨포넌트 스캔임 ! Controller 안에 컨포넌트 어노테이션 설정이 되어있음
			// 컨포넌트로 di를 할 수도 있고 자바 코드로 직접 빈을 등록할 수 있음 이거는 스프링 컨테이너에 등록하는것임

public class MemberController { //멤버 컨트롤러가 멤버 서비스에 의존한다
	 private final MemberService memberService; // 앞에다 @Autowired 붙이면 필드 의존성 주입 (별로 안좋음) final로 변경 못하게 한다 한 번 가져와서 쓰면 멤버 서비스를 다른 걸로 교체 불가하게 만들어버림

	// @Autowired
	// public void setMemberService(MemberService memberService) {
	// 	this.memberService = memberService;
	// } // setter di 방법 단점은 setmemberservice 가 변경 될 수 있다 한 번 세팅이 되면 바뀔일이 없다

	@Autowired // 이거 해주면 스프링컨테이너에있는 memberservice를 가져다가 연결을 시켜준다 그래서 memberservice 자체를 스프링 컨테이너에 넣어줘야한다 의존성 주입 선을 연결
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	} // di 생성사 주입
	@GetMapping("/members/new")
	public String createForm(){
		return "members/createMemberForm";
	}
	@PostMapping("/members/new")
	public String create(MemberForm form){
		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member);

		return "redirect:/";
	}

	@GetMapping("/members")
	public String list(Model model){
		List<Member> memebers = memberService.findMembers();
		model.addAttribute("members",memebers);
		return "members/memberList";
	}
	//GET 방식 : 어떠한 정보를 가져와서 조회하기 위해 사용되는 방식
	//POST 방식: 데이터를 서버로 제출하여 추가 또는 수정하기 위해서 데이터를 전송하는 방식
}
