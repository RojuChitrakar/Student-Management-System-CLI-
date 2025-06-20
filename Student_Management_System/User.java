package Student_Management_System;

public abstract class User {
    protected String name;
    protected String email;
    protected String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public abstract void showMenu();
}


