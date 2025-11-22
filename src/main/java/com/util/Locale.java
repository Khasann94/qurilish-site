package com.util;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.context.i18n.LocaleContextHolder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Locale {
    private String ru;
    private String uz;

    public String locale() {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return "ru".equals(lang) ? ru : uz;
        }
}
