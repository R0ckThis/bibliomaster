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
public class Idioma {

    private int id;
    private String nome;
    private String sigla;

    /**
     * @return the id
     */
    public Idioma() {
        this.id = 0;
        this.nome = "";
        this.sigla = "";
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = Formatacao.formataString(nome);
    }

    /**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(String sigla) {
        this.sigla = Formatacao.formataString(sigla);
    }
}
