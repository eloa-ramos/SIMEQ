package modelo;

// Combina Encontro, Serviço e a Mãe Responsável
public class EncontroServico {
    // Funcionalidade 2: Para cada serviço do encontro, registrar Nome da mãe responsável e breve descrição
    private int idEncontroServico;
    private Encontro encontro;
    private Servico servico;
    private Mae maeResponsavel; // A mãe responsável (opcional)
    private String descricaoAtividade; // Breve descrição da atividade

    public EncontroServico() {
    }

    public int getIdEncontroServico() {
        return idEncontroServico;
    }

    public void setIdEncontroServico(int idEncontroServico) {
        this.idEncontroServico = idEncontroServico;
    }

    public Encontro getEncontro() {
        return encontro;
    }

    public void setEncontro(Encontro encontro) {
        this.encontro = encontro;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Mae getMaeResponsavel() {
        return maeResponsavel;
    }

    public void setMaeResponsavel(Mae maeResponsavel) {
        this.maeResponsavel = maeResponsavel;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }
}