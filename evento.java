// Classe pra representar o evento
public class Evento {
    // Atributos do evento
    private String nome;
    private String endereco;
    private String categoria;
    private String data;
    private String hora;
    private String descricao;

    // Inicializando os atributos do evento
    public Evento(String nome, String endereco, String categoria, String data, String hora, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    // comparar interesse do usuário com categoria do evento
    public String getCategoria() { 
        return categoria; 
    }

    // texto para exibir no console
    @Override
    public String toString() {
        return "Evento: " + nome + " | Categoria: " + categoria +
               " | Data: " + data + " " + hora +
               " | Local: " + endereco +
               " | Descrição: " + descricao;
    }
}
