package com.service.support;

import com.dto.support.LanguageDto;
import com.exception.RecordNotFoundException;
import com.model.support.Language;
import com.repository.support.LanguageRepository;
import com.util.Pagination;
import com.util.UtilComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository repository;

    public List<LanguageDto> listAll() {
        return repository.listAll();
    }

    public Map<String, Object> list(String search, Pagination pagination) {
        Page<LanguageDto> page = repository.findAllWithSearch(search != null && !search.isEmpty() ? search.toLowerCase() : "", PageRequest.of(pagination.getPage(), pagination.getLimit()));
        return UtilComponents.listMap(page);
    }

    public void save(Language model) {
        repository.save(model);
    }

    public Language findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Muvaffaqiyatli o'chirildi";
    }

    public synchronized String create(LanguageDto form) {
        if (form.getCode() == null || form.getCode().isEmpty())
            throw new RecordNotFoundException("Kod bo'sh bo'lishi mumkin emas!");
        Language language = repository.findByCode(form.getCode());
        if (language == null) {
            save(convertToModel(form, new Language()));
        }
        return "Muvaffaqiyatli yaratildi";
    }


    public String update(LanguageDto form) {
        if (form.getCode() == null || form.getCode().isEmpty()) throw new RecordNotFoundException("Invalid code...");
        save(convertToModel(form, repository.findByCode(form.getCode())));
        return "Muvaffaqiyatli tahrirlandi";
    }

    public Language convertToModel(LanguageDto form, Language model) {
        model.setName(form.getLocaleName());
        model.setCode(form.getCode());
        return model;
    }

    public Language findByCode(String code) {
        return repository.findByCode(code);
    }
}
