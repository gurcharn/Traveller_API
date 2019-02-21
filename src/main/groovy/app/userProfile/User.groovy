package app.userProfile

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String bio;
    private String email;
    private String facebook;

    User() {}

    User(String firstName, String lastName, String dateOfBirth, String gender, String bio, String email, String facebook) {
        this.firstName = firstName
        this.lastName = lastName
        this.dateOfBirth = dateOfBirth
        this.gender = gender
        this.bio = bio
        this.email = email
        this.facebook = facebook
    }

    String getUserId() {
        return userId
    }

    void setUserId(String userId) {
        this.userId = userId
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getDateOfBirth() {
        return dateOfBirth
    }

    void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth
    }

    String getGender() {
        return gender
    }

    void setGender(String gender) {
        this.gender = gender
    }

    String getBio() {
        return bio
    }

    void setBio(String bio) {
        this.bio = bio
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getFacebook() {
        return facebook
    }

    void setFacebook(String facebook) {
        this.facebook = facebook
    }
}
