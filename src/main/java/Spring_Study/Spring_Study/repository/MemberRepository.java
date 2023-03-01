package Spring_Study.Spring_Study.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import Spring_Study.Spring_Study.domain.Member;
@Repository
public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id); // optional 을 null 을 처리하기 위한 기법임
	Optional<Member> findByName(String name);
	List<Member> findAll();

	
}
