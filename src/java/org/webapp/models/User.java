/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.models;

/**
 *
 * @author Pablo Jacobo
 */
public class User {
    private String nickname;
    private String password;

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
    
    public User( ) {}
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
