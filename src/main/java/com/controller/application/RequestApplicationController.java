package com.controller.application;


import com.dto.application.RequestApplicationDto;
import com.service.application.RequestApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v.1/request-application/")
public class RequestApplicationController {

    private final RequestApplicationService service;


    @GetMapping("list")
    public ResponseEntity<List<RequestApplicationDto>> list() {
        return new ResponseEntity<>(service.listAll(), OK);
    }


    @PostMapping("send")
    public ResponseEntity<String> send(@RequestBody RequestApplicationDto form) {
        return new ResponseEntity<>(service.send(form), OK);
    }
}
