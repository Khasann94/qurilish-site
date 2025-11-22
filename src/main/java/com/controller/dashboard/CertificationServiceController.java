package com.controller.dashboard;


import com.dto.dashboard.CertificationServiceDto;
import com.service.dashboard.CertificationServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v.1/certificate/")
public class CertificationServiceController {

    private final CertificationServiceService service;

    @GetMapping("list")
    public ResponseEntity<List<CertificationServiceDto>> list() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody CertificationServiceDto form) {
        return new ResponseEntity<>(service.create(form), OK);
    }

    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody CertificationServiceDto form) {
        return new ResponseEntity<>(service.update(form), OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), OK);
    }
}
