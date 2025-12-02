package modelo;

public class Servico {
    // Funcionalidade 3: Serviços Fixos
    private int idServico;
    private String nome;

    public Servico() {
    }

    public Servico(int idServico, String nome) {
        this.idServico = idServico;
        this.nome = nome;
    }


    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}