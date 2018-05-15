/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.ArrayList;
import java.util.Date;
import util.Formatacao;

/**
 *
 * @author ramon
 */
public class Movimentacao {

    private int id;
    private Pessoa pessoa;
    private String status;
    private Date data_reserva;
    private Date data_retirada;
    private Date data_devolver;
    private String observacao;
    private Double valor;
    private ArrayList<Movimentacao_has_livro> livros;

    public Movimentacao() {
        this.id = 0;
        this.pessoa = new Pessoa();
        this.status = "";
        this.data_reserva = null;
        this.data_retirada = null;
        this.data_devolver = null;
        this.observacao = "";
        this.valor = 0.0;
        this.livros = new ArrayList();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = Formatacao.formataString(status);
    }

    /**
     * @return the data_reserva
     */
    public Date getData_reserva() {
        return data_reserva;
    }

    /**
     * @param data_reserva the data_reserva to set
     */
    public void setData_reserva(Date data_reserva) {
        this.data_reserva = data_reserva;
    }

    /**
     * @return the data_retirada
     */
    public Date getData_retirada() {
        return data_retirada;
    }

    /**
     * @param data_retirada the data_retirada to set
     */
    public void setData_retirada(Date data_retirada) {
        this.data_retirada = data_retirada;
    }

    /**
     * @return the data_devolver
     */
    public Date getData_devolver() {
        return data_devolver;
    }

    /**
     * @param data_devolver the data_devolver to set
     */
    public void setData_devolver(Date data_devolver) {
        this.data_devolver = data_devolver;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = Formatacao.formataString(observacao);
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the livros
     */
    public ArrayList<Movimentacao_has_livro> getLivros() {
        return livros;
    }

    /**
     * @param livros the livros to set
     */
    public void setLivros(ArrayList<Movimentacao_has_livro> livros) {
        this.livros = livros;
    }
}
