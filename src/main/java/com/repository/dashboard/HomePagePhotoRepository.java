package com.repository.dashboard;

import com.dto.dashboard.HomePagePhotoDto;
import com.model.dashboard.HomePagePhoto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomePagePhotoRepository extends CrudRepository<HomePagePhoto, Long> {


    @Query("select new com.dto.dashboard.HomePagePhotoDto(p) from HomePagePhoto p where p.homePage.id = ?1 order by p.id")
    List<HomePagePhotoDto> findAllByHomePageId(Long homePageId);
}
