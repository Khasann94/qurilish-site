package com.dto.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.model.support.Language;
import com.util.Locale;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    private Long id;
    private Locale localeName;
    private String name;
    private String code;

    public LanguageDto(Language model) {
        if (model.getId() != null) setId(model.getId());
        if (model.getName() != null) {
            setLocaleName(model.getName());
            setName(model.getName().locale());
        }
        setCode(model.getCode());
    }

    public LanguageDto(Language model, Boolean t) {
        if (model.getId() != null) setId(model.getId());
        if (model.getName() != null) {
            setName(model.getName().locale());
            setLocaleName(model.getName());
        }
        setCode(model.getCode());
    }

    public static LanguageDto toDto(Language model) {
        if (model == null) return null;
        return LanguageDto.builder()
                .id(model.getId())
                .localeName(model.getName())
                .code(model.getCode())
                .build();
    }
}
