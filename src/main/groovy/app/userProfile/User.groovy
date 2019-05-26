package app.userProfile

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import javax.jws.soap.SOAPBinding

@Document
class User {

    @Id
    private String userId
    private String firstName
    private String lastName
    private String age
    private String gender
    private String bio
    private String email
    private String phone
    private String facebook
    private List<String> likes

    User() {}

    User(String firstName, String lastName, String age, String gender, String bio, String email, String phone, String facebook, List<String> likes) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.gender = gender
        this.bio = bio
        this.email = email
        this.phone = phone
        this.facebook = facebook
        this.likes = likes
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

    String getAge() {
        return age
    }

    void setAge(String age) {
        this.age = age
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

    String getPhone() {
        return phone
    }

    void setPhone(String phone) {
        this.phone = phone
    }

    String getFacebook() {
        return facebook
    }

    void setFacebook(String facebook) {
        this.facebook = facebook
    }

    List<String> getLikes() {
        if(likes == null)
            return new ArrayList<String>()
        else
            return likes
    }

    void setLikes(List<String> likes) {
        this.likes = likes
    }


    @Override
    boolean equals (Object o) {
        if(this==null && o==null) return true
        if(this!=null && o==null) return false
        if(this==null && o!=null) return false
        if(!(o instanceof User)) return false
        User user = (User) o
        return (this.getUserId() == user.getUserId())
    }

    /**
     * Method to get hash of object
     * @return int
     */
    int hashCode() {
        return (userId != null ? userId.hashCode() : 0)
    }
}
