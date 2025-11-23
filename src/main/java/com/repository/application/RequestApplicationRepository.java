package com.repository.application;

import com.dto.application.RequestApplicationDto;
import com.model.application.RequestApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestApplicationRepository extends CrudRepository<RequestApplication, Long> {


    @Query("select new com.dto.application.RequestApplicationDto(a) from RequestApplication a order by a.id desc ")
    List<RequestApplicationDto> listAllWithDto();


}
