package com.model.dashboard;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "certification_service")
@EqualsAndHashCode(callSuper = true)
public class CertificationService extends Auditable<String> {

    @Column(name = "name_uz")
    private String nameUz;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "how")
    private String how;
    @Column(name = "count")
    private Integer count;
    @Column(name = "price")
    private Long price;
    @Column(name = "supply_price")
    private Long supplyPrice;
    @Column(name = "nds_staff_count", precision = 20, scale = 2)
    private BigDecimal ndsStaffCount;
    @Column(name = "nds_staff_price")
    private Long ndsStaffPrice;
    @Column(name = "nds_supply_price")
    private Long ndsSupplyPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @JsonIgnore
    private CertificationService parent;

}
