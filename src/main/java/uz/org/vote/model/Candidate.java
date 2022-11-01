package uz.org.vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.org.vote.annotation.ValidatePhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Candidate {
  @Min(0)
  private int id;
  @NotNull@NotBlank
  private String firstName;
  @NotNull@NotBlank
  private String lastName;
  @Min(18)
  private int age;
  @Email
  private String email;
  @ValidatePhoneNumber
  private String phoneNumber;
  private String job;
  private long score;
  private int electionId;
  private boolean isActive;


}
