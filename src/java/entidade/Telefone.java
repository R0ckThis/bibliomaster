/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import util.Formatacao;

/**
 *
 * @author ramon
 */
public class Telefone {

    private int id;
    private int pessoa;
    private String ddd;
    private String numero;

    /**
     * @return the id
     */
    public Telefone() {
        this.id = 0;
        this.pessoa = 0;
        this.ddd = "";
        this.numero = "";
    }

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
    public int getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = Formatacao.formataString(numero);
    }

    /**
     * @return the ddd
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * @param ddd the ddd to set
     */
    public void setDdd(String ddd) {
        this.ddd = Formatacao.formataString(ddd);
    }

    public String toString() {
        return "(" + this.ddd + ") " + this.numero;
    }
}
