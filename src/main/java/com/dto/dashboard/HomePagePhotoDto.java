package com.dto.dashboard;

import com.model.dashboard.HomePagePhoto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HomePagePhotoDto {

    private Long id;
    private String uploadPath;
    private String extension;
    private String name;

    public HomePagePhotoDto(HomePagePhoto file) {
        if (file.getId() != null)
            setId(file.getId());
        setUploadPath(file.getUploadPath());
        setExtension(file.getPhotoExtension());
        setName(file.getPhotoName());
    }
}
