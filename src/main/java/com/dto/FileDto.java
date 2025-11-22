package com.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class FileDto {
    private String filename;
    private Long size;
    private String extension;
    private String modifiedName;
    private String uploadPath;
    private String absolutePath;

    public FileDto(){
    }

    public FileDto(String filename, Long size, String extension, String modifiedName, String uploadPath, String absolutePath) {
        this.filename = filename;
        this.size = size;
        this.extension = extension;
        this.modifiedName = modifiedName;
        this.uploadPath = uploadPath;
        this.absolutePath = absolutePath;
    }
}
