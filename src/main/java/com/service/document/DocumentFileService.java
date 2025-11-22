package com.service.document;

import com.dto.FileDto;
import com.dto.document.DocumentFileDto;
import com.model.document.DocumentFile;
import com.repository.document.DocumentFileRepository;
import com.service.UploadPathService;
import com.util.UtilComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentFileService {

    private final DocumentFileRepository repository;
    private final UploadPathService uploadPathService;

    public void save(DocumentFile file) {
        repository.save(file);
    }

    public List<DocumentFileDto> listAll() {
        return repository.findAllWithDto();
    }

    public DocumentFile getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Muvaffaqiyatli o'chirildi";
    }

    public String create(String name, MultipartFile file) {
        FileDto fileDto = UtilComponents.saveFile(file, "documentFile", uploadPathService, file.getOriginalFilename());
        DocumentFile documentFile = new DocumentFile();
        documentFile.setFileExtension(fileDto.getExtension());
        documentFile.setModifiedFileName(fileDto.getModifiedName());
        documentFile.setFileName(name);
        documentFile.setUploadPath(fileDto.getUploadPath());
        documentFile.setName(name);
        save(documentFile);
        return "success";
    }
}
