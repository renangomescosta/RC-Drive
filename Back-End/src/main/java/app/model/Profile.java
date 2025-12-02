package app.model;

public class Profile {
    private String name;
    private String email;

    public Profile(String name, String email){
        setName(name);
        setEmail(email);
    }

    public String getName() {return name;}
    public String getEmail() {return email;}

    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}

}
