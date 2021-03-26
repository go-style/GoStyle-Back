package fr.epsi.gostyle.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userid;
    private String username;
    private String usermail;
    private String userpassword;

    @Override
    public String toString() {
        return String.format("User=[Name:%s, Mail:%s, Password:%s]", username, usermail, userpassword);
    }


    public User(int userid, String username, String usermail, String userpassword) {
        this.userid = userid;
        this.username = username;
        this.usermail = usermail;
        this.userpassword = userpassword;
    }

    //TEST
    public User(String username, String usermail, String userpassword) {
        this.username = username;
        this.usermail = usermail;
        this.userpassword = userpassword;
    }

    public User() {

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) throws NoSuchAlgorithmException {


        String mdp = userpassword;
        String mdpSalt = mdp+ User.SALT;
        byte[] byteChaine = mdpSalt.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(byteChaine);
        String s = new String(hash, StandardCharsets.UTF_8);
        this.userpassword = s;

    }

    public static final String SALT = "45ergqerfqerf455";
}