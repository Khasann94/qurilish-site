package com.model.support;

import com.model.Auditable;
import com.util.Locale;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "languages", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class Language extends Auditable<String> {

    @Embedded
    @Column(name = "name")
    private Locale name;

    @Column(name = "code")
    private String code;

    @Column(name = "is_mob")
    private Boolean isMob = Boolean.FALSE;
}
