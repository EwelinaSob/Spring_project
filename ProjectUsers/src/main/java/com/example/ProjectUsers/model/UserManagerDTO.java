package com.example.ProjectUsers.model;

import com.example.ProjectUsers.UserNotFoundException;
import com.example.ProjectUsers.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString


public class UserManagerDTO{

    private String nameUser;
    private String surnameUser;
    private String jobUser;
    private String nameManager;
    private String surnameManager;

    public UserManagerDTO(String nameUser, String surnameUser, String jobUser, String nameManager, String surnameManager) {
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.jobUser = jobUser;
        this.nameManager = nameManager;
        this.surnameManager = surnameManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserManagerDTO)) return false;
        UserManagerDTO that = (UserManagerDTO) o;
        return Objects.equals(getNameUser(), that.getNameUser()) && Objects.equals(getSurnameUser(), that.getSurnameUser()) && Objects.equals(getJobUser(), that.getJobUser()) && Objects.equals(getNameManager(), that.getNameManager()) && Objects.equals(getSurnameManager(), that.getSurnameManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameUser(), getSurnameUser(), getJobUser(), getNameManager(), getSurnameManager());
    }



    @Override
    public String toString() {
        return "User {" +
                " User name = '" + nameUser + '\'' +
                ", User surname = '" + surnameUser + '\'' +
                ", User job ='" + jobUser + '\'' +
                ", Manager name = '" + nameManager + '\'' +
                ", Manager surname = '" + surnameManager + '\'' +
                '}';
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public String getJobUser() {
        return jobUser;
    }

    public String getNameManager() {
        return nameManager;
    }

    public String getSurnameManager() {
        return surnameManager;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public void setJobUser(String jobUser) {
        this.jobUser = jobUser;
    }


    public void setNameManager(String nameManager) {
        this.nameManager = nameManager;
    }

    public void setSurnameManager(String surnameManager) {
        this.surnameManager = surnameManager;
    }
}
