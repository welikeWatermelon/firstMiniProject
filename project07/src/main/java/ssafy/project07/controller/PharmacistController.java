package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.dto.column.ColumnRequest;
import ssafy.project07.dto.column.ColumnResponse;
import ssafy.project07.dto.pharmacist.PharmacistResponse;
import ssafy.project07.service.ColumnService;
import ssafy.project07.service.PharmacistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pharmacists")
public class PharmacistController {

    private final PharmacistService pharmacistService;
    private final ColumnService columnService;

    @GetMapping
    public ResponseEntity<List<PharmacistResponse>> getAllPharmacists() {
        return ResponseEntity.ok(pharmacistService.getAllPharmacists());
    }

    @GetMapping("/{id}/columns")
    public ResponseEntity<List<ColumnResponse>> getColumnsByPharmacist(@PathVariable Long id) {
        return ResponseEntity.ok(columnService.getColumnsByPharmacist(id));
    }

    @PostMapping("/columns")
    public ResponseEntity<Long> createColumn(@RequestBody ColumnRequest request) {
        return ResponseEntity.ok(columnService.create(request));
    }

    @PutMapping("/columns/{id}")
    public ResponseEntity<Void> updateColumn(@PathVariable Long id, @RequestBody ColumnRequest request) {
        columnService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/columns/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Long id) {
        columnService.delete(id);
        return ResponseEntity.ok().build();
    }
}
