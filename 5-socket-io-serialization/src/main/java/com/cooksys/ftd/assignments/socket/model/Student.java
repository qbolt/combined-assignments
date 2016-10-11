package com.cooksys.socket.assignment.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private String firstName;

    @XmlAttribute
    private String lastName;

    @XmlElement(name = "favorite-ide")
    private String favoriteIDE;

    @XmlElement(name = "favorite-language")
    private String favoriteLanguage;

    @XmlElement(name = "favorite-paradigm")
    private String favoriteParadigm;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavoriteIDE() {
        return favoriteIDE;
    }

    public void setFavoriteIDE(String favoriteIDE) {
        this.favoriteIDE = favoriteIDE;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public String getFavoriteParadigm() {
        return favoriteParadigm;
    }

    public void setFavoriteParadigm(String favoriteParadigm) {
        this.favoriteParadigm = favoriteParadigm;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", favoriteIDE='" + favoriteIDE + '\'' +
                ", favoriteLanguage='" + favoriteLanguage + '\'' +
                ", favoriteParadigm='" + favoriteParadigm + '\'' +
                '}';
    }
}
