package com.controller.document;


import com.dto.document.DocumentFileDto;
import com.service.document.DocumentFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v.1/document-file/")
public class DocumentFileController {

    private final DocumentFileService service;

    @GetMapping("list")
    public ResponseEntity<List<DocumentFileDto>> listAllDto() {
        return new ResponseEntity<>(service.listAll(), OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestParam String name,
                                         @RequestParam MultipartFile file) {
        return ResponseEntity.ok(service.create(name, file));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
