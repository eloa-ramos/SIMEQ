package modelo;

import java.time.LocalDate;
import java.util.List;

/**
 * A classe Relatorio representa um resumo das atividades de um Encontro.
 * Neste projeto, esta classe é usada primariamente para estruturar a informação
 * antes de ser escrita no arquivo TXT, sendo a maior parte da lógica
 * de composição e geração tratada na camada DAO.
 */
public class Relatorio {

    private Encontro encontro;
    private List<EncontroServico> detalhesServicos;

    public Relatorio() {
    }

    // Construtor opcional para montar o relatório
    public Relatorio(Encontro encontro, List<EncontroServico> detalhesServicos) {
        this.encontro = encontro;
        this.detalhesServicos = detalhesServicos;
    }

    // Getters e Setters
    public Encontro getEncontro() {
        return encontro;
    }

    public void setEncontro(Encontro encontro) {
        this.encontro = encontro;
    }

    public List<EncontroServico> getDetalhesServicos() {
        return detalhesServicos;
    }

    public void setDetalhesServicos(List<EncontroServico> detalhesServicos) {
        this.detalhesServicos = detalhesServicos;
    }
}