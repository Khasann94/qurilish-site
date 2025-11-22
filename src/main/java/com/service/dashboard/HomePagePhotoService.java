package com.service.dashboard;

import com.dto.dashboard.HomePagePhotoDto;
import com.model.dashboard.HomePage;
import com.model.dashboard.HomePagePhoto;
import com.repository.dashboard.HomePagePhotoRepository;
import com.service.UploadPathService;
import com.util.UtilComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePagePhotoService {

    private final HomePagePhotoRepository repository;
    private final UploadPathService uploadPathService;


    public void save(HomePagePhoto photo) {
        repository.save(photo);
    }

    public List<HomePagePhotoDto> getByPageId(Long pageId) {
        return repository.findAllByHomePageId(pageId);
    }

    public void addPhoto(HomePage page, MultipartFile[] files) {
        for (MultipartFile file : files) {
            com.virtual.docflow_service.dto.FileDto fileDto = UtilComponents.saveFile(file, "homePage", uploadPathService, file.getOriginalFilename());
            HomePagePhoto photo = new HomePagePhoto();
            photo.setHomePage(page);
            photo.setPhotoName(fileDto.getFilename());
            photo.setPhotoExtension(fileDto.getExtension());
            photo.setUploadPath(fileDto.getUploadPath());
            photo.setModifiedPhotoName(fileDto.getModifiedName());
            save(photo);
        }
    }

    public HomePagePhoto getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Muvaffaqiyatli o'chirildi";
    }
}
