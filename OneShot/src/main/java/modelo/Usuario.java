/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nestor y Asociados
 */
public class Usuario {
    private String User;
    private String token;

    public Usuario() {
    }

    public Usuario(String User, String token) {
        this.User = User;
        this.token = token;
    }
    
    //GETTER Y SETTERS

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void logout(){
        this.User = "";
        this.token = "";
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "User=" + User + ", token=" + token + '}';
    }
    
    
}
