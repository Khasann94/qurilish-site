package com.controller.support;

import com.dto.support.LanguageDto;
import com.service.support.LanguageService;
import com.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v.1/language/")
public class LanguageController {

    private final LanguageService service;

    @GetMapping("list-all")
    public ResponseEntity<List<LanguageDto>> listAllDto() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

    @PostMapping("list-search")
    public ResponseEntity<Map<String, Object>> list(@RequestParam(required = false) String search, @RequestBody Pagination pagination) {
        return new ResponseEntity<>(service.list(search, pagination), OK);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<LanguageDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new LanguageDto(service.findById(id)), OK);
    }

    @GetMapping("get-by-code")
    public ResponseEntity<LanguageDto> findByCode(@RequestParam String code) {
        return new ResponseEntity<>(new LanguageDto(service.findByCode(code)), OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody LanguageDto form) {
        return ResponseEntity.ok(service.create(form));
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody LanguageDto form) {
        return ResponseEntity.ok(service.update(form));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
