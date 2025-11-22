package com.util;

import com.exception.RecordNotFoundException;
import com.service.UploadPathService;
import com.virtual.docflow_service.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UtilComponents {


    public static String emptyToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    public static FileDto saveFile(MultipartFile file, String folder, UploadPathService uploadPathService, String filename) {
        if (file == null || !StringUtils.hasText(file.getOriginalFilename()))
            throw new RecordNotFoundException("Invalid filename...");
        String fileName;
        if (filename != null && !filename.isEmpty()) fileName = filename;
        else fileName = file.getOriginalFilename().replaceAll(" ", "");
        String fileExtension = FilenameUtils.getExtension(fileName);
        fileName = FilenameUtils.getBaseName(fileName);
        if (fileName.length() > 20) fileName = fileName.substring(0, 20);
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
        String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth();
        String modifiedFileName = fileName + "_" + pwdGenerator.generate(15) + System.currentTimeMillis() + "." + fileExtension;
        String url = currentMill + "/" + folder;
        File storeFile = uploadPathService.getFilePath(modifiedFileName, url);
        if (storeFile != null) {
            try {
                FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        assert storeFile != null;
        return FileDto.builder().filename(fileName + "." + fileExtension).size(file.getSize()).extension(fileExtension).modifiedName(modifiedFileName).uploadPath("/images/" + url + "/" + modifiedFileName).absolutePath(storeFile.getAbsolutePath()).build();
    }


    public static PageRequest paginationToPageRequest(Pagination pagination) {
        return PageRequest.of(pagination.getPage(), pagination.getLimit());
    }


    public static Map<String, Object> listMap(Page<?> page) {
        Map<String, Object> map = new HashMap<>();
        if (page != null && page.hasContent()) {
            map.put("list", page.getContent());
            map.put("total", page.getTotalElements());
        } else {
            map.put("list", new ArrayList<>(1));
            map.put("total", 0);
        }
        return map;
    }

}
