package com.dto.dashboard;

import com.model.dashboard.HomePage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HomePageDto {

    private Long id;
    private String aboutRu;
    private String aboutUz;
    private String phone;
    private String address;
    private String titleUz;
    private String titleRu;
    private String textUz;
    private String textRu;

    private List<HomePagePhotoDto> photos = new ArrayList<>();

    public HomePageDto(HomePage homePage) {
        if (homePage != null) {
            setId(homePage.getId());
            setAboutRu(homePage.getAboutRu());
            setAboutUz(homePage.getAboutUz());
            setAddress(homePage.getAddress());
            setPhone(homePage.getPhone());
            setTitleUz(homePage.getTitleUz());
            setTitleRu(homePage.getTitleRu());
            setTextUz(homePage.getTextUz());
            setTextRu(homePage.getTextRu());
        }
    }
}
