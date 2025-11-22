package com.model.document;


import com.model.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table(name = "document_file")
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFile extends Auditable<String> {

    @Column(name = "name", length = 10000)
    private String name;

    @Column(name = "file_name", length = 1000)
    private String fileName;

    @Column(name = "file_extension", length = 1000)
    private String fileExtension;

    @Column(name = "modified_file_name")
    private String modifiedFileName;

    @Column(name = "upload_path", length = 1000)
    private String uploadPath;
}
