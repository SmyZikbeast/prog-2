package Utility;

import com.google.gson.annotations.Expose;

public class User {


    @Expose
    private int id;
    @Expose
    private String Username;
    @Expose
    private String Password;
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public User(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }

    public void setId(int userId) {
        this.id = userId;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString(){
        return Username;
    }
}
