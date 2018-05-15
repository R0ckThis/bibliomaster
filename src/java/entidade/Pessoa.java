/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.ArrayList;
import util.Formatacao;

/**
 *
 * @author ramon
 */
public class Pessoa {

    private int id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String rg;
    private String email;
    private ArrayList<Telefone> telefone;

    public Pessoa() {
        this.nome = "";
        this.sobrenome = "";
        this.cpf = "";
        this.email = "";
        this.rg = "";
        this.telefone = new ArrayList<>();
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = Formatacao.formataString(cpf);
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

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = Formatacao.formataString(rg);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = Formatacao.formataString(email);
    }

    /**
     * @return the telefone
     */
    public ArrayList<Telefone> getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(ArrayList<Telefone> telefone) {
        this.telefone = telefone;
    }
}
