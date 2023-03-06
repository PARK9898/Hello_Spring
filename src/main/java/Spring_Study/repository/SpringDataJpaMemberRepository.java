package Spring_Study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring_Study.domain.Member;
//이렇게 해놓으면 스프링 데이터 jpa가 구현체를 알아서 만들어서 구현체를 만들어낸다. 알아서 구현체를 스프링 빈에 올려준다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
	@Override
	Optional<Member> findByName(String name); // id로 찾을수 있는 공통화 되어있는것 뺴고는 설정해주어야 함
	// 이렇게 설정하면 select m from Member m where m.name = ? 이 쿼리가 날라간다 인터페이스 이름으로만 개발이 끝난다
}
