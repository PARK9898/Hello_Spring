package Spring_Study.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Spring_Study.domain.Member;
import Spring_Study.repository.MemoryMemberRepository;

public class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach(){
		memberRepository.clearStore();
	}

	@Test
	void 회원가입(){
	//given 이게 주어졌을때 (데이터)
	Member member = new Member();
	member.setName("hello");

	//when 언제 (검증하는것)
	Long saveId = memberService.join(member);


	//then 이렇게 된다
	Member findmember = memberService.findOne(saveId).get();
	assertThat(member.getName()).isEqualTo(findmember.getName()); // static import 로 들어가게 해주어야함
	}

	@Test
	public void 중복회원테스트(){
		//given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");
		//when
		 memberService.join(member1);
		//then
		/*try{
			memberService.join(member2);
			fail("예외가 발생해야합니다");
		}catch (IllegalStateException e){
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}*/
		IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(
			IllegalStateException.class, () -> memberService.join(member2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
	}
	@Test
	void findMembers(){


	}

	@Test
	void findOne(){

	}
}
