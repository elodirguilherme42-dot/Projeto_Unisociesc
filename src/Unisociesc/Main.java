package Unisociesc;


	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	import java.io.*;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;

	public class Main {

	    static Scanner sc = new Scanner(System.in);
	    static List<Usuario> usuarios = new ArrayList<>();
	    static List<Evento> eventos = new ArrayList<>();
	    static String arquivoEventos = "events.data";
	    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	    static void cadastrarUsuario() {
	        System.out.print("Nome: ");
	        String nome = sc.nextLine();

	        System.out.print("Idade: ");
	        int idade = sc.nextInt();
	        sc.nextLine();

	        System.out.print("Sexo: ");
	        String sexo = sc.nextLine();

	        System.out.print("Email: ");
	        String email = sc.nextLine();

	        System.out.print("Telefone: ");
	        String telefone = sc.nextLine();

	        System.out.print("CPF: ");
	        String cpf = sc.nextLine();

	        System.out.print("Cidade: ");
	        String cidade = sc.nextLine();

	        System.out.print("Senha: ");
	        String senha = sc.nextLine();

	        Usuario u = new Usuario(nome, idade, sexo, email, telefone, cpf, cidade, senha);
	        usuarios.add(u);

	        System.out.println("Usuário cadastrado com sucesso!");
	    }

	    static void cadastrarEvento() {
	        System.out.print("Nome do evento: ");
	        String nome = sc.nextLine();

	        System.out.println("Horário do evento (dd/MM/yyyy HH:mm):");
	        String horarioStr = sc.nextLine().trim();
	        LocalDateTime horario = LocalDateTime.parse(horarioStr, formatter);

	        System.out.print("Local do evento: ");
	        String local = sc.nextLine();

	        System.out.print("Categoria do evento: ");
	        String categoria = sc.nextLine();

	        System.out.print("Descrição: ");
	        String descricao = sc.nextLine();

	        Evento u = new Evento(nome, horario, local, descricao, categoria);
	        eventos.add(u);

	        salvarEventos();
	        System.out.println("Evento cadastrado com sucesso!");
	    }

	    static void salvarEventos() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoEventos))) {
	            for (Evento e : eventos) {
	                writer.write(e.getNome() + ";" +
	                             e.getHorario().format(formatter) + ";" +
	                             e.getLocal() + ";" +
	                             e.getDescricao() + ";" +
	                             e.getCategoria());
	                writer.newLine();
	            }
	        } catch (IOException ex) {
	            System.out.println("Erro ao salvar eventos: " + ex.getMessage());
	        }
	    }

	    static void carregarEventos() {
	        File arquivo = new File(arquivoEventos);
	        if (!arquivo.exists()) return;

	        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
	            String linha;
	            while ((linha = reader.readLine()) != null) {
	                String[] partes = linha.split(";");
	                if (partes.length == 5) {
	                    String nome = partes[0];
	                    LocalDateTime horario = LocalDateTime.parse(partes[1], formatter);
	                    String local = partes[2];
	                    String descricao = partes[3];
	                    String categoria = partes[4];

	                    eventos.add(new Evento(nome, horario, local, descricao, categoria));
	                }
	            }
	        } catch (IOException ex) {
	            System.out.println("Erro ao carregar eventos: " + ex.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        carregarEventos();

	        while (true) {
	            System.out.println("\n===== MENU PRINCIPAL =====");
	            System.out.println("1 - Cadastrar Usuário");
	            System.out.println("2 - Criar Evento");
	            System.out.println("3 - Consultar Eventos");
	            System.out.println("4 - Participar de Eventos");
	            System.out.println("5 - Cancelar Participação");
	            System.out.println("6 - Listar Participantes do Evento");
	            System.out.println("7 - Login");
	            System.out.println("0 - Sair");

	            int opcao = sc.nextInt();
	            sc.nextLine();

	            switch (opcao) {
	                case 1:
	                    cadastrarUsuario();
	                    break;
	                case 2:
	                    if (usuarios.isEmpty()) {
	                        System.out.println("Não é possível criar evento. Cadastre pelo menos um usuário primeiro!");
	                    } else {
	                        cadastrarEvento();
	                    }
	                    break;
	                case 3:
	                    if (eventos.isEmpty()) {
	                        System.out.println("Nenhum evento cadastrado");
	                    } else {
	                        eventos.sort((e1, e2) -> e1.getHorario().compareTo(e2.getHorario()));
	                        System.out.println("Eventos:");
	                        for (int i = 0; i < eventos.size(); i++) {
	                            System.out.println((i + 1) + " - " + eventos.get(i));
	                        }
	                    }
	                    break;
	                case 4:
	                    if (eventos.isEmpty()) {
	                        System.out.println("Nenhum evento cadastrado");
	                    } else if (usuarios.isEmpty()) {
	                        System.out.println("Nenhum usuário cadastrado");
	                    } else {
	    
	                        System.out.println("Selecione o evento que deseja participar:");
	                        for (int i = 0; i < eventos.size(); i++) {
	                            System.out.println((i + 1) + " - " + eventos.get(i));
	                        }
	                        int opcaoEventos = sc.nextInt();
	                        sc.nextLine();
	                        if (opcaoEventos < 1 || opcaoEventos > eventos.size()) {
	                            System.out.println("Opção inválida!");
	                        } else {
	                            Evento eventoEscolhido = eventos.get(opcaoEventos - 1);

	                        System.out.println("Selecione o usuário que vai participar:");
	                        for (int i = 0; i < usuarios.size(); i++) {
	                            System.out.println((i + 1) + " - " + usuarios.get(i).getNome());
	                        }
	                        int opcaoUsuario = sc.nextInt();
	                        sc.nextLine();
	                        if (opcaoUsuario < 1 || opcaoUsuario > usuarios.size()) {
	                            System.out.println("Opção inválida!");
	                        } else {
	                            Usuario usuarioAtivo = usuarios.get(opcaoUsuario - 1);
	                            eventoEscolhido.adicionarParticipante(usuarioAtivo);
	                            salvarEventos();
	                            }
	                        }
	                    }
	                    break;

	                case 5:
	                    if (eventos.isEmpty()) {
	                        System.out.println("Nenhum evento cadastrado");
	                    } else if (usuarios.isEmpty()) {
	                        System.out.println("Nenhum usuário cadastrado");
	                    } else {
	    
	                        System.out.println("Selecione o evento para cancelar sua participação:");
	                    for (int i = 0; i < eventos.size(); i++) {
	                        System.out.println((i + 1) + " - " + eventos.get(i));
	                    }
	                    int opcaoEventos = sc.nextInt();
	                    sc.nextLine();
	                    if (opcaoEventos < 1 || opcaoEventos > eventos.size()) {
	                        System.out.println("Opção inválida");
	                    } else {
	                        Evento eventoEscolhido = eventos.get(opcaoEventos - 1);

	        
	                        System.out.println("Selecione o usuário que vai cancelar a participação:");
	                    for (int i = 0; i < usuarios.size(); i++) {
	                        System.out.println((i + 1) + " - " + usuarios.get(i).getNome());
	                    }
	                        int opcaoUsuario = sc.nextInt();
	                        sc.nextLine();
	                    if (opcaoUsuario < 1 || opcaoUsuario > usuarios.size()) {
	                        System.out.println("Opção inválida");
	                    } else {
	                        Usuario usuarioAtivo = usuarios.get(opcaoUsuario - 1);
	                        eventoEscolhido.removerParticipante(usuarioAtivo);
	                            salvarEventos();
	                    }
	                    }
	                }

	                    break;
	                case 6:
	                    if (eventos.isEmpty()) {
	                        System.out.println("Nenhum evento cadastrado");
	                    } else {
	                        System.out.println("Selecione o evento para ver a lista de participantes:");
	                        for (int i = 0; i < eventos.size(); i++) {
	                            System.out.println((i + 1) + " - " + eventos.get(i));
	                        }
	                        int opcaoEventos = sc.nextInt();
	                        sc.nextLine();
	                        if (opcaoEventos < 1 || opcaoEventos > eventos.size()) {
	                            System.out.println("Opção inválida");
	                        } else {
	                            Evento eventoEscolhido = eventos.get(opcaoEventos - 1);
	                            eventoEscolhido.listarParticipantes();
	                        }
	                    }
	                    break;
	                case 0:
	                    System.exit(0);
	                default:
	                    System.out.println("Opção inválida!");
	            }
	        }
	    }
	}



