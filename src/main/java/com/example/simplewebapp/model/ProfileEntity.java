package com.example.simplewebapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
public class ProfileEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "profile_uid", nullable = false)
    private String profileUid;

    @Column(name = "user_uid", nullable = false)
    private String userUid;

    @OneToOne
    @JoinColumn(name = "user_uid", referencedColumnName = "user_uid",
            insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "website")
    private String website;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public void setProfileUid(String profileUid) {
        this.profileUid = profileUid;
    }

    public String getProfileUid() {
        return profileUid;
    }

    public String getUserUid() {return userUid;}

    public void setUserUid(String userUid) {this.userUid = userUid;}

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {this.user = user;}

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

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Date getCreatedAt() {return createdAt;}

    public Date getUpdatedAt() {return updatedAt;}

    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}
}
