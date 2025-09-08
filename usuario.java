// Classe que representa um Usuário
public class Usuario {
    // Atributos do usuário
    private String nome;
    private String email;
    private String interesseCategoria;

    // inicializar os atributos do usuário
    public Usuario(String nome, String email, String interesseCategoria) {
        this.nome = nome;
        this.email = email;
        this.interesseCategoria = interesseCategoria;
    }

    // saber qual categoria interessa ao usuário
    public String getInteresseCategoria() { 
        return interesseCategoria; 
    }

    // texto para exibir no console
    @Override
    public String toString() {
        return "Usuário: " + nome + " | Email: " + email + " | Interesse: " + interesseCategoria;
    }
}
