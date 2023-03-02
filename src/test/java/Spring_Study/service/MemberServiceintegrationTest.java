package Spring_Study.service;

import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import Spring_Study.domain.Member;
import Spring_Study.repository.MemberRepository;

@SpringBootTest // 스프링 컨테이너와 테스트 함께 실행
@Transactional // 테스트 케이스에 있으면(테스트 케이스의 경우에만) 테스트 케이스 실행 후 롤백이 된다.
public class MemberServiceintegrationTest {

	@Autowired MemberService memberService; //테스트할때는 그냥 필드주입 해도 됨
	@Autowired MemberRepository memberRepository;



	@Test
	void 회원가입(){
	//given 이게 주어졌을때 (데이터)
	Member member = new Member();
	member.setName("spring");

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
// 스프링 단위테스트 (스프링 통합테스트 x)가 좋은 테스트 왜냐하면 단위테스트가 엄청 빨리 끝내기 떄문이다.
}
