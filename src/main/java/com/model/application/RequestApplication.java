package com.model.application;


import com.model.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "request_application")
@EqualsAndHashCode(callSuper = true)
public class RequestApplication extends Auditable<String> {

    private String fullName;
    private String phone;

}
