package br.com.scrummob.model;

import java.util.Date;

/**
 * Created by hlandim on 17/06/15.
 */
public class Atividade {

    private static final String TAG = "Atividade";

    private Long id;
    private String idExterno;
    private String idResponsavel;
    private String nome;
    private Status status;
    private int ponto;
    private int prioridade;
    private String descricao;
    private Date dataCriacao;
    private String idSprint;

    public Atividade(Long id, String nome, Status status, int ponto, int prioridade, Date dataCriacao, String descricao) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.ponto = ponto;
        this.prioridade = prioridade;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
    }
    public Atividade(String nome, Status status, int ponto, int prioridade, Date dataCriacao, String descricao) {
        this.nome = nome;
        this.status = status;
        this.ponto = ponto;
        this.prioridade = prioridade;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(String idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(String idSprint) {
        this.idSprint = idSprint;
    }

    public enum Status {

        ABERTO("Aberto"), EM_DESENVOLVIMENTO("Em Desenvolvimento"), PARA_REVIEW(
                "Para Review"), EM_REVIEW("Em Review"), TERMINADA("Terminada");

        private String descricao;

        private Status(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public static Status getStatusPorDescricao(String descricao) {
            for (Status status : values()) {
                if (status.getDescricao().equals(descricao)) {
                    return status;
                }
            }
            return null;
        }

    }

}
