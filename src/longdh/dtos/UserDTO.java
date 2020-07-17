/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author donglong
 */
public class UserDTO implements Serializable {

    private String id, name, role, password, email, phone;

    public UserDTO() {
    }

    public Vector toVector()  {
        Vector row = new Vector();
        row.add(id);
        row.add(name);
        row.add(email);
        row.add(phone);
        return row;
    }

    public UserDTO(String id, String name, String role, String password, String email, String phone) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(String id, String name, String role, String email, String phone) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
