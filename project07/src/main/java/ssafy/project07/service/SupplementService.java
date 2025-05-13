package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Supplement;
import ssafy.project07.dto.supplement.SupplementRegisterRequest;
import ssafy.project07.repository.SupplementRepository;

@Service
@RequiredArgsConstructor
public class SupplementService {

    // 레포지토리 생성
    private final SupplementRepository supplementRepository;

    // 파라미터 안에 dto 넣기
    // dto의 역할은 어떤 데이터가 넘어오는지 받는 것
    // 요청과 응답 데이터를 전달하는 순수 데이터 전용 객체
    // 즉, 데이터는 dto 형태로 넘어오겠다 !!! 라는 것임
    // service 에서 쓸만한 데이터 형태가 필요한데, 그 데이터 형태를 dto에서 만드는 것임
    // 그 후 service 에서 레포지토리를 이용하여 저장이나 등등의 처리를 하는 것
    
    // 메서드 안에는 Entity(domain) 이 들어감
    // dto형태의 데이터를 입력받아서, Entity 값에 넣어주고, 그것을 레포지토리에 저장하는 과정임
    public void registerSupplement(SupplementRegisterRequest request) {
        Supplement supplement = new Supplement();
        supplement.setName(request.getName());
        supplement.setDoseMg(request.getDoseMg());
        supplement.setDailyLimit(request.getDailyLimit());
        supplement.setIngredients(request.getIngredients());

        // 레포지토리에 동작
        supplementRepository.save(supplement);
    }
}
