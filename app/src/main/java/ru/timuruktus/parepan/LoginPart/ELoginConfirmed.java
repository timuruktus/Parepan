package ru.timuruktus.parepan.LoginPart;

public class ELoginConfirmed {

    public String city, school;
    public LoginFragment loginFragment;

    public ELoginConfirmed(String city, String school, LoginFragment loginFragment) {
        this.city = city;
        this.school = school;
        this.loginFragment = loginFragment;
    }

    public void callback(){
        loginFragment.eventCallback();
    }

}
