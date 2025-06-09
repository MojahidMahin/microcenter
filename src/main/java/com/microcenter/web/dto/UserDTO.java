package com.microcenter.web.dto;

public class UserDTO {
    private String Username;
    private String Password;
    private String PasswordConfirmed;
    private String Email;
    private String FirstName;
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
