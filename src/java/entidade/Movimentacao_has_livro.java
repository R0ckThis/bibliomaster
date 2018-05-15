/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.Date;
import util.Formatacao;

/**
 *
 * @author ramon
 */
public class Movimentacao_has_livro {

    private int movimentacao;
    private Copia_livro copia_livro;
    private String status;
    private Date data_devolucao;
    private String observacao;
    private Double multa;

    public Movimentacao_has_livro() {
        this.movimentacao = 0;
        this.copia_livro = new Copia_livro();
        this.status = "";
        this.data_devolucao = null;
        this.observacao = "";
        this.multa = 0.0;
    }

    /**
     * @return the movimentacao
     */
    public int getMovimentacao() {
        return movimentacao;
    }

    /**
     * @param movimentacao the movimentacao to set
     */
    public void setMovimentacao(int movimentacao) {
        this.movimentacao = movimentacao;
    }

    /**
     * @return the copia_livro
     */
    public Copia_livro getCopia_livro() {
        return copia_livro;
    }

    /**
     * @param copia_livro the copia_livro to set
     */
    public void setCopia_livro(Copia_livro copia_livro) {
        this.copia_livro = copia_livro;
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
     * @return the data_devolucao
     */
    public Date getData_devolucao() {
        return data_devolucao;
    }

    /**
     * @param data_devolucao the data_devolucao to set
     */
    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
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
     * @return the multa
     */
    public Double getMulta() {
        return multa;
    }

    /**
     * @param multa the multa to set
     */
    public void setMulta(Double multa) {
        this.multa = multa;
    }

}
