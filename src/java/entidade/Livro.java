/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import dao.LivroDAO;
import java.util.ArrayList;
import java.util.Date;
import util.Formatacao;

/**
 *
 * @author ramon
 */
public class Livro {

    private int id;
    private String titulo;
    private Date publicacao;
    private int paginas;
    private String edicao;
    private String localizacao;
    private String capa;
    private Idioma idioma;
    private Editora editora;
    private ArrayList<Autor> autor;
    private ArrayList<Genero> genero;

    /**
     * @return the id
     */
    public Livro() {
        this.id = 0;
        this.titulo = "";
        this.publicacao = new Date();
        this.paginas = 0;
        this.edicao = "";
        this.localizacao = "";
        this.capa = "";
        this.idioma = new Idioma();
        this.editora = new Editora();
        this.autor = new ArrayList<>();
        this.genero = new ArrayList<>();
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = Formatacao.formataString(titulo);
    }

    /**
     * @return the publicacao
     */
    public Date getPublicacao() {
        return publicacao;
    }

    /**
     * @param publicacao the publicacao to set
     */
    public void setPublicacao(Date publicacao) {
        this.publicacao = publicacao;
    }

    /**
     * @return the paginas
     */
    public int getPaginas() {
        return paginas;
    }

    /**
     * @param paginas the paginas to set
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    /**
     * @return the edicao
     */
    public String getEdicao() {
        return edicao;
    }

    /**
     * @param edicao the edicao to set
     */
    public void setEdicao(String edicao) {
        this.edicao = Formatacao.formataString(edicao);
    }

    /**
     * @return the localizacao
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = Formatacao.formataString(localizacao);
    }

    /**
     * @return the estoque_total
     */
    public int getEstoque_total() {
        return new LivroDAO().estoqueTotal(this);
    }

    /**
     * @return the estoque_disponivel
     */
    public int getEstoque_disponivel() {
        return new LivroDAO().estoqueDisponivel(this);
    }

    /**
     * @return the capa
     */
    public String getCapa() {
        return capa;
    }

    /**
     * @param capa the capa to set
     */
    public void setCapa(String capa) {
        this.capa = Formatacao.formataString(capa);
    }

    /**
     * @return the idioma
     */
    public Idioma getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the editora
     */
    public Editora getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    /**
     * @return the autor
     */
    public ArrayList<Autor> getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(ArrayList<Autor> autor) {
        this.autor = autor;
    }

    /**
     * @return the genero
     */
    public ArrayList<Genero> getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(ArrayList<Genero> genero) {
        this.genero = genero;
    }

    /**
     * @return the autor
     */
}
