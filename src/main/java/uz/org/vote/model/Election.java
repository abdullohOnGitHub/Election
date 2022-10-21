package uz.org.vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Election {
  public Election(String name) {
    this.name = name;
  }

  @Min(0)
  private int id;
  @NotNull@NotBlank
  private String name;
  private boolean isActive=true;
}
