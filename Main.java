import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Instrutor> instrutores = new ArrayList<>();
        int op = 0;

        while (op != 8) {
            System.out.println("\n--- MENU DA ACADEMIA ---");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar instrutor");
            System.out.println("3 - Criar plano de treino para aluno");
            System.out.println("4 - Adicionar exercicios ao plano");
            System.out.println("5 - Associar instrutor a um aluno");
            System.out.println("6 - Exibir o plano de treino de um aluno");
            System.out.println("7 - Listar alunos e seus instrutores");
            System.out.println("8 - Sair");
            System.out.print("Escolha: ");
            
            // Leitura tratada da opção do menu
            op = lerOpcaoMenu(sc);

            try {
                if (op == 1) {
                    System.out.print("Nome do aluno: ");
                    String nome = sc.nextLine();
                    
                    int idade = lerInteiroSeguro(sc, "Idade: ");
                    
                    alunos.add(new Aluno(nome, idade));
                    System.out.println("Aluno cadastrado!");
                } 
                else if (op == 2) {
                    System.out.print("Nome do instrutor: ");
                    String nome = sc.nextLine();
                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();
                    
                    instrutores.add(new Instrutor(nome, esp));
                    System.out.println("Instrutor cadastrado!");
                }
                else if (op == 3) {
                    System.out.print("Nome do aluno para criar o plano: ");
                    String nomeBusca = sc.nextLine();
                    Aluno a = buscarAluno(alunos, nomeBusca);
                    
                    if (a != null) {
                        System.out.print("Qual o objetivo do plano? ");
                        String obj = sc.nextLine();
                        a.setPlano(new PlanoTreino(obj));
                        System.out.println("Plano criado no perfil do aluno.");
                    } else {
                        System.out.println("Aluno nao encontrado.");
                    }
                }
                else if (op == 4) {
                    System.out.print("Nome do aluno para add exercicio: ");
                    String nomeBusca = sc.nextLine();
                    Aluno a = buscarAluno(alunos, nomeBusca);
                    
                    if (a != null && a.getPlano() != null) {
                        System.out.print("Nome do exercicio: ");
                        String ex = sc.nextLine();
                        
                        int series = lerInteiroSeguro(sc, "Quantas series: ");
                        int reps = lerInteiroSeguro(sc, "Quantas repeticoes: ");
                        
                        a.getPlano().addExercicio(ex, series, reps);
                        System.out.println("Exercicio adicionado ao plano!");
                    } else {
                        System.out.println("Aluno nao encontrado ou sem plano criado.");
                    }
                }
                else if (op == 5) {
                    System.out.print("Nome do aluno: ");
                    String nomeAluno = sc.nextLine();
                    Aluno a = buscarAluno(alunos, nomeAluno);
                    
                    if (a != null) {
                        System.out.print("Nome do instrutor: ");
                        String nomeInst = sc.nextLine();
                        Instrutor inst = buscarInstrutor(instrutores, nomeInst);
                        
                        if (inst != null) {
                            a.setInstrutor(inst);
                            System.out.println("Instrutor associado ao aluno!");
                        } else {
                            System.out.println("Instrutor nao encontrado.");
                        }
                    } else {
                        System.out.println("Aluno nao encontrado.");
                    }
                }
                else if (op == 6) {
                    System.out.print("Nome do aluno: ");
                    String nomeBusca = sc.nextLine();
                    Aluno a = buscarAluno(alunos, nomeBusca);
                    
                    if (a != null) {
                        if (a.getPlano() != null) {
                            System.out.println("\n== PLANO DE TREINO DE " + a.getNome().toUpperCase() + " ==");
                            a.getPlano().mostrarDados();
                        } else {
                            System.out.println("Este aluno ainda nao tem plano de treino.");
                        }
                    } else {
                        System.out.println("Aluno nao encontrado.");
                    }
                }
                else if (op == 7) {
                    System.out.println("\n--- LISTA DE ALUNOS ---");
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        for (Aluno a : alunos) {
                            a.mostrarDados();
                        }
                    }
                }
                else if (op == 8) {
                    System.out.println("Fechando sistema...");
                }
                else {
                    System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao processar a operacao: " + e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Ler a opção do menu com tratamento para prevenir erros de digitação.
     */
    private static int lerOpcaoMenu(Scanner sc) {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpa o buffer do teclado
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas numeros inteiros!");
                sc.nextLine(); // Limpa o valor inválido digitado
                System.out.print("Tente novamente. Escolha: ");
            }
        }
    }

    /**
     * Ler valores inteiros genéricos (como idade, séries e repetições) com tratamento de exceção.
     */
    private static int lerInteiroSeguro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpa o buffer do teclado
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada invalida! Por favor, informe um numero inteiro.");
                sc.nextLine(); // Limpa a entrada incorreta
            }
        }
    }

    private static Aluno buscarAluno(ArrayList<Aluno> lista, String nome) {
        for (Aluno a : lista) {
            if (a.getNome().equalsIgnoreCase(nome)) return a;
        }
        return null;
    }

    private static Instrutor buscarInstrutor(ArrayList<Instrutor> lista, String nome) {
        for (Instrutor i : lista) {
            if (i.getNome().equalsIgnoreCase(nome)) return i;
        }
        return null;
    }
}
