package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project07.dto.supplement.SupplementRegisterRequest;
import ssafy.project07.service.SupplementService;

@RestController
@RequestMapping("/api/supplements")
@RequiredArgsConstructor
public class SupplementController {
    private final SupplementService supplementService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerSupplement(@RequestBody SupplementRegisterRequest request) {
        supplementService.registerSupplement(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
