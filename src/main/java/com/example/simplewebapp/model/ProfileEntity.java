package com.example.simplewebapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class ProfileEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "profile_uid", nullable = false)
    private String profileUid;

    @OneToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "user_uid", nullable = false)
    private UserEntity user;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "website")
    private String website;

    public void setProfileUid(String profileUid) {
        this.profileUid = profileUid;
    }

    public String getProfileUid() {
        return profileUid;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
