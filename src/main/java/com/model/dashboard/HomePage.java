package com.model.dashboard;

import com.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class HomePage extends Auditable<String> {

    @Column(name = "about_ru")
    private String aboutRu;
    @Column(name = "about_uz")
    private String aboutUz;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "title_uz")
    private String titleUz;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "text_uz")
    private String textUz;

    @Column(name = "text_ru")
    private String textRu;
}
