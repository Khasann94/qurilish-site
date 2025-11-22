package com.repository.document;

import com.dto.document.DocumentFileDto;
import com.model.document.DocumentFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentFileRepository extends CrudRepository<DocumentFile, Long>, PagingAndSortingRepository<DocumentFile, Long> {

    @Query("select new com.dto.document.DocumentFileDto(d) from DocumentFile d order by d.id desc ")
    List<DocumentFileDto> findAllWithDto();
}
