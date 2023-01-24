package Spring_Study.repository;


import java.util.List;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;



import Spring_Study.Spring_Study.domain.Member;
import Spring_Study.Spring_Study.repository.MemoryMemberRepository;

public class MemoryMemberRepositoryTest {
	 MemoryMemberRepository repository = new MemoryMemberRepository();

	 @AfterEach// 한 클래스 끝날떄 마다 레포지토리 초기화 해준다
	 public void afterEeah(){
		 repository.clearStore();
	 }

	 @Test
	 public void save() {
		 Member member = new Member();
		 member.setName("spring");

		 repository.save(member);

		 Member result = repository.findById(member.getId()).get(); // get 으로 꺼내와야 한다

		 Assertions.assertThat(member).isEqualTo(result);
	 }

	 @Test
	public void findByName() {
		 Member member1 = new Member();
		 member1.setName("spring1");
		 repository.save(member1);

		 Member member2 = new Member();
		 member2.setName("spring2");
		 repository.save(member2);

		 Member result = repository.findByName("spring1").get(); //원래 옵셔널로 받아줘야하는데 get으로 optional 한번 까서 겟해줌

		 Assertions.assertThat(result).isEqualTo(member1);

	 }
	 @Test
	public void findAll() {
		 Member member1 = new Member();
		 member1.setName("spring1");
		 repository.save(member1);

		 Member member2 = new Member();
		 member2.setName("spring2");
		 repository.save(member2);

		 List<Member> result = repository.findAll();

		 Assertions.assertThat(result.size()).isEqualTo(2); // 전체 테스트에서 오류가 나는 이유는 ?? 순서가 보장이 안된다. 그럼 앞에서 Findall이 먼저 실행돠면 다른 객체가 튀어나옴 그럼
		 // 어떻게 하냐? 한 클래스 테스트가 끝날때마다 clear해주면됨 레포지토리를
	 }

}
