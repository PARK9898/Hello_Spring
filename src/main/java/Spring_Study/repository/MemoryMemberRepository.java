package Spring_Study.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Spring_Study.domain.Member;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>(); // save 할 때 어딘가 저장을 해놔야 되니까 map 사용
	private static Long sequence = 0L;

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(),member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id)); // null로 반활될 가능성이 있으면 이와같이 optional 로 감싸서 처리해준다
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny(); //  stream()이 자동으로 optional로 감싸서 처리해주는지 알아보기
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
	}
}
