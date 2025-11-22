package com.repository.dashboard;

import com.dto.dashboard.HomePageDto;
import com.model.dashboard.HomePage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomePageRepository extends CrudRepository<HomePage, Long> {


    @Query("select p from HomePage p ")
    List<HomePage> listAll();
}
