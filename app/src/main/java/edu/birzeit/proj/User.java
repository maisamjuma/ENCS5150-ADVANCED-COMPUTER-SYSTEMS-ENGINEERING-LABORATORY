package edu.birzeit.proj;

import androidx.annotation.NonNull;


public class User {
    private String Email;
    private String Password;
    private String mPhone;
    private String Gender;
    private String Fname;
    private String Lname;


    public User() {
    }

    public User(String Email, String Password, String mPhone, String Gender, String Fname,String Lname) {
        this.Email = Email;
        this.Password = Password;
        this.mPhone = mPhone;
        this.Gender = Gender;
        this.Fname= Fname;
        this.Lname= Lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }


    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }



    @Override
    public String toString() {
        return "User{" +
                "Email=" + Email +
                ", Password='" + Password + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", Fname='" + Fname + '\'' +
                ", Lname='" + Lname + '\'' +
                ", mGender='" + Gender + '\'' +
                '}';
    }
}