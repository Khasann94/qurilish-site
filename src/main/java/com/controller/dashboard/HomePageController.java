package com.controller.dashboard;


import com.dto.dashboard.HomePageDto;
import com.service.dashboard.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v.1/home-page/")
public class HomePageController {

    private final HomePageService service;

    @GetMapping("list")
    public ResponseEntity<List<HomePageDto>> list() {
        return new ResponseEntity<>(service.list(), OK);
    }
    
    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody HomePageDto form) {
        return new ResponseEntity<>(service.create(form), OK);
    }

    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody HomePageDto form) {
        return new ResponseEntity<>(service.update(form), OK);
    }

    @PostMapping("add-photo")
    public ResponseEntity<String> addPhoto(@RequestParam Long pageId,
                                           @RequestParam MultipartFile[] files) {
        return new ResponseEntity<>(service.addPhoto(pageId, files), OK);
    }

    @DeleteMapping("delete-photo")
    public ResponseEntity<String> deletePhoto(@RequestParam Long photoId) {
        return new ResponseEntity<>(service.deletePhoto(photoId), OK);
    }


}
