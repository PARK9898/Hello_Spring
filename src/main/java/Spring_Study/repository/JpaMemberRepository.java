package Spring_Study.repository;

import java.util.List;
import java.util.Optional;

import Spring_Study.domain.Member;
import jakarta.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository{
	private final EntityManager em; // jpa 는 entitiy manager로 모든 동작을 함 스프링 부트가 propertity와 db connection 정보랑 짬봉해서 em 을 만들어줌 db와 통신할때 필요 jpa 쓸려면 em 주입 받아야함

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member); // 영속하다 (영구 저장하다)
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		 Member member = em.find(Member.class,id);
		 return Optional.ofNullable(member);
	}
 	//pk로 하는것이 아닌것은 jpql 문 작성해주어야 함
	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select  m from Member m where m.name = :name",Member.class)
			.setParameter("name", name)
			.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m",Member.class).getResultList(); // command option n -> inline reSult = ... 를 바로 리턴해준다
	} // m 은 엔티디 자체를 select하는것임 이미 매핑이 다 되어있음
}
