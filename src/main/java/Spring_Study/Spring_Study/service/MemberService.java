package Spring_Study.Spring_Study.service;

import java.util.List;
import java.util.Optional;

import Spring_Study.Spring_Study.domain.Member;
import Spring_Study.Spring_Study.repository.MemberRepository;
import Spring_Study.Spring_Study.repository.MemoryMemberRepository;

public class MemberService {
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	//회원가입
	public Long join(Member member) {
		// 같은 이름 중복 회원x
		validateDuplicateMember(member); // 옵셔널이기 때문에 ifpresent 쓸 수 있음 왜냐면 null일 경우에 optional

		memberRepository.save(member);
		return member.getId();
	} // command + t 하면 함수 뽑아줌

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())// commaand option v 하면 알아서 반환해줌 옵셔널로
			.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		});
	}
	//전체 회원 조회
	public List<Member> findMembers(){
			return  memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
