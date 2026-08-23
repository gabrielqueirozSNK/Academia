public class Aluno implements Imprimivel {
    private String nome;
    private int idade;
    private PlanoTreino plano;
    private Instrutor instrutor;

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { return nome; }
    
    public void setPlano(PlanoTreino plano) {
        this.plano = plano;
    }
    
    public PlanoTreino getPlano() {
        return plano;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }
    
    public Instrutor getInstrutor() {
        return instrutor;
    }

    @Override
    public void mostrarDados() {
        System.out.println("\n== DADOS DO ALUNO ==");
        System.out.println("Nome: " + nome + " | Idade: " + idade);
        
        if (instrutor != null) {
            System.out.println("Acompanhado por: " + instrutor.getNome());
        } else {
            System.out.println("Sem instrutor associado.");
        }
    }
}
