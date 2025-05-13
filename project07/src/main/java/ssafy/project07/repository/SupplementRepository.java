package ssafy.project07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Supplement;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
    // save, findById, findAll, delete, count 기본 기능임
    // Long 자리는 @Id 가 붙은 녀석의 타입을 쓴느것임
    // 그 앞 자리는 어떤 데이터를 넣을건지 !
}
