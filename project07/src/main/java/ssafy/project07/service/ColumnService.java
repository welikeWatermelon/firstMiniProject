package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Column;
import ssafy.project07.domain.Pharmacist;
import ssafy.project07.dto.column.ColumnRequest;
import ssafy.project07.dto.column.ColumnResponse;
import ssafy.project07.repository.column.ColumnRepository;
import ssafy.project07.repository.pharmacist.PharmacistRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final PharmacistRepository pharmacistRepository;

    public List<ColumnResponse> getColumnsByPharmacist(Long pharmacistId) {
        return columnRepository.findByPharmacistId(pharmacistId).stream().map(col -> {
            ColumnResponse res = new ColumnResponse();
            res.setId(col.getId());
            res.setTitle(col.getTitle());
            res.setContent(col.getContent());
            res.setCreatedAt(col.getCreatedAt());
            res.setPharmacistName(col.getPharmacist().getName());
            return res;
        }).collect(Collectors.toList());
    }

    public Long create(ColumnRequest request) {
        Pharmacist pharmacist = pharmacistRepository.findById(request.getPharmacistId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 약사입니다."));

        Column column = new Column();
        column.setTitle(request.getTitle());
        column.setContent(request.getContent());
        column.setCreatedAt(LocalDateTime.now());
        column.setPharmacist(pharmacist);

        return columnRepository.save(column).getId();
    }

    public void update(Long columnId, ColumnRequest request) {
        Column column = columnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));
        column.setTitle(request.getTitle());
        column.setContent(request.getContent());
        columnRepository.save(column);
    }

    public void delete(Long columnId) {
        columnRepository.deleteById(columnId);
    }
}
