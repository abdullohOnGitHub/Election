package uz.org.vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private Integer id;
    private Long fileSize;
    private String fileName;
    private String fileUrl;
    private String contentType;
    private Integer candidate_id;

    public FileData(Long fileSize, String fileName, String fileUrl, String contentType, Integer candidate_id) {
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.contentType = contentType;
        this.candidate_id = candidate_id;
    }
}
