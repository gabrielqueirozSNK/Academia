public class Instrutor implements Imprimivel {
    private String nome;
    private String especialidade;

    public Instrutor(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome() { return nome; }

    @Override
    public void mostrarDados() {
        System.out.println("Instrutor: " + nome + " | Especialidade: " + especialidade);
    }
}