package Spring_Study.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import Spring_Study.domain.Member;

public class JdbcTemplateMemberRepository implements MemberRepository{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Member save(Member member) {
		return null;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public Optional<Member> findByName(String name) {
		return Optional.empty();
	}

	@Override
	public List<Member> findAll() {
		return null;
	}
}