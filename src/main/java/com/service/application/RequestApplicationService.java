package com.service.application;

import com.dto.application.RequestApplicationDto;
import com.model.application.RequestApplication;
import com.repository.application.RequestApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestApplicationService {

    private final RequestApplicationRepository repository;

    public List<RequestApplicationDto> listAll() {
        return repository.listAllWithDto();
    }

    public void save(RequestApplication dto) {
        repository.save(dto);
    }


    public RequestApplication findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public String send(RequestApplicationDto dto) {
        RequestApplication requestApplication = new RequestApplication();
        requestApplication.setFullName(dto.getFullName());
        requestApplication.setPhone(dto.getPhone());
        save(requestApplication);
        return "success";
    }


}
