import java.util.*;
import java.io.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

// Classe principal do sistema
public class SistemaEventos {
    // Caminhos dos arquivos JSON que vão armazenar os dados
    private static final String ARQ_EVENTOS = "data/eventos.json";
    private static final String ARQ_USUARIOS = "data/usuarios.json";

    // Listas que guardam os eventos e usuários em memória
    private static ArrayList<Evento> eventos = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    // Scanner para ler entradas do usuário no console
    private static Scanner sc = new Scanner(System.in);

    // Objeto da biblioteca Gson para manipulação de arquivos JSON
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        // Ao iniciar o sistema, carrega os dados dos arquivos (se existirem)
        carregarDados();

        int opcao;
        do {
            // Menu de opções para o usuário
            System.out.println("\n===== SISTEMA DE EVENTOS =====");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Cadastrar usuário");
            System.out.println("4 - Listar usuários");
            System.out.println("5 - Notificar usuários por categoria");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer do scanner

            // Switch para decidir o que fazer
            switch (opcao) {
                case 1 -> cadastrarEvento();
                case 2 -> listarEventos();
                case 3 -> cadastrarUsuario();
                case 4 -> listarUsuarios();
                case 5 -> notificarUsuarios();
                case 0 -> salvarDados(); // ao sair, salvar tudo em arquivo
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==========================
    // Métodos do sistema
    // ==========================

    // Cadastro de evento
    private static void cadastrarEvento() {
        System.out.print("Nome do evento: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Categoria: ");
        String categoria = sc.nextLine();
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Hora: ");
        String hora = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        // Cria o objeto e adiciona na lista
        eventos.add(new Evento(nome, endereco, categoria, data, hora, descricao));
        salvarDados(); // salva no JSON
        System.out.println("✅ Evento cadastrado!");
    }

    // Listagem de eventos
    private static void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("⚠ Nenhum evento cadastrado.");
        } else {
            eventos.forEach(System.out::println);
        }
    }

    // Cadastro de usuário
    private static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Categoria de interesse: ");
        String interesse = sc.nextLine();

        // Cria o objeto e adiciona na lista
        usuarios.add(new Usuario(nome, email, interesse));
        salvarDados(); // salva no JSON
        System.out.println("✅ Usuário cadastrado!");
    }

    // Listagem de usuários
    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("⚠ Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    // Notificação: mostra os eventos que interessam a cada usuário
    private static void notificarUsuarios() {
        if (eventos.isEmpty() || usuarios.isEmpty()) {
            System.out.println("⚠ Cadastre eventos e usuários primeiro.");
            return;
        }

        // Para cada usuário, listar eventos da categoria de interesse
        for (Usuario u : usuarios) {
            System.out.println("\n🔔 Notificações para " + u + ":");
            for (Evento e : eventos) {
                if (e.getCategoria().equalsIgnoreCase(u.getInteresseCategoria())) {
                    System.out.println(" - " + e);
                }
            }
        }
    }

    // ==========================
    // Persistência em JSON
    // ==========================

    // Salva as listas em arquivos JSON
    private static void salvarDados() {
        try {
            // Cria a pasta "data" se não existir
            new File("data").mkdir();

            // Salva eventos
            FileWriter fwEventos = new FileWriter(ARQ_EVENTOS);
            gson.toJson(eventos, fwEventos);
            fwEventos.close();

            // Salva usuários
            FileWriter fwUsuarios = new FileWriter(ARQ_USUARIOS);
            gson.toJson(usuarios, fwUsuarios);
            fwUsuarios.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // Carrega dados dos arquivos JSON (se existirem)
    private static void carregarDados() {
        try {
            FileReader frEventos = new FileReader(ARQ_EVENTOS);
            eventos = gson.fromJson(frEventos, new TypeToken<ArrayList<Evento>>(){}.getType());
            frEventos.close();
        } catch (Exception e) { 
            eventos = new ArrayList<>(); 
        }

        try {
            FileReader frUsuarios = new FileReader(ARQ_USUARIOS);
            usuarios = gson.fromJson(frUsuarios, new TypeToken<ArrayList<Usuario>>(){}.getType());
            frUsuarios.close();
        } catch (Exception e) { 
            usuarios = new ArrayList<>(); 
        }
    }
}
