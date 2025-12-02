package app.model;

public class Profile {
    private String nome;
    private String email;

    public Profile(){
    }

    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
