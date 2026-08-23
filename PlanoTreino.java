import java.util.ArrayList;

public class PlanoTreino implements Imprimivel {
    private String objetivo;
    private ArrayList<Exercicio> exercicios;

    public PlanoTreino(String objetivo) {
        this.objetivo = objetivo;
        this.exercicios = new ArrayList<>();
    }

    public void addExercicio(String nome, int series, int reps) {
        this.exercicios.add(new Exercicio(nome, series, reps));
    }

    @Override
    public void mostrarDados() {
        System.out.println("Objetivo do plano: " + objetivo);
        if (exercicios.isEmpty()) {
            System.out.println("Nenhum exercicio cadastrado no plano.");
        } else {
            System.out.println("Lista de Exercicios:");
            for (Exercicio ex : exercicios) {
                System.out.println("-> " + ex.getNome() + " | " + ex.getSeries() + " series de " + ex.getRepeticoes());
            }
        }
    }
}