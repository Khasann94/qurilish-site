package com.service.dashboard;

import com.dto.dashboard.HomePageDto;
import com.model.dashboard.HomePage;
import com.repository.dashboard.HomePageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final HomePageRepository repository;
    private final HomePagePhotoService photoService;

    public void save(HomePage page) {
        repository.save(page);
    }


    public HomePage getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public HomePageDto getDtoById(Long id) {
        HomePage page = getById(id);
        if (page == null) return null;
        HomePageDto dto = new HomePageDto(page);
        dto.setPhotos(photoService.getByPageId(id));
        return dto;
    }

    public String addPhoto(Long pageId, MultipartFile[] files) {
        photoService.addPhoto(getById(pageId), files);
        return "success";
    }

    public String deletePhoto(Long photoId) {
        photoService.delete(photoId);
        return "success";
    }

    public String create(HomePageDto dto) {
        HomePage page = new HomePage();
        setData(dto, page);
        save(page);
        return "success";
    }

    public String update(HomePageDto dto) {
        HomePage page = getById(dto.getId());
        if (page == null) return "error";
        setData(dto, page);
        save(page);
        return "success";
    }

    public void setData(HomePageDto dto, HomePage page) {
        page.setAboutRu(dto.getAboutRu());
        page.setAboutUz(dto.getAboutUz());
        page.setPhone(dto.getPhone());
        page.setAddress(dto.getAddress());
        page.setTitleUz(dto.getTitleUz());
        page.setTitleRu(dto.getTitleRu());
        page.setTextUz(dto.getTextUz());
        page.setTextRu(dto.getTextRu());
    }

    public List<HomePageDto> list() {
        List<HomePage> homePageList = repository.listAll();
        List<HomePageDto> dtoList = new ArrayList<>(homePageList.size());
        for (HomePage homePage : homePageList) {
            HomePageDto dto = getDtoById(homePage.getId());
            dtoList.add(dto);
        }
        return dtoList;
    }
}

