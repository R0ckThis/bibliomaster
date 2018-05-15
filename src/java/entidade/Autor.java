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
public class Autor {
    private int id;
    private String nome;
    private String sobrenome;

    /**
     * @return the id
     */
    public Autor(){
        this.id = 0;
        this.nome = "";
        this.sobrenome = "";
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
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = Formatacao.formataString(sobrenome);
    }
}
