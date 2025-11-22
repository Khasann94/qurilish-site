package com.repository.dashboard;

import com.model.dashboard.CertificationService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationServiceRepository extends CrudRepository<CertificationService, Long> {

    @Query("select c from CertificationService c where c.parent.id is null order by c.id")
    List<CertificationService> findAllWithParent();


    @Query("select c from CertificationService c where c.parent.id = ?1 order by c.id")
    List<CertificationService> findAllByParentId(Long parentId);
}
