package com.repository.support;

import com.dto.support.LanguageDto;
import com.model.support.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>, PagingAndSortingRepository<Language, Long> {


    @Query("SELECT new com.dto.support.LanguageDto(m, true) FROM Language m ORDER BY m.id DESC")
    List<LanguageDto> listAll();

    @Query("SELECT new com.dto.support.LanguageDto(m) FROM Language m " +
            "WHERE LOWER(m.name.uz) LIKE %?1% " +
            "OR LOWER(m.name.ru) LIKE %?1% OR LOWER(m.code) LIKE %?1% " +
            "ORDER BY m.id DESC")
    Page<LanguageDto> findAllWithSearch(String search, Pageable paging);

    @Query("select l from Language l where l.code = ?1")
    Language findByCode(String code);
}
