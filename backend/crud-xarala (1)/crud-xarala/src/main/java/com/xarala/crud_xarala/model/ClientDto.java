package com.xarala.crud_xarala.model;

import lombok.*;

import java.io.Serializable;



@AllArgsConstructor
@NoArgsConstructor
public class ClientDto  implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String actions;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getActions() {
        return actions;
    }
}
