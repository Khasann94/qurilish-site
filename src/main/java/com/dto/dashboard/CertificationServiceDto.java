package com.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.dashboard.CertificationService;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CertificationServiceDto {


    private Long id;
    private String nameUz;
    private String nameRu;
    private String how;
    private Integer count;
    private Long price;
    private Long supplyPrice;
    private BigDecimal ndsStaffCount;
    private Long ndsStaffPrice;
    private Long ndsSupplyPrice;
    private List<CertificationServiceDto> child;
    private Long parentId;

    public CertificationServiceDto(CertificationService certificationService) {
        if (certificationService != null) {
           setId(certificationService.getId());
           setNameUz(certificationService.getNameUz());
           setNameRu(certificationService.getNameRu());
           setHow(certificationService.getHow());
           setCount(certificationService.getCount());
           setPrice(certificationService.getPrice());
           setSupplyPrice(certificationService.getNdsSupplyPrice());
           setNdsStaffCount(certificationService.getNdsStaffCount());
           setNdsStaffPrice(certificationService.getNdsStaffPrice());
           setParentId(certificationService.getParent() != null ? certificationService.getParent().getId() : null);
        }
    }

}
