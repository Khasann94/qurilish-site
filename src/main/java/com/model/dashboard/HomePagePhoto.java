package com.model.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table(name = "home_page_photo")
@NoArgsConstructor
@AllArgsConstructor
public class HomePagePhoto extends Auditable<String> {

    @Column(name = "photo_name", length = 1000)
    private String photoName;

    @Column(name = "photo_extension", length = 1000)
    private String photoExtension;

    @Column(name = "modified_photo_name")
    private String modifiedPhotoName;

    @Column(name = "upload_path", length = 1000)
    private String uploadPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_page_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private HomePage homePage;

}
