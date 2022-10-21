package uz.org.vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Min(0)
  private int id;
  @NotNull@NotBlank
  private String firstName;
  @NotNull@NotBlank
  private String lastName;
  @Min(18)
  private long age;

  public User(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  @Size(min = 4, max = 12)
  private String username;
  @Email
  private String email;
  @Size(min = 8, max = 12)
  private String password;
  private Boolean isActive;
  private Boolean isAdmin;
}
