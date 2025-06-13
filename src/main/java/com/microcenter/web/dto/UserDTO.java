package com.microcenter.web.dto;
import com.microcenter.web.util.PasswordEqual;
import jakarta.validation.constraints.*;

@PasswordEqual(
        first = "Password",
        second = "PasswordConfirmed",
        message = "Password and confirmation must match"
)

public class UserDTO {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String Username;
    
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "Password must contain at least one special character")
    @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase and one lowercase letter")
    private String Password;
    
    @NotEmpty(message = "Password confirmation cannot be empty")
    @Size(min = 6, max = 20, message = "Password confirmation must be between 6 and 20 characters")
    @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "Password confirmation must contain at least one special character")
    @Pattern(regexp = ".*[0-9].*", message = "Password confirmation must contain at least one digit")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$", message = "Password confirmation must contain at least one uppercase and one lowercase letter")
//    @AssertTrue(message = "Password and confirmation must match")
//    public boolean isPasswordConfirmed() {
//        return Password != null && Password.equals(PasswordConfirmed);
//    }
    private String PasswordConfirmed;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be a valid email address")
    private String Email;

    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    private String FirstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String LastName;
    

    public UserDTO() {
    }

    public UserDTO(String username, String password, String passwordConfirmed, String email, String firstName, String lastName) {
        this.Username = username;
        this.Password = password;
        this.PasswordConfirmed = passwordConfirmed;
        this.Email = email;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPasswordConfirmed() {
        return PasswordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.PasswordConfirmed = passwordConfirmed;
    }
}
