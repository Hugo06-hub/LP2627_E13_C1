package pt.upt.perdidos;

import java.util.List;
import java.util.Scanner;

import pt.upt.perdidos.model.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Gerir gerir = new Gerir();

        int opcao;
        boolean isRunning = true;

        while (isRunning) {

            System.out.println("===== PERDIDOS E ACHADOS =====");
            System.out.println("--- Registar ---");
            System.out.println("1  - Registar cliente");
            System.out.println("2  - Registar funcionário");
            System.out.println("3  - Registar espaço");
            System.out.println("4  - Passar cartão (entrada/saída)");
            System.out.println("5  - Registar objeto achado");
            System.out.println("6  - Reportar objeto perdido");
            System.out.println("7  - Registar levantamento");
            System.out.println("--- Listar e consultar ---");
            System.out.println("8  - Listar clientes");
            System.out.println("9  - Listar funcionários");
            System.out.println("10 - Listar espaços");
            System.out.println("11 - Listar objetos");
            System.out.println("12 - Listar mensagens do fórum");
            System.out.println("13 - Ver mensagens de um objeto");
            System.out.println("14 - Ver histórico de um objeto");
            System.out.println("--- Fórum ---");
            System.out.println("15 - Contactar os perdidos e achados (fórum, telefone ou email)");
            System.out.println("16 - Responder a mensagem (funcionário)");
            System.out.println("--- Objetos ---");
            System.out.println("17 - Reclamar objeto");
            System.out.println("18 - Alterar estado de um objeto");
            System.out.println("19 - Eliminar objeto");
            System.out.println("0  - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

                switch (opcao) {

                    case 1: {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Telefone: ");
                        int num = Integer.parseInt(scanner.nextLine());
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Password: ");
                        String pass = scanner.nextLine();
                        Cliente cliente = gerir.registarCliente(nome, num, email, pass);
                        if (cliente != null) {
                            System.out.println("Cliente registado com id " + cliente.getIdC());
                        }
                        break;
                    }

                    case 2: {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Password: ");
                        String pass = scanner.nextLine();
                        Funcionario funcionario = gerir.registarFuncionario(nome, email, pass);
                        if (funcionario != null) {
                            System.out.println("Funcionário registado com id " + funcionario.getIdF());
                        }
                        break;
                    }

                    case 3: {
                        System.out.print("Nome da sala: ");
                        String sala = scanner.nextLine();
                        Espaco espaco = gerir.registarEspaco(sala);
                        if (espaco != null) {
                            System.out.println("Espaço registado com id " + espaco.getId());
                        }
                        break;
                    }

                    case 4: {
                        System.out.print("Id do cliente (cartão): ");
                        long idCliente = Long.parseLong(scanner.nextLine());
                        System.out.print("Id do espaço: ");
                        long idEspaco = Long.parseLong(scanner.nextLine());
                        System.out.print("Tipo (ENTRADA ou SAIDA): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        Registo registo = gerir.registarAcesso(idCliente, idEspaco, tipo);
                        if (registo != null) {
                            System.out.println("Passagem de cartão registada em " + registo.getDataHora());
                        }
                        break;
                    }

                    case 5: {
                        System.out.print("Id do espaço onde foi achado: ");
                        long idEspaco = Long.parseLong(scanner.nextLine());
                        System.out.print("Nome do objeto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Tipo do objeto: ");
                        String tipo = scanner.nextLine();
                        Objeto objeto = gerir.registarObjeto(idEspaco, nome, tipo);
                        if (objeto != null) {
                            System.out.println("Objeto achado registado com id " + objeto.getId());
                        }
                        break;
                    }

                    case 6: {
                        System.out.print("Id do cliente: ");
                        long idCliente = Long.parseLong(scanner.nextLine());
                        System.out.print("Id do espaço onde perdeu: ");
                        long idEspaco = Long.parseLong(scanner.nextLine());
                        System.out.print("Nome do objeto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Tipo do objeto: ");
                        String tipo = scanner.nextLine();
                        Objeto objeto = gerir.reportarObjetoPerdido(idCliente, idEspaco, nome, tipo);
                        if (objeto != null) {
                            System.out.println("Objeto perdido registado com id " + objeto.getId());
                        }
                        break;
                    }

                    case 7: {
                        System.out.print("Id do cliente: ");
                        long idCliente = Long.parseLong(scanner.nextLine());
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        gerir.registarLevantamento(idCliente, idObjeto);
                        break;
                    }

                    case 8: {
                        List<Cliente> clientes = gerir.listarClientes();
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente.getIdC() + " - " + cliente.getNome() + " - " + cliente.getEmail());
                        }
                        break;
                    }

                    case 9: {
                        List<Funcionario> funcionarios = gerir.listarFuncionarios();
                        for (Funcionario funcionario : funcionarios) {
                            System.out.println(funcionario.getIdF() + " - " + funcionario.getNome());
                        }
                        break;
                    }

                    case 10: {
                        List<Espaco> espacos = gerir.listarEspacos();
                        for (Espaco espaco : espacos) {
                            System.out.println(espaco.getId() + " - " + espaco.getSala());
                        }
                        break;
                    }

                    case 11: {
                        List<Objeto> objetos = gerir.listarObjetos();
                        for (Objeto objeto : objetos) {
                            System.out.println(objeto.getId() + " - " + objeto.getNome() + " - " + objeto.getEstado()
                                    + " - " + objeto.getEspaco().getSala());
                        }
                        break;
                    }

                    case 12: {
                        List<Mensagem> mensagens = gerir.listarMensagens();
                        for (Mensagem mensagem : mensagens) {
                            System.out.println(mensagem.getId() + " - objeto " + mensagem.getObjeto().getId()
                                    + " - " + mensagem.getTexto() + " | Resposta: " + mensagem.getResposta());
                        }
                        break;
                    }

                    case 13: {
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        List<Mensagem> mensagens = gerir.verMensagensDoObjeto(idObjeto);
                        for (Mensagem mensagem : mensagens) {
                            System.out.println(mensagem.getId() + " - " + mensagem.getTexto()
                                    + " | Resposta: " + mensagem.getResposta());
                        }
                        break;
                    }

                    case 14: {
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        gerir.historicoDoObjeto(idObjeto);
                        break;
                    }

                    case 15: {
                        System.out.print("Id do cliente: ");
                        long idCliente = Long.parseLong(scanner.nextLine());
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        System.out.print("Canal (FORUM, TELEFONE ou EMAIL): ");
                        String canal = scanner.nextLine();
                        System.out.print("Mensagem: ");
                        String texto = scanner.nextLine();
                        Mensagem mensagem = gerir.contactarPerdidosEAchados(idCliente, idObjeto, canal, texto);
                        if (mensagem != null) {
                            System.out.println("Mensagem enviada com id " + mensagem.getId());
                        }
                        break;
                    }

                    case 16: {
                        System.out.print("Id da mensagem: ");
                        long idMensagem = Long.parseLong(scanner.nextLine());
                        System.out.print("Id do funcionário: ");
                        long idFuncionario = Long.parseLong(scanner.nextLine());
                        System.out.print("Resposta: ");
                        String resposta = scanner.nextLine();
                        gerir.responderCliente(idMensagem, idFuncionario, resposta);
                        break;
                    }

                    case 17: {
                        System.out.print("Id do cliente: ");
                        long idCliente = Long.parseLong(scanner.nextLine());
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        gerir.reclamarObjeto(idCliente, idObjeto);
                        break;
                    }

                    case 18: {
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        System.out.print("Novo estado (PERDIDO, ACHADO, ENTREGUE): ");
                        String estado = scanner.nextLine().toUpperCase();
                        gerir.alterarEstado(idObjeto, estado);
                        break;
                    }

                    case 19: {
                        System.out.print("Id do objeto: ");
                        long idObjeto = Long.parseLong(scanner.nextLine());
                        gerir.eliminarObjeto(idObjeto);
                        break;
                    }

                    case 0: {
                        System.out.println("A sair do programa...");
                        isRunning = false;
                        break;
                    }

                    default: {
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                    }
                }
        }

        scanner.close();
        gerir.fechar();
    }
}
