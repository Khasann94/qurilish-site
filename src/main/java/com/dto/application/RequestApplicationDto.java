package com.dto.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.model.application.RequestApplication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RequestApplicationDto {

    private Long id;
    private String fullName;
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Tashkent")
    private Date date;


    public RequestApplicationDto(RequestApplication application) {
        if (application != null) {
            setId(application.getId());
            setFullName(application.getFullName());
            setPhone(application.getPhone());
            setDate(application.getCreated());
        }
    }
}
