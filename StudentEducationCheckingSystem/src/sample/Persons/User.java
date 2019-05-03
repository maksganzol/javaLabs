package sample.Persons;

public abstract class User {
    protected String login, password, firstname, lastName, status;

    public User(String login, String password, String firstname, String lastName) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    abstract public String getStatus();
}
