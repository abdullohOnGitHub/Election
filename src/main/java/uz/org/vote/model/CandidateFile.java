package uz.org.vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateFile {

  private Integer id;
  private Long fileSize;
  private String fileName;
  private String fileUrl;
  private String contentType;
  private Integer candidateId;

}
