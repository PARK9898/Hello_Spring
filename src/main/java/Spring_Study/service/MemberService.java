package Spring_Study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import Spring_Study.domain.Member;
import Spring_Study.repository.MemberRepository;

//@Service
@Transactional // 데이터 저장이나 변경할 때에는 Transcational 필다
public class MemberService { // commadn shift t 누르면 테스트 자동으로 만들어줌
	private final MemberRepository memberRepository;

	//@Autowired 여기서 Autowired 쓰는 이유는 멤버 서비스 측에서 멤버 리포지토리를 필요로 하기 떄문임
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	} // 생성자 command + n 외부에서 멤버 리포지토리를 넣어주도록 함 // 의존성주입

	//회원가입
	public Long join(Member member) {
		// 같은 이름 중복 회원x
		long start = System.currentTimeMillis();
		try {
			validateDuplicateMember(member); // 옵셔널이기 때문에 ifpresent 쓸 수 있음 왜냐면 null일 경우에 optional
			memberRepository.save(member);
			return member.getId();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("join = " + timeMs + "ms" );
		}
	} // command + t 하면 함수 뽑아줌

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())// commaand option v 하면 알아서 반환해줌 옵셔널로
			.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		});
	}
	//전체 회원 조회
	public List<Member> findMembers(){
		long start = System.currentTimeMillis();
		try {
			return  memberRepository.findAll();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("findMembers = " + timeMs + "ms" );
		}

	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
