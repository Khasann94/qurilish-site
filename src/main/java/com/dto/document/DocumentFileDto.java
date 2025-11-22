package com.dto.document;

import com.model.document.DocumentFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentFileDto {

    private Long id;
    private String uploadPath;
    private String extension;
    private String name;
    private String fileName;

    public DocumentFileDto(DocumentFile file) {
        if (file.getId() != null)
            setId(file.getId());
        setUploadPath(file.getUploadPath());
        setExtension(file.getFileExtension());
        setName(file.getName());
        setFileName(file.getFileName());
    }
}
