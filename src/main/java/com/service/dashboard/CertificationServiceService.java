package com.service.dashboard;

import com.dto.dashboard.CertificationServiceDto;
import com.model.dashboard.CertificationService;
import com.repository.dashboard.CertificationServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationServiceService {

    private final CertificationServiceRepository repository;

    public void save(CertificationService service) {
        repository.save(service);
    }

    public CertificationService getById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public List<CertificationServiceDto> listAll() {
        List<CertificationService> listParent = repository.findAllWithParent();
        List<CertificationServiceDto> dtoList = new java.util.ArrayList<>(listParent.size());
        for (CertificationService service : listParent) {
            CertificationServiceDto dto = new CertificationServiceDto(service);
            List<CertificationService> children = repository.findAllByParentId(service.getId());
            List<CertificationServiceDto> childrenDto = new java.util.ArrayList<>(children.size());
            for (CertificationService child : children) {
                CertificationServiceDto childDto = new CertificationServiceDto(child);
                childrenDto.add(childDto);
            }
            dto.setChild(childrenDto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public String create(CertificationServiceDto dto) {
        CertificationService certificationService = new CertificationService();
        setData(dto, certificationService);
        save(certificationService);
        return "success";
    }

    public String update(CertificationServiceDto dto) {
        CertificationService certificationService = getById(dto.getId());
        if (certificationService == null) return "error";
        setData(dto, certificationService);
        save(certificationService);
        return "success";
    }

    public void setData(CertificationServiceDto dto, CertificationService certificationService) {
        certificationService.setNameUz(dto.getNameUz());
        certificationService.setNameRu(dto.getNameRu());
        certificationService.setHow(dto.getHow());
        certificationService.setCount(dto.getCount());
        certificationService.setPrice(dto.getPrice());
        certificationService.setSupplyPrice(dto.getSupplyPrice());
        certificationService.setNdsStaffCount(dto.getNdsStaffCount());
        certificationService.setNdsStaffPrice(dto.getNdsStaffPrice());
        certificationService.setNdsSupplyPrice(dto.getNdsSupplyPrice());
        if (dto.getParentId() != null) {
            certificationService.setParent(getById(dto.getParentId()));
        }
    }

    public String delete(Long id) {
        CertificationService certificationService = getById(id);
        if (certificationService == null) return "error";
        repository.delete(certificationService);
        return "success";
    }

}
