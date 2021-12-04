package br.com.study.model;

public class Produto {
    private String codigo;
    private String descricao;
    private Integer valor;

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getValor() {
        return valor;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
