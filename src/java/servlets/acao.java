/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import apoio.Arquivo;
import apoio.ConexaoBD;
import dao.AutorDAO;
import dao.Copia_livroDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.IdiomaDAO;
import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.PessoaDAO;
import dao.UsuarioDAO;
import dao.ValorDAO;
import entidade.Autor;
import entidade.Copia_livro;
import entidade.Editora;
import entidade.Genero;
import entidade.Idioma;
import entidade.Livro;
import entidade.Movimentacao;
import entidade.Movimentacao_has_livro;
import entidade.Pessoa;
import entidade.Telefone;
import entidade.Usuario;
import entidade.Valor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.Formatacao;
import util.Util;
import util.Validacao;

/**
 *
 * @author ramon
 */
public class acao extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;
    ArrayList<String> retorno;
    private final String UPLOAD_DIRECTORY = "D:/Google Drive/Univates/2016 B/Programação para Internet/Projeto/bibliomaster/web/capa";
    private final String DIRETORIO_RELATORIOS = "D:/Google Drive/Univates/2016 B/Programação para Internet/Projeto/bibliomaster/src/java/relatorios/";
    private final String DIRETORIO_CSV = "D:/Google Drive/Univates/2016 B/Programação para Internet/Projeto/bibliomaster/web/csv/";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        requisicao = request;
        resposta = response;

        String parametro = request.getParameter("parametro");
        Usuario usuario = (Usuario) requisicao.getSession().getAttribute("usuarioLogado");
        if (usuario != null) {
            if (parametro.equals("logout")) {
                efetuarLogout();
            } else if (parametro.equals("visualizarLivro")) {
                visualizarLivro();
            } else if (parametro.equals("consultarLivros")) {
                consultarLivros();
            } else if (parametro.equals("buscarLivro")) {
                buscarLivro();
            } else if (parametro.equals("consultarMovimentacoes")) {
                consultarMovimentacoes();
            } else if (parametro.equals("csvMovimentacoes")) {
                csvMovimentacoes();
            } else if (parametro.equals("gerarCSV")) {
                gerarCSV();
            } else if (parametro.equals("cancelarReserva")) {
                cancelarReserva();
            } else if (parametro.equals("visualizarMovimentacao")) {
                visualizarMovimentacao();
            } else if (usuario.getTipo().equals("Administrador")) {
                if (parametro.equals("consultarEditoras")) {
                    consultarEditoras();
                } else if (parametro.equals("buscarEditora")) {
                    buscarEditora();
                } else if (parametro.equals("editarEditora")) {
                    editarEditora();
                } else if (parametro.equals("excluirEditora")) {
                    excluirEditora();
                } else if (parametro.equals("consultarIdiomas")) {
                    consultarIdiomas();
                } else if (parametro.equals("buscarIdioma")) {
                    buscarIdioma();
                } else if (parametro.equals("editarIdioma")) {
                    editarIdioma();
                } else if (parametro.equals("excluirIdioma")) {
                    excluirIdioma();
                } else if (parametro.equals("consultarGeneros")) {
                    consultarGeneros();
                } else if (parametro.equals("buscarGenero")) {
                    buscarGenero();
                } else if (parametro.equals("editarGenero")) {
                    editarGenero();
                } else if (parametro.equals("excluirGenero")) {
                    excluirGenero();
                } else if (parametro.equals("consultarAutores")) {
                    consultarAutores();
                } else if (parametro.equals("buscarAutor")) {
                    buscarAutor();
                } else if (parametro.equals("editarAutor")) {
                    editarAutor();
                } else if (parametro.equals("excluirAutor")) {
                    excluirAutor();
                } else if (parametro.equals("buscarPessoa")) {
                    buscarPessoa();
                } else if (parametro.equals("consultarPessoas")) {
                    consultarPessoas();
                } else if (parametro.equals("editarPessoa")) {
                    editarPessoa();
                } else if (parametro.equals("excluirPessoa")) {
                    excluirPessoa();
                } else if (parametro.equals("editarLivro")) {
                    editarLivro();
                } else if (parametro.equals("consultarCopias")) {
                    consultarCopias();
                } else if (parametro.equals("editarCopia")) {
                    editarCopia();
                } else if (parametro.equals("retirarReserva")) {
                    retirarReserva();
                } else if (parametro.equals("procederDevolucao")) {
                    procederDevolucao();
                } else if (parametro.equals("editarUsuario")) {
                    editarUsuario();
                } else if (parametro.equals("relAtraso")) {
                    relatorioAtraso();
                }
            } else {
                encaminharPagina("menu.jsp");
            }
        } else {
            encaminharPagina("index.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        requisicao = request;
        resposta = response;

        String parametro = request.getParameter("parametro");
        Usuario usuario = (Usuario) requisicao.getSession().getAttribute("usuarioLogado");
        if (parametro.equals("login")) {
            validarLogin();
        } else if (usuario != null) {
            if (parametro.equals("reservarLivro")) {
                reservarLivro();
            } else if (parametro.equals("histPessoa")) {
                historicoPessoa();
            } else if (usuario.getTipo().equals("Administrador")) {
                if (parametro.equals("cadastraIdioma")) {
                    cadastrarIdioma();
                } else if (parametro.equals("cadastraEditora")) {
                    cadastrarEditora();
                } else if (parametro.equals("cadastraGenero")) {
                    cadastrarGenero();
                } else if (parametro.equals("cadastraAutor")) {
                    cadastrarAutor();
                } else if (parametro.equals("cadastraPessoa")) {
                    cadastrarPessoa();
                } else if (parametro.equals("cadastraLivro")) {
                    cadastrarLivro();
                } else if (parametro.equals("cadastraCopiaLivro")) {
                    cadastrarCopiaLivro();
                } else if (parametro.equals("cadastraUsuario")) {
                    cadastrarUsuarioAdmin();
                } else if (parametro.equals("editarCopiaLivro")) {
                    editarCopiaLivro();
                } else if (parametro.equals("retirarLivro")) {
                    retirarLivro();
                } else if (parametro.equals("retirarReservaLivro")) {
                    retirarReservaLivro();
                } else if (parametro.equals("devolverLivro")) {
                    devolverLivro();
                } else if (parametro.equals("cadastraValor")) {
                    cadastrarValor();
                } else if (parametro.equals("relLivros")) {
                    relatorioLivros();
                } else if (parametro.equals("histLivro")) {
                    historicoLivro();
                }
            } else {
                encaminharPagina("menu.jsp");
            }
        } else if (parametro.equals("cadastraUsuarioNormal")) {
            cadastrarUsuarioNormal();
        } else {
            encaminharPagina("index.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void buscarIdioma() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Idioma idioma = (Idioma) new IdiomaDAO().consultarId(id);

        if (idioma != null) {
            try {
                // deu certo = achou o idioma
                resposta.getWriter().write(idioma.getNome());
            } catch (IOException ex) {
                Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void consultarIdiomas() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> idiomas = new IdiomaDAO().consultar(criterio);

        try {
            if (idiomas.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Nome</th><th>Sigla</th><th style=\"text-align: center\">Selecionar</th></tr></thead><tbody>");
                for (int i = 0; i < idiomas.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Idioma) idiomas.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Idioma) idiomas.get(i)).getNome() + "</td>");
                    resposta.getWriter().write("<td>" + ((Idioma) idiomas.get(i)).getSigla() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a onclick=\"selecionarIdioma(this)\" href=\"#\" data-id=\"" + ((Idioma) idiomas.get(i)).getId() + "\" data-nome=\"" + ((Idioma) idiomas.get(i)).getNome() + "\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cadastrarIdioma() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        String sigla = requisicao.getParameter("sigla");

        Idioma i = new Idioma();
        i.setId(id);
        i.setNome(nome);
        i.setSigla(sigla);

        this.retorno = new ArrayList<>();

        if (!Validacao.validaTamanhoString(i.getSigla(), 0, "maior")) {
            retorno.add("Falta a Sigla.");
        } else if (!Validacao.validaTamanhoString(i.getSigla(), 7, "menor")) {
            retorno.add("Sigla muito comprida.");
        }
        if (!Validacao.validaTamanhoString(i.getNome(), 0, "maior")) {
            retorno.add("Falta o Nome.");
        } else if (!Validacao.validaTamanhoString(i.getNome(), 46, "menor")) {
            retorno.add("Nome muito comprido.");
        }

        if (retorno.isEmpty()) {
            if (id == 0) {
                retorno.add(new IdiomaDAO().salvar(i, 'i'));
            } else {
                retorno.add(new IdiomaDAO().atualizar(i));
            }
        }

        try {
            if (retorno.get(0) == null) {
                if (id == 0) {
                    resposta.getWriter().println("Sucesso!Idioma cadastrado com sucesso!");
                } else {
                    resposta.getWriter().println("Sucesso!Idioma editado com sucesso!");
                }
            } else {
                String erro = "Erro!";
                for (int a = 0; a < retorno.size(); a++) {
                    erro = erro + retorno.get(a) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void editarIdioma() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Idioma idioma = (Idioma) new IdiomaDAO().consultarId(id);

        if (idioma != null) {
            // deu certo = achou o idioma
            requisicao.setAttribute("idioma", idioma);
            encaminharPagina("cadIdioma.jsp");
        } else {

        }
    }

    private void excluirIdioma() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));

        retorno.add(new IdiomaDAO().excluir(id));

        requisicao.setAttribute("paginaRetorno", "listaIdioma.jsp");
        requisicao.setAttribute("nomeRetorno", "Voltar para a Lista de Idiomas");
        requisicao.setAttribute("retorno", retorno);
        if (retorno.get(0) == null) {
            requisicao.setAttribute("mensagem", "Idioma excluído com sucesso!");
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void buscarGenero() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Genero genero = (Genero) new GeneroDAO().consultarId(id);

        if (genero != null) {
            try {
                // deu certo = achou o idioma
                resposta.getWriter().write(genero.getNome());
            } catch (IOException ex) {
                Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void consultarGeneros() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> generos = new GeneroDAO().consultar(criterio);

        try {
            if (generos.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Nome</th><th style=\"text-align: center\">Selecionar</th></tr></thead><tbody>");
                for (int i = 0; i < generos.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Genero) generos.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Genero) generos.get(i)).getNome() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a onclick=\"selecionarGenero(this)\" href=\"#\" data-id=\"" + ((Genero) generos.get(i)).getId() + "\" data-nome=\"" + ((Genero) generos.get(i)).getNome() + "\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cadastrarGenero() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        Genero g = new Genero();
        g.setId(id);
        g.setNome(nome);

        if (!Validacao.validaTamanhoString(g.getNome(), 0, "maior")) {
            retorno.add("Falta o Nome.");
        } else if (!Validacao.validaTamanhoString(g.getNome(), 46, "menor")) {
            retorno.add("Nome muito comprido.");
        }

        if (retorno.isEmpty()) {
            if (id == 0) {
                retorno.add(new GeneroDAO().salvar(g, 'i'));
            } else {
                retorno.add(new GeneroDAO().atualizar(g));
            }
        }

        try {
            if (retorno.get(0) == null) {
                if (id == 0) {
                    resposta.getWriter().println("Sucesso!Gênero cadastrado com sucesso!");
                } else {
                    resposta.getWriter().println("Sucesso!Gênero editado com sucesso!");
                }
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void editarGenero() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Genero genero = (Genero) new GeneroDAO().consultarId(id);

        if (genero != null) {
            // deu certo = achou a editora
            requisicao.setAttribute("genero", genero);
            encaminharPagina("cadGenero.jsp");
        } else {

        }
    }

    private void excluirGenero() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));

        retorno.add(new GeneroDAO().excluir(id));

        requisicao.setAttribute("paginaRetorno", "listaGenero.jsp");
        requisicao.setAttribute("nomeRetorno", "Voltar para a Lista de Gêneros");
        requisicao.setAttribute("retorno", retorno);

        if (retorno.get(0) == null) {
            requisicao.setAttribute("mensagem", "Gênero excluído com sucesso!");
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void consultarEditoras() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> editoras = new EditoraDAO().consultar(criterio);

        try {
            if (editoras.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Nome</th><th style=\"text-align: center\">Selecionar</th></tr></thead><tbody>");
                for (int i = 0; i < editoras.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Editora) editoras.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Editora) editoras.get(i)).getNome() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a onclick=\"selecionarEditora(this)\" href=\"#\" data-id=\"" + ((Editora) editoras.get(i)).getId() + "\" data-nome=\"" + ((Editora) editoras.get(i)).getNome() + "\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buscarEditora() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Editora editora = (Editora) new EditoraDAO().consultarId(id);

        if (editora != null) {
            try {
                // deu certo = achou a editora
                resposta.getWriter().write(editora.getNome());
            } catch (IOException ex) {
                Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void cadastrarEditora() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");

        Editora e = new Editora();
        e.setId(id);
        e.setNome(nome);

        if (!Validacao.validaTamanhoString(e.getNome(), 0, "maior")) {
            retorno.add("Falta o Nome.");
        } else if (!Validacao.validaTamanhoString(e.getNome(), 51, "menor")) {
            retorno.add("Nome muito comprido.");
        }

        if (retorno.isEmpty()) {
            if (id == 0) {
                retorno.add(new EditoraDAO().salvar(e, 'i'));
            } else {
                retorno.add(new EditoraDAO().atualizar(e));
            }
        }

        try {
            if (retorno.get(0) == null) {
                if (id == 0) {
                    resposta.getWriter().println("Sucesso!Editora cadastrada com sucesso!");
                } else {
                    resposta.getWriter().println("Sucesso!Editora editada com sucesso!");
                }
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void editarEditora() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Editora editora = (Editora) new EditoraDAO().consultarId(id);

        if (editora != null) {
            // deu certo = achou a editora
            requisicao.setAttribute("editora", editora);
            encaminharPagina("cadEditora.jsp");
        } else {

        }
    }

    private void excluirEditora() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));

        retorno.add(new EditoraDAO().excluir(id));

        requisicao.setAttribute("paginaRetorno", "listaEditora.jsp");
        requisicao.setAttribute("nomeRetorno", "Voltar para a Lista de Editoras");
        requisicao.setAttribute("retorno", retorno);

        if (retorno.get(0) == null) {
            requisicao.setAttribute("mensagem", "Editora excluída com sucesso!");
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void buscarAutor() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Autor autor = (Autor) new AutorDAO().consultarId(id);

        if (autor != null) {
            try {
                // deu certo = achou o idioma
                resposta.getWriter().write(autor.getNome() + " " + autor.getSobrenome());
            } catch (IOException ex) {
                Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void consultarAutores() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> autores = new AutorDAO().consultar(criterio);

        try {
            if (autores.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Nome</th><th>Sobrenome</th><th style=\"text-align: center\">Selecionar</th></tr></thead><tbody>");
                for (int i = 0; i < autores.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Autor) autores.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Autor) autores.get(i)).getNome() + "</td>");
                    resposta.getWriter().write("<td>" + ((Autor) autores.get(i)).getSobrenome() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a onclick=\"selecionarAutor(this)\" href=\"#\" data-id=\"" + ((Autor) autores.get(i)).getId() + "\" data-nome=\"" + ((Autor) autores.get(i)).getNome() + " " + ((Autor) autores.get(i)).getSobrenome() + "\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cadastrarAutor() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        String sobrenome = requisicao.getParameter("sobrenome");

        Autor a = new Autor();
        a.setId(id);
        a.setNome(nome);
        a.setSobrenome(sobrenome);

        if (!Validacao.validaTamanhoString(a.getNome(), 0, "maior")) {
            retorno.add("Falta o Nome.");
        } else if (!Validacao.validaTamanhoString(a.getNome(), 31, "menor")) {
            retorno.add("Nome muito comprido.");
        }

        if (!Validacao.validaTamanhoString(a.getSobrenome(), 0, "maior")) {
            retorno.add("Falta o Sobrenome.");
        } else if (!Validacao.validaTamanhoString(a.getSobrenome(), 61, "menor")) {
            retorno.add("Sobrenome muito comprido.");
        }

        if (retorno.isEmpty()) {
            if (id == 0) {
                retorno.add(new AutorDAO().salvar(a, 'i'));
            } else {
                retorno.add(new AutorDAO().atualizar(a));
            }
        }
        try {
            if (retorno.get(0) == null) {
                if (id == 0) {
                    resposta.getWriter().println("Sucesso!Autor cadastrado com sucesso!");
                } else {
                    resposta.getWriter().println("Sucesso!Autor editado com sucesso!");
                }
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarAutor() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Autor autor = (Autor) new AutorDAO().consultarId(id);

        if (autor != null) {
            requisicao.setAttribute("autor", autor);
            encaminharPagina("cadAutor.jsp");
        } else {

        }
    }

    private void excluirAutor() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));

        retorno.add(new AutorDAO().excluir(id));

        requisicao.setAttribute("paginaRetorno", "listaAutor.jsp");
        requisicao.setAttribute("nomeRetorno", "Voltar para a Lista de Autores");
        requisicao.setAttribute("retorno", retorno);

        if (retorno.get(0) == null) {
            requisicao.setAttribute("mensagem", "Autor excluído com sucesso!");
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void cadastrarPessoa() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        String sobrenome = requisicao.getParameter("sobrenome");
        String cpf = requisicao.getParameter("cpf");
        String rg = requisicao.getParameter("rg");
        String email = requisicao.getParameter("email");
        ArrayList<Telefone> telefone = new ArrayList<>();
        String[] telefones = requisicao.getParameterValues("telefone");
        if (telefones != null) {
            for (int i = 0; i < telefones.length; i++) {
                String telAtual = telefones[i];
                if (telAtual.length() > 0) {
                    boolean valido = true;
                    String ddd = "";
                    String numero = "";
                    if (telAtual.length() == 13) {
                        ddd = telAtual.substring(1, 3).replaceAll(" ", "");
                        numero = telAtual.substring(5, 13).replaceAll(" ", "");
                    } else if (telAtual.length() == 14) {
                        ddd = telAtual.substring(1, 3).replaceAll(" ", "");
                        numero = telAtual.substring(5, 14).replaceAll(" ", "");
                    } else {
                        valido = false;
                    }
                    if (valido) {
                        boolean incluso = false;
                        for (int j = 0; j < telefone.size(); j++) {
                            if (telefone.get(j).getDdd().equals(ddd) && telefone.get(j).getNumero().equals(numero)) {
                                incluso = true;
                                j = telefone.size();
                            }
                        }
                        if (!incluso) {
                            Telefone t = new Telefone();
                            t.setPessoa(id);
                            t.setDdd(ddd);
                            t.setNumero(numero);
                            telefone.add(t);
                        }
                    } else {
                        retorno.add("Telefone: " + telAtual + " inválido.");
                    }
                }
            }
        }

        Pessoa p = new Pessoa();
        p.setId(id);
        p.setNome(nome);
        p.setSobrenome(sobrenome);
        p.setCpf(cpf);
        p.setRg(rg);
        p.setEmail(email);
        p.setTelefone(telefone);

        if (!Validacao.validaTamanhoString(p.getNome(), 0, "maior")) {
            retorno.add("Falta o Nome.");
        } else if (!Validacao.validaTamanhoString(p.getNome(), 31, "menor")) {
            retorno.add("Nome muito comprido.");
        }

        if (!Validacao.validaTamanhoString(p.getSobrenome(), 0, "maior")) {
            retorno.add("Falta o Sobrenome.");
        } else if (!Validacao.validaTamanhoString(p.getSobrenome(), 61, "menor")) {
            retorno.add("Sobrenome muito comprido.");
        }
        if (!Validacao.validarCPF(p.getCpf())) {
            retorno.add("CPF inválido.");
        } else if (id == 0) {
            if (new PessoaDAO().verificaCpfExistente(p.getCpf()).equals("true")) {
                retorno.add("CPF já cadastrado.");
            }
        }
        if (!Validacao.validaTamanhoString(p.getRg(), 0, "maior")) {
            retorno.add("Falta o RG.");
        } else if (!Validacao.validaTamanhoString(p.getRg(), 12, "menor")) {
            retorno.add("RG muito comprido.");
        }
        if (!p.getEmail().equals("")) {
            if (!Validacao.validarEmail(p.getEmail())) {
                retorno.add("Email inválido.");
            }
        }

        if (retorno.isEmpty()) {
            if (id == 0) {
                retorno.add(new PessoaDAO().salvar(p, 'i'));
            } else {
                retorno.add(new PessoaDAO().atualizar(p));
            }
        }

        try {
            if (retorno.get(0) == null) {
                if (id == 0) {
                    resposta.getWriter().println("Sucesso!Pessoa cadastrada com sucesso!");
                } else {
                    resposta.getWriter().println("Sucesso!Pessoa editada com sucesso!");
                }
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buscarPessoa() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Pessoa pessoa = (Pessoa) new PessoaDAO().consultarId(id);

        if (pessoa.getNome() != "") {
            try {
                resposta.getWriter().write(pessoa.getNome() + " " + pessoa.getSobrenome());
            } catch (IOException ex) {
                Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void consultarPessoas() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> pessoas = new PessoaDAO().consultar(criterio);

        try {
            if (pessoas.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Nome completo</th><th>CPF</th><th style=\"text-align: center\">Selecionar</th></tr></thead><tbody>");
                for (int i = 0; i < pessoas.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Pessoa) pessoas.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Pessoa) pessoas.get(i)).getNome() + " " + ((Pessoa) pessoas.get(i)).getSobrenome() + "</td>");
                    resposta.getWriter().write("<td>" + ((Pessoa) pessoas.get(i)).getCpf() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a onclick=\"selecionarPessoa(this)\" href=\"#\" data-id=\"" + ((Pessoa) pessoas.get(i)).getId() + "\" data-nome=\"" + ((Pessoa) pessoas.get(i)).getNome() + " " + ((Pessoa) pessoas.get(i)).getSobrenome() + "\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarPessoa() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Pessoa pessoa = (Pessoa) new PessoaDAO().consultarId(id);

        if (pessoa != null) {
            requisicao.setAttribute("pessoa", pessoa);
            requisicao.setAttribute("readonly", "readonly");
            encaminharPagina("cadPessoa.jsp");
        } else {

        }
    }

    private void excluirPessoa() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));

        retorno.add(new PessoaDAO().excluir(id));

        requisicao.setAttribute("paginaRetorno", "listaPessoa.jsp");
        requisicao.setAttribute("nomeRetorno", "Voltar para a Lista de Pessoas");
        requisicao.setAttribute("retorno", retorno);

        if (retorno.get(0) == null) {
            requisicao.setAttribute("mensagem", "Pessoa excluída com sucesso!");
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void cadastrarUsuarioAdmin() {
        this.retorno = new ArrayList<>();

        int pessoa = Integer.parseInt(requisicao.getParameter("pessoa"));
        String senha = requisicao.getParameter("senha");
        String tipo = requisicao.getParameter("tipo");
        String ativo = requisicao.getParameter("ativo");
        String editar = requisicao.getParameter("editar");

        UsuarioDAO uDAO = new UsuarioDAO();

        Usuario u = new Usuario();
        if (editar.equals("true")) {
            u = (Usuario) uDAO.consultarId(pessoa);
            if (!senha.isEmpty()) {
                u.setSenha(senha);
            }
        } else {
            if (senha.isEmpty()) {
                retorno.add("Defina uma senha!");
            }
            u.setSenha(senha);
        }
        u.setPessoa((Pessoa) new PessoaDAO().consultarId(pessoa));
        u.setTipo(tipo);
        if (ativo == null) {
            u.setAtivo(false);
        } else {
            u.setAtivo(true);
        }

        if (retorno.isEmpty()) {
            if (uDAO.pessoaCadastrada(u)) {
                if (editar.equals("true")) {
                    retorno.add(uDAO.atualizar(u));
                } else {
                    retorno.add("Pessoa com usuário já cadastrado. Edite-o.");
                }
            } else {
                retorno.add(uDAO.salvar(u, 'i'));
            }
        }

        try {
            if (retorno.get(0) == null) {
                if (editar.equals("true")) {
                    resposta.getWriter().println("Sucesso!Usuário atualizado com sucesso!");
                } else {
                    resposta.getWriter().println("Sucesso!Usuário cadastrado com sucesso!");
                }
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void cadastrarCopiaLivro() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("livro"));
        int quantidade = Integer.parseInt(requisicao.getParameter("quantidade"));

        Copia_livro cl = new Copia_livro();

        cl.setLivro((Livro) new LivroDAO().consultarId(id));
        Copia_livroDAO clDAO = new Copia_livroDAO();
        for (int i = 0; i < quantidade; i++) {
            String erro = clDAO.salvar(cl, 'i');
            if (erro != null) {
                retorno.add(erro);
            }
        }

        try {
            if (retorno.isEmpty()) {
                resposta.getWriter().println("Sucesso!Cópias incluídas com sucesso!");
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarCopiaLivro() {
        this.retorno = new ArrayList<>();

        Livro livro = (Livro) new LivroDAO().consultarId(Integer.parseInt(requisicao.getParameter("livro")));
        int identificador = Integer.parseInt(requisicao.getParameter("identificador"));
        String estado = requisicao.getParameter("estado");
        String observacao = requisicao.getParameter("observacao");

        Copia_livro cl = new Copia_livro();
        cl.setLivro(livro);
        cl.setIdentificador(identificador);
        cl.setEstado(estado);
        cl.setObservacao(observacao);

        retorno.add(new Copia_livroDAO().atualizar(cl));

        try {
            if (retorno.get(0) == null) {
                resposta.getWriter().println("Sucesso!Cópia editada com sucesso!");
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cadastrarLivro() {

        if (ServletFileUpload.isMultipartContent(requisicao)) {
            Livro l = new Livro();
            ArrayList<Autor> autor = new ArrayList<>();
            AutorDAO autorDAO = new AutorDAO();
            ArrayList<Genero> genero = new ArrayList<>();
            GeneroDAO generoDAO = new GeneroDAO();

            this.retorno = new ArrayList<>();

            try {
                List<FileItem> itens = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(requisicao);
                boolean possuiAutorInvalido = false;
                boolean possuiGeneroInvalido = false;
                for (FileItem item : itens) {
                    if (item.isFormField()) {

                        if ("id".equals(item.getFieldName())) {
                            int id = Integer.parseInt(item.getString());
                            if (id == 0) {
                                l.setId(id);
                            } else {
                                l = (Livro) new LivroDAO().consultarId(id);
                            }
                        } else if ("titulo".equals(item.getFieldName())) {
                            l.setTitulo(item.getString("UTF-8"));
                        } else if ("publicacao".equals(item.getFieldName())) {
                            l.setPublicacao(Formatacao.transformarParaDataWeb(item.getString()));
                        } else if ("paginas".equals(item.getFieldName())) {
                            if (!item.getString().equals("")) {
                                l.setPaginas(Integer.parseInt(item.getString()));
                            }
                        } else if ("edicao".equals(item.getFieldName())) {
                            l.setEdicao(item.getString("UTF-8"));
                        } else if ("localizacao".equals(item.getFieldName())) {
                            l.setLocalizacao(item.getString("UTF-8"));
                        } else if ("idioma".equals(item.getFieldName())) {
                            Idioma i = (Idioma) new IdiomaDAO().consultarId(Integer.parseInt(item.getString()));
                            if (i == null) {
                                retorno.add("Idioma não encontrado.");
                            } else {
                                l.setIdioma(i);
                            }
                        } else if ("editora".equals(item.getFieldName())) {
                            Editora e = (Editora) new EditoraDAO().consultarId(Integer.parseInt(item.getString()));
                            if (e == null) {
                                retorno.add("Editora não encontrada.");
                            } else {
                                l.setEditora(e);
                            }
                        } else if ("autor".equals(item.getFieldName())) {
                            Autor a = (Autor) autorDAO.consultarId(Integer.parseInt(item.getString()));
                            if (a == null) {
                                if (!possuiAutorInvalido) {
                                    retorno.add("Autor não encontrado.");
                                    possuiAutorInvalido = true;
                                }
                            } else {
                                boolean incluso = false;
                                for (int i = 0; i < autor.size(); i++) {
                                    if (autor.get(i).getId() == a.getId()) {
                                        incluso = true;
                                    }
                                }
                                if (!incluso) {
                                    autor.add(a);
                                }
                            }
                        } else if ("genero".equals(item.getFieldName())) {
                            Genero g = (Genero) generoDAO.consultarId(Integer.parseInt(item.getString()));
                            if (g == null) {
                                if (!possuiGeneroInvalido) {
                                    retorno.add("Gênero não encontrado.");
                                    possuiGeneroInvalido = true;
                                }
                            } else {
                                boolean incluso = false;
                                for (int i = 0; i < genero.size(); i++) {
                                    if (genero.get(i).getId() == g.getId()) {
                                        incluso = true;
                                    }
                                }
                                if (!incluso) {
                                    genero.add(g);
                                }
                            }
                        }
                    }
                }
                for (FileItem item : itens) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        if (!name.isEmpty()) {
                            String extencao = name.substring(name.lastIndexOf('.') + 1);
                            if (extencao.equalsIgnoreCase("jpg") || extencao.equalsIgnoreCase("jpeg") || extencao.equalsIgnoreCase("png")) {
                                if (l.getId() != 0) {
                                    name = l.getId() + "." + extencao;
                                }
                                l.setCapa("capa/" + name);
                                item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                            } else {
                                this.retorno.add("Arquivo de capa inválido!");
                            }
                        }
                    }
                }
                //File uploaded successfully
            } catch (Exception ex) {
                retorno.add(ex + "");
            }
            l.setGenero(genero);
            l.setAutor(autor);
            if (retorno.isEmpty()) {
                if (l.getId() == 0) {
                    retorno.add(new LivroDAO().salvar(l, 'i'));
                    requisicao.setAttribute("mensagem", "Livro cadastrado com sucesso!");
                } else {
                    retorno.add(new LivroDAO().atualizar(l));
                    requisicao.setAttribute("mensagem", "Livro editado com sucesso!");
                }
            }

            try {
                if (retorno.get(0) == null) {
                    if (l.getId() == 0) {
                        resposta.getWriter().println("Sucesso!Livro cadastrado com sucesso!");
                    } else {
                        resposta.getWriter().println("Sucesso!Livro editado com sucesso!");
                    }
                } else {
                    String erro = "Erro!";
                    for (int i = 0; i < retorno.size(); i++) {
                        erro = erro + retorno.get(i) + "\n";
                    }
                    resposta.getWriter().println(erro);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            requisicao.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
            System.out.println("Sorry this Servlet only handles file upload request");
        }
    }

    private void buscarLivro() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Livro livro = (Livro) new LivroDAO().consultarId(id);

        if (livro.getTitulo() != "") {
            try {
                resposta.getWriter().write(livro.getTitulo() + " - " + livro.getEditora().getNome());
            } catch (IOException ex) {
                Logger.getLogger(acao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void consultarLivros() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> livros = new LivroDAO().consultar(criterio);

        try {
            if (livros.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Título</th><th>Est. Disp.</th><th>Idioma</th><th>Editora</th><th>Autor(es)</th><th>Gênero(s)</th><th style=\"text-align: center\">Selecionar</th><th style=\"text-align: center\">Visualizar</th></tr></thead><tbody>");
                for (int i = 0; i < livros.size(); i++) {
                    resposta.getWriter().write("<tr><td style=\"vertical-align: middle;\">" + ((Livro) livros.get(i)).getTitulo() + "</td>");
                    resposta.getWriter().write("<td style=\"vertical-align: middle;\" align=\"center\">" + ((Livro) livros.get(i)).getEstoque_disponivel() + "</td>");
                    resposta.getWriter().write("<td style=\"vertical-align: middle;\">" + ((Livro) livros.get(i)).getIdioma().getNome() + "</td>");
                    resposta.getWriter().write("<td style=\"vertical-align: middle;\">" + ((Livro) livros.get(i)).getEditora().getNome() + "</td>");
                    String autores = "<td style=\"vertical-align: middle;\">";
                    for (Autor autor : ((Livro) livros.get(i)).getAutor()) {
                        autores = autores + autor.getNome() + " " + autor.getSobrenome() + "<br>";
                    }
                    autores = autores + "</td>";
                    resposta.getWriter().write(autores);

                    String generos = "<td style=\"vertical-align: middle;\">";
                    for (Genero genero : ((Livro) livros.get(i)).getGenero()) {
                        generos = generos + genero.getNome() + "<br>";
                    }
                    generos = generos + "</td>";
                    resposta.getWriter().write(generos);

                    resposta.getWriter().write("<td style=\"vertical-align: middle;\" align=\"center\"><a onclick=\"selecionarLivro(this)\" href=\"#\" data-id=\"" + ((Livro) livros.get(i)).getId() + "\" data-nome=\"" + ((Livro) livros.get(i)).getTitulo() + " - " + ((Livro) livros.get(i)).getEditora().getNome() + "\" data-dismiss=\"modal\"><span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span></a> </td>");
                    resposta.getWriter().write("<td style=\"vertical-align: middle;\" align=\"center\"><a href=\"#\" onclick=\"visualizaLivro('" + ((Livro) livros.get(i)).getId() + "', '" + ((Livro) livros.get(i)).getTitulo() + " - " + ((Livro) livros.get(i)).getEditora().getNome() + "')\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarLivro() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Livro livro = (Livro) new LivroDAO().consultarId(id);

        if (livro != null) {
            requisicao.setAttribute("livro", livro);
            encaminharPagina("cadLivro.jsp");
        } else {

        }
    }

    private void consultarCopias() {
        String criterio = Formatacao.formataString(requisicao.getParameter("nome"));
        ArrayList<Object> copias = new Copia_livroDAO().consultarLivro(criterio);

        try {
            if (copias.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Identificador</th><th>Estado</th><th>Observação</th><th style=\"text-align: center\">Editar</th></tr></thead><tbody>");
                for (int i = 0; i < copias.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Copia_livro) copias.get(i)).getIdentificador() + "</td>");
                    resposta.getWriter().write("<td>" + ((Copia_livro) copias.get(i)).getEstado() + "</td>");
                    resposta.getWriter().write("<td>" + ((Copia_livro) copias.get(i)).getObservacao() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=editarCopia&livro=" + ((Copia_livro) copias.get(i)).getLivro().getId() + "&identificador=" + ((Copia_livro) copias.get(i)).getIdentificador() + "\"><span class=\"glyphicon glyphicon-edit\" aria-hidden=\"true\"></span></a> </td>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarCopia() {
        int livro = Integer.parseInt(requisicao.getParameter("livro"));

        int identificador = Integer.parseInt(requisicao.getParameter("identificador"));

        Copia_livro cl = (Copia_livro) new Copia_livroDAO().consultarUnico(livro, identificador);

        if (cl != null) {
            // deu certo = achou a copia
            requisicao.setAttribute("cl", cl);
            encaminharPagina("editCopia.jsp");
        } else {
            this.retorno = new ArrayList<>();
            retorno.add("Livro não encontrado!");
            requisicao.setAttribute("retorno", retorno);
            requisicao.setAttribute("paginaRetorno", "listaCopiaLivro.jsp");
            encaminharPagina("erro.jsp");
        }
    }

    private void editarUsuario() {
        int pessoa = Integer.parseInt(requisicao.getParameter("pessoa"));

        Usuario usuario = (Usuario) new UsuarioDAO().consultarId(pessoa);

        if (usuario != null) {
            // deu certo = achou o usuario
            requisicao.setAttribute("usuario", usuario);
            encaminharPagina("cadUsuarioAdmin.jsp");
        } else {
            this.retorno = new ArrayList<>();
            retorno.add("Usuário não encontrado!");
            requisicao.setAttribute("retorno", retorno);
            requisicao.setAttribute("paginaRetorno", "listaUsuario.jsp");
            encaminharPagina("erro.jsp");
        }
    }

    private void visualizarLivro() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Livro livro = (Livro) new LivroDAO().consultarId(id);

        if (livro != null) {
            // deu certo = achou o livro
            requisicao.setAttribute("livro", livro);
            encaminharPagina("visualizarLivro.jsp");
        } else {
            this.retorno = new ArrayList<>();
            retorno.add("Livro não encontrado.");
            requisicao.setAttribute("paginaRetorno", "listaLivros.jsp");
            requisicao.setAttribute("nomeRetorno", "Voltar para a Lista de Livros");
            encaminharPagina("erro.jsp");
        }
    }

    private void cancelarReserva() {
        this.retorno = new ArrayList<>();
        requisicao.setAttribute("nomeRetorno", "Voltar para lista de movimentações");
        requisicao.setAttribute("paginaRetorno", "listaMovimentacao.jsp");
        int idMovimentacao = Integer.parseInt(requisicao.getParameter("id"));
        MovimentacaoDAO movDAO = new MovimentacaoDAO();
        Movimentacao mov = (Movimentacao) movDAO.consultarId(idMovimentacao);
        if (!mov.getStatus().equals("")) {
            if (mov.getStatus().equals("Reserva")) {
                mov.setStatus("Cancelado");
                for (Movimentacao_has_livro mhl : mov.getLivros()) {
                    mhl.setStatus("Cancelado");
                    mhl.getCopia_livro().setEstado("Disponível");
                }
                retorno.add(movDAO.atualizar(mov));
                if (retorno.get(0) == null) {
                    requisicao.setAttribute("mensagem", "Reserva cancelada com sucesso!");
                    requisicao.setAttribute("movimentacao", mov);
                    encaminharPagina("visualizaMovimentacao.jsp");
                } else {
                    requisicao.setAttribute("retorno", retorno);
                    encaminharPagina("erro.jsp");
                }
            } else {
                retorno.add("Movimentação não está em reserva.");
                requisicao.setAttribute("retorno", retorno);
                encaminharPagina("erro.jsp");
            }
        } else {
            retorno.add("Movimentação não encontrada.");
            requisicao.setAttribute("retorno", retorno);
            encaminharPagina("erro.jsp");
        }
    }

    private void reservarLivro() {
        this.retorno = new ArrayList<>();
        int pessoa = Integer.parseInt(requisicao.getParameter("pessoa"));
        MovimentacaoDAO movDAO = new MovimentacaoDAO();
        if (!movDAO.temPendencias(pessoa)) {
            String observacao = requisicao.getParameter("observacao");
            Date data_reserva = null;
            try {
                data_reserva = Formatacao.transformarParaDataWeb(requisicao.getParameter("data_reserva"));
            } catch (Exception e) {
                this.retorno.add(e.toString());
            }
            String[] livro = requisicao.getParameterValues("livro");
            String[] observacaoLivro = requisicao.getParameterValues("observacaoLivro");

            Movimentacao mov = new Movimentacao();
            mov.setPessoa((Pessoa) new PessoaDAO().consultarId(pessoa));
            mov.setObservacao(observacao);
            mov.setData_reserva(data_reserva);
            mov.setStatus("Reserva");

            ArrayList<Livro> livros = new ArrayList<>();
            LivroDAO lDAO = new LivroDAO();

            for (String livro1 : livro) {
                boolean incluso = false;
                Livro livroAtual = (Livro) lDAO.consultarId(Integer.parseInt(livro1));
                for (Livro livro2 : livros) {
                    if (livroAtual == livro2) {
                        incluso = true;
                    }
                }
                if (!incluso) {
                    livros.add(livroAtual);
                }
            }
            livros.stream().filter((livroAtual) -> (livroAtual.getEstoque_disponivel() == 0)).forEach((livroAtual) -> {
                retorno.add("O Livro: " + livroAtual.getTitulo() + " - " + livroAtual.getEditora().getNome()
                        + ", não possui estoque disponível.");
            });

            ArrayList<Movimentacao_has_livro> mhls = new ArrayList<>();
            if (retorno.isEmpty()) {
                Copia_livroDAO clDAO = new Copia_livroDAO();
                for (Livro livroAtual : livros) {
                    Copia_livro clAtual = clDAO.pegarCopiaDisponivel(livroAtual);
                    if (clAtual.getIdentificador() == 0) {
                        retorno.add("O Livro: " + livroAtual.getTitulo() + " - " + livroAtual.getEditora().getNome()
                                + ", não possui estoque disponível.");
                    } else {

                        Movimentacao_has_livro mhlAtual = new Movimentacao_has_livro();
                        for (int i = 0; i < livro.length; i++) {
                            if (livro[i].equals(livroAtual.getId() + "")) {
                                mhlAtual.setObservacao(observacaoLivro[i]);
                                i = livro.length;
                            }
                        }
                        clAtual.setEstado("Reservado");
                        mhlAtual.setStatus("Reservado");
                        mhlAtual.setCopia_livro(clAtual);
                        mhls.add(mhlAtual);
                    }
                }
            }
            if (retorno.isEmpty()) {
                mov.setLivros(mhls);
                retorno.add(movDAO.salvar(mov, 'i'));
            }
        } else {
            retorno.add("Esta pessoa possui Pendências!");
        }

        requisicao.setAttribute("paginaRetorno", "cadReserva.jsp");
        requisicao.setAttribute("retorno", retorno);

        try {
            int idMov = Integer.parseInt(retorno.get(0));
            resposta.getWriter().println("/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + idMov);
        } catch (Exception e) {
            try {
                String erro = "Erro!";
                for (int a = 0; a < retorno.size(); a++) {
                    erro = erro + retorno.get(a) + "\n";
                }
                resposta.getWriter().println(erro);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void retirarLivro() {
        this.retorno = new ArrayList<>();

        int pessoa = Integer.parseInt(requisicao.getParameter("pessoa"));
        MovimentacaoDAO movDAO = new MovimentacaoDAO();
        if (!movDAO.temPendencias(pessoa)) {
            String observacao = requisicao.getParameter("observacao");
            Date data_retirada = null;
            Date data_devolver = null;
            try {
                data_retirada = Formatacao.transformarParaDataWeb(requisicao.getParameter("data_retirada"));
                data_devolver = Formatacao.transformarParaDataWeb(requisicao.getParameter("data_devolver"));
            } catch (Exception e) {
                this.retorno.add(e.toString());
            }
            String[] livro = requisicao.getParameterValues("livro");
            String[] observacaoLivro = requisicao.getParameterValues("observacaoLivro");

            Movimentacao mov = new Movimentacao();
            mov.setPessoa((Pessoa) new PessoaDAO().consultarId(pessoa));
            mov.setObservacao(observacao);
            mov.setData_retirada(data_retirada);
            mov.setData_devolver(data_devolver);
            mov.setStatus("Retirado");

            ArrayList<Livro> livros = new ArrayList<>();
            LivroDAO lDAO = new LivroDAO();

            for (String livro1 : livro) {
                boolean incluso = false;
                Livro livroAtual = (Livro) lDAO.consultarId(Integer.parseInt(livro1));
                for (Livro livro2 : livros) {
                    if (livroAtual.getId() == livro2.getId()) {
                        incluso = true;
                    }
                }
                if (!incluso) {
                    livros.add(livroAtual);
                }
            }
            livros.stream().filter((livroAtual) -> (livroAtual.getEstoque_disponivel() == 0)).forEach((livroAtual) -> {
                retorno.add("O Livro: " + livroAtual.getTitulo() + " - " + livroAtual.getEditora().getNome()
                        + ", não possui estoque disponível.");
            });

            ArrayList<Movimentacao_has_livro> mhls = new ArrayList<>();
            if (retorno.isEmpty()) {
                Copia_livroDAO clDAO = new Copia_livroDAO();
                for (Livro livroAtual : livros) {
                    Copia_livro clAtual = clDAO.pegarCopiaDisponivel(livroAtual);
                    if (clAtual.getIdentificador() == 0) {
                        retorno.add("O Livro: " + livroAtual.getTitulo() + " - " + livroAtual.getEditora().getNome()
                                + ", não possui estoque disponível.");
                    } else {

                        Movimentacao_has_livro mhlAtual = new Movimentacao_has_livro();
                        for (int i = 0; i < livro.length; i++) {
                            if (livro[i].equals(livroAtual.getId() + "")) {
                                mhlAtual.setObservacao(observacaoLivro[i]);
                                i = livro.length;
                            }
                        }
                        clAtual.setEstado("Retirado");
                        mhlAtual.setStatus("Retirado");
                        mhlAtual.setCopia_livro(clAtual);
                        mhls.add(mhlAtual);
                    }
                }
            }

            if (retorno.isEmpty()) {
                mov.setLivros(mhls);
                int diasUteis = Integer.parseInt(Util.calculaDiasUteis(mov.getData_retirada(), mov.getData_devolver()) + "");
                ValorDAO v = new ValorDAO();
                mov.setValor(v.pegarValor().getDiaria() * diasUteis * mov.getLivros().size());
                retorno.add(new MovimentacaoDAO().salvar(mov, 'i'));
                requisicao.setAttribute("mensagem", "Retirada efetuada com sucesso!");
            }
        } else {
            retorno.add("Esta pessoa possui Pendências!");
        }
        requisicao.setAttribute("retorno", retorno);

        try {
            int idMov = Integer.parseInt(retorno.get(0));
            resposta.getWriter().println("/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + idMov);
        } catch (Exception e) {
            try {
                String erro = "Erro!";
                for (int a = 0; a < retorno.size(); a++) {
                    erro = erro + retorno.get(a) + "\n";
                }
                resposta.getWriter().println(erro);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void devolverLivro() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));
        String observacao = requisicao.getParameter("observacao");

        String[] livro = requisicao.getParameterValues("livro");
        String[] identificador = requisicao.getParameterValues("identificador");
        String[] observacaoLivro = requisicao.getParameterValues("observacaoLivro");
        String[] data_devolucao = requisicao.getParameterValues("data_devolucao");
        String[] multa = requisicao.getParameterValues("multa");
        int qtdeLivros = Integer.parseInt(requisicao.getParameter("qtdeLivros"));
        ArrayList<String> devolver = new ArrayList<>();
        for (int i = 0; i <= qtdeLivros; i++) {
            devolver.add(requisicao.getParameter("devolver" + i));
        }

        Movimentacao mov = (Movimentacao) new MovimentacaoDAO().consultarId(id);
        mov.setObservacao(observacao);

        ArrayList<Movimentacao_has_livro> mhls = new ArrayList<>();
        Copia_livroDAO clDAO = new Copia_livroDAO();

        int qtdeDevolvidos = 0;
        for (int i = 0; i < livro.length; i++) {
            Movimentacao_has_livro mhlAtual = new Movimentacao_has_livro();
            mhlAtual.setMovimentacao(mov.getId());
            if (devolver.get(i) == null) {
                Copia_livro clAtual = (Copia_livro) clDAO.consultarUnico(Integer.parseInt(livro[i]), Integer.parseInt(identificador[i]));
                mhlAtual.setObservacao(observacaoLivro[i]);
                mhlAtual.setCopia_livro(clAtual);
                mhlAtual.setStatus("Retirado");
                mhls.add(mhlAtual);
            } else {
                qtdeDevolvidos++;
                Copia_livro clAtual = (Copia_livro) clDAO.consultarUnico(Integer.parseInt(livro[i]), Integer.parseInt(identificador[i]));
                clAtual.setEstado("Disponível");
                try {
                    mhlAtual.setData_devolucao(Formatacao.transformarParaDataWeb(data_devolucao[i]));
                } catch (Exception e) {
                    retorno.add(e.toString());
                }
                Double mult = 0.0;
                if (!multa[i].isEmpty()) {
                    mult = Double.parseDouble(multa[i]);
                }
                mhlAtual.setMulta(mult);
                mhlAtual.setStatus("Devolvido");
                mhlAtual.setObservacao(observacaoLivro[i]);
                mhlAtual.setCopia_livro(clAtual);
                mhls.add(mhlAtual);
            }
        }

        if (qtdeDevolvidos == 0) {
            retorno.add("Nenhum livro marcado para devolução!");
        }

        int retirado = 0;
        for (Movimentacao_has_livro mhl : mhls) {
            if (mhl.getStatus().equals("Retirado")) {
                retirado++;
            }
        }
        if (retirado == 0) {
            mov.setStatus("Fechado");
        }

        if (retorno.isEmpty()) {
            mov.setLivros(mhls);
            retorno.add(new MovimentacaoDAO().atualizar(mov));
        }

        try {
            if (retorno.get(0) == null) {
                resposta.getWriter().println("/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + mov.getId());
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void retirarReservaLivro() {
        this.retorno = new ArrayList<>();

        int id = Integer.parseInt(requisicao.getParameter("id"));
        int pessoa = Integer.parseInt(requisicao.getParameter("pessoa"));
        String observacao = requisicao.getParameter("observacao");
        Date data_reserva = null;
        Date data_retirada = null;
        Date data_devolver = null;
        try {
            data_reserva = Formatacao.transformarParaDataWeb(requisicao.getParameter("data_reserva"));
            data_retirada = Formatacao.transformarParaDataWeb(requisicao.getParameter("data_retirada"));
            data_devolver = Formatacao.transformarParaDataWeb(requisicao.getParameter("data_devolver"));
        } catch (Exception e) {
            this.retorno.add("Data informada inválida!");
        }
        String[] livro = requisicao.getParameterValues("livro");
        String[] identificador = requisicao.getParameterValues("identificador");
        String[] observacaoLivro = requisicao.getParameterValues("observacaoLivro");
        int qtdeLivros = Integer.parseInt(requisicao.getParameter("qtdeLivros"));
        ArrayList<String> retirar = new ArrayList<>();
        for (int i = 0; i <= qtdeLivros; i++) {
            retirar.add(requisicao.getParameter("retirar" + i));
        }

        Movimentacao mov = new Movimentacao();
        mov.setId(id);
        mov.setPessoa((Pessoa) new PessoaDAO().consultarId(pessoa));
        mov.setObservacao(observacao);
        mov.setData_reserva(data_reserva);
        mov.setData_retirada(data_retirada);
        mov.setData_devolver(data_devolver);
        mov.setStatus("Retirado");

        ArrayList<Movimentacao_has_livro> mhls = new ArrayList<>();
        Copia_livroDAO clDAO = new Copia_livroDAO();

        int qtdeRetirados = 0;
        for (int i = 0; i < livro.length; i++) {
            Movimentacao_has_livro mhlAtual = new Movimentacao_has_livro();
            mhlAtual.setMovimentacao(id);
            if (retirar.get(i) == null) {
                Copia_livro clAtual = (Copia_livro) clDAO.consultarUnico(Integer.parseInt(livro[i]), Integer.parseInt(identificador[i]));
                clAtual.setEstado("Disponível");
                mhlAtual.setStatus("Não Retirado");
                mhlAtual.setObservacao(observacaoLivro[i]);
                mhlAtual.setCopia_livro(clAtual);
                mhls.add(mhlAtual);
            } else {
                qtdeRetirados++;
                Copia_livro clAtual = (Copia_livro) clDAO.consultarUnico(Integer.parseInt(livro[i]), Integer.parseInt(identificador[i]));
                clAtual.setEstado("Retirado");
                mhlAtual.setStatus("Retirado");
                mhlAtual.setObservacao(observacaoLivro[i]);
                mhlAtual.setCopia_livro(clAtual);
                mhls.add(mhlAtual);
            }
        }

        if (qtdeRetirados == 0) {
            retorno.add("Nenhum livro retirado. Cancele a reserva!");
        }

        if (retorno.isEmpty()) {
            mov.setLivros(mhls);
            int diasUteis = Integer.parseInt(Util.calculaDiasUteis(mov.getData_retirada(), mov.getData_devolver()) + "");
            ValorDAO v = new ValorDAO();
            mov.setValor(v.pegarValor().getDiaria() * diasUteis * qtdeRetirados);
            retorno.add(new MovimentacaoDAO().atualizar(mov));
            requisicao.setAttribute("mensagem", "Retirada efetuada com sucesso!");
        }

        requisicao.setAttribute("retorno", retorno);

        try {
            if (retorno.get(0) == null) {
                resposta.getWriter().println("/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + mov.getId());
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void retirarReserva() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Movimentacao mov = (Movimentacao) new MovimentacaoDAO().consultarId(id);

        if (mov.getStatus().equals("Reserva")) {
            // deu certo = achou a reserva
            requisicao.setAttribute("movimentacao", mov);
            encaminharPagina("retirarReserva.jsp");
        } else {
            this.retorno = new ArrayList<>();
            retorno.add("Reserva não encontrada!");
            requisicao.setAttribute("retorno", retorno);
            requisicao.setAttribute("paginaRetorno", "listaMovimentacao.jsp");
            encaminharPagina("erro.jsp");
        }
    }

    private void procederDevolucao() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Movimentacao mov = (Movimentacao) new MovimentacaoDAO().consultarId(id);

        if (mov.getStatus().equals("Retirado")) {
            // deu certo = achou a retirada
            requisicao.setAttribute("movimentacao", mov);
            encaminharPagina("devolverLivro.jsp");
        } else {
            this.retorno = new ArrayList<>();
            retorno.add("Retirada não encontrada!");
            requisicao.setAttribute("retorno", retorno);
            requisicao.setAttribute("paginaRetorno", "listaMovimentacao.jsp");
            encaminharPagina("erro.jsp");
        }
    }

    private void consultarMovimentacoes() {
        String status = Formatacao.formataString(requisicao.getParameter("status"));
        String pessoa = Formatacao.formataString(requisicao.getParameter("pessoa"));
        String consulta = "WHERE status = '" + status + "'";
        if (!pessoa.equals("")) {
            consulta = consulta + " AND pessoa = " + pessoa;
        }
        Usuario usuario = (Usuario) requisicao.getSession().getAttribute("usuarioLogado");
        ArrayList<Object> Movimentacao = new MovimentacaoDAO().consultar(consulta);
        try {
            String completarTabela = "";
            if (Movimentacao.isEmpty()) {
                resposta.getWriter().write("<h3 style=\"text-align: center;\">Nada encontrado!</h3>");
            } else if (status.equals("Reserva")) {
                if (usuario.getTipo().equals("Administrador")) {
                    completarTabela = "<th style=\"text-align: center\">Retirar</th>";
                }
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Pessoa</th><th>Data reserva</th><th>Observação</th>" + completarTabela + "<th style=\"text-align: center\">Cancelar reserva</th><th style=\"text-align: center\">Visualizar</th></tr></thead><tbody>");
                for (int i = 0; i < Movimentacao.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Movimentacao) Movimentacao.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getPessoa().getNome() + " " + ((Movimentacao) Movimentacao.get(i)).getPessoa().getSobrenome() + "</td>");
                    resposta.getWriter().write("<td>" + Formatacao.ajustaDataDMA(((Movimentacao) Movimentacao.get(i)).getData_reserva().toString()) + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getObservacao() + "</td>");
                    if (usuario.getTipo().equals("Administrador")) {
                        resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=retirarReserva&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-check\" aria-hidden=\"true\"></span></a> </td>");
                    }
                    resposta.getWriter().write("<td align=\"center\"><a href=\"#\" data-toggle=\"modal\" data-target=\"#modalCancelar\" data-href=\"/bibliomaster/acao?parametro=cancelarReserva&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a> </td>");
                    resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");

            } else if (status.equals("Retirado")) {
                if (usuario.getTipo().equals("Administrador")) {
                    completarTabela = "<th style=\"text-align: center\">Devolver</th>";
                }
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Pessoa</th><th>Data retirada</th><th>Devolver dia</th><th>Observação</th>" + completarTabela + "<th style=\"text-align: center\">Visualizar</th></tr></thead><tbody>");
                for (int i = 0; i < Movimentacao.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Movimentacao) Movimentacao.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getPessoa().getNome() + " " + ((Movimentacao) Movimentacao.get(i)).getPessoa().getSobrenome() + "</td>");
                    resposta.getWriter().write("<td>" + Formatacao.ajustaDataDMA(((Movimentacao) Movimentacao.get(i)).getData_retirada().toString()) + "</td>");
                    resposta.getWriter().write("<td>" + Formatacao.ajustaDataDMA(((Movimentacao) Movimentacao.get(i)).getData_devolver().toString()) + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getObservacao() + "</td>");
                    if (usuario.getTipo().equals("Administrador")) {
                        resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=procederDevolucao&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-check\" aria-hidden=\"true\"></span></a> </td>");
                    }
                    resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            } else if (status.equals("Cancelado")) {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Pessoa</th><th>Data reserva</th><th>Observação</th><th style=\"text-align: center\">Visualizar</th></tr></thead><tbody>");
                for (int i = 0; i < Movimentacao.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Movimentacao) Movimentacao.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getPessoa().getNome() + " " + ((Movimentacao) Movimentacao.get(i)).getPessoa().getSobrenome() + "</td>");
                    resposta.getWriter().write("<td>" + Formatacao.ajustaDataDMA(((Movimentacao) Movimentacao.get(i)).getData_reserva().toString()) + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getObservacao() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            } else if (status.equals("Fechado")) {
                resposta.getWriter().write("<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Pessoa</th><th>Data retirada</th><th>Devolver dia</th><th>Observação</th><th style=\"text-align: center\">Visualizar</th></tr></thead><tbody>");
                for (int i = 0; i < Movimentacao.size(); i++) {
                    resposta.getWriter().write("<tr><td>" + ((Movimentacao) Movimentacao.get(i)).getId() + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getPessoa().getNome() + " " + ((Movimentacao) Movimentacao.get(i)).getPessoa().getSobrenome() + "</td>");
                    resposta.getWriter().write("<td>" + Formatacao.ajustaDataDMA(((Movimentacao) Movimentacao.get(i)).getData_retirada().toString()) + "</td>");
                    resposta.getWriter().write("<td>" + Formatacao.ajustaDataDMA(((Movimentacao) Movimentacao.get(i)).getData_devolver().toString()) + "</td>");
                    resposta.getWriter().write("<td>" + ((Movimentacao) Movimentacao.get(i)).getObservacao() + "</td>");
                    resposta.getWriter().write("<td align=\"center\"><a href=\"/bibliomaster/acao?parametro=visualizarMovimentacao&id=" + ((Movimentacao) Movimentacao.get(i)).getId() + "\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></a> </td></tr>");
                }
                resposta.getWriter().write("</tbody></table>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void validarLogin() {
        String cpf = requisicao.getParameter("cpf");
        String senha = requisicao.getParameter("senha");

        Usuario u = new Usuario();
        Pessoa p = new Pessoa();
        p.setCpf(cpf);
        u.setPessoa(p);
        u.setSenha(senha);
        UsuarioDAO uDAO = new UsuarioDAO();

        if (uDAO.consultar(u)) {
            // deu certo
            // setando atributos completos usuário
            p = (Pessoa) new PessoaDAO().consultarCpf(cpf);
            u = (Usuario) uDAO.consultarId(p.getId());
            // usuario validado: cria coloca seu nome na sessao
            HttpSession sessao = requisicao.getSession();
            // setando um atributo da sessao
            sessao.setAttribute("usuarioLogado", u);
            // verificando reservas
            if (u.getTipo().equals("Administrador")) {
                System.out.println("Verificando Reservas");
                new MovimentacaoDAO().verificarReservas();
            }
            encaminharPagina("menu.jsp");
        } else {
            // deu errado
            requisicao.setAttribute("erro", "erro");
            encaminharPagina("index.jsp");
        }

    }

    private void efetuarLogout() {
        HttpSession sessao = requisicao.getSession();
        sessao.invalidate();
        encaminharPagina("index.jsp");
    }

    private void encaminharPagina(String pagina) {
        try {
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            rd.forward(requisicao, resposta);
        } catch (Exception e) {
            System.out.println("erro no encaminhamento: " + e);
        }
    }

    private void cadastrarValor() {
        this.retorno = new ArrayList<>();

        double diaria = Double.parseDouble(requisicao.getParameter("diaria"));
        double multa = Double.parseDouble(requisicao.getParameter("multa"));

        Valor v = new Valor();
        v.setDiaria(diaria);
        v.setMulta(multa);

        retorno.add(new ValorDAO().salvar(v, 'i'));

        try {
            if (retorno.get(0) == null) {
                resposta.getWriter().println("Sucesso!Valores cadastrados com sucesso!");
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cadastrarUsuarioNormal() {
        this.retorno = new ArrayList<>();

        String nome = requisicao.getParameter("nome");
        String sobrenome = requisicao.getParameter("sobrenome");
        String cpf = Formatacao.formataString(requisicao.getParameter("cpf"));
        String rg = requisicao.getParameter("rg");
        String email = requisicao.getParameter("email");
        String senha = requisicao.getParameter("senha");
        ArrayList<Telefone> telefone = new ArrayList<>();
        String[] telefones = requisicao.getParameterValues("telefone");

        UsuarioDAO uDAO = new UsuarioDAO();
        PessoaDAO pDAO = new PessoaDAO();
        Pessoa p = new Pessoa();
        Usuario usuario = new Usuario();

        if (pDAO.verificaCpfExistente(cpf).equals("true")) {
            p = (Pessoa) pDAO.consultarCpf(cpf);
        } else {
            p.setCpf(cpf);
        }

        usuario.setPessoa(p);

        if (uDAO.pessoaCadastrada(usuario)) {
            retorno.add("CPF com usuário já cadastrado");
        } else {
            if (telefones != null) {
                for (int i = 0; i < telefones.length; i++) {
                    String telAtual = telefones[i];
                    if (telAtual.length() > 0) {
                        boolean valido = true;
                        String ddd = "";
                        String numero = "";
                        if (telAtual.length() == 13) {
                            ddd = telAtual.substring(1, 3).replaceAll(" ", "");
                            numero = telAtual.substring(5, 13).replaceAll(" ", "");
                        } else if (telAtual.length() == 14) {
                            ddd = telAtual.substring(1, 3).replaceAll(" ", "");
                            numero = telAtual.substring(5, 14).replaceAll(" ", "");
                        } else {
                            valido = false;
                        }
                        if (valido) {
                            boolean incluso = false;
                            for (int j = 0; j < telefone.size(); j++) {
                                if (telefone.get(j).getDdd().equals(ddd) && telefone.get(j).getNumero().equals(numero)) {
                                    incluso = true;
                                    j = telefone.size();
                                }
                            }
                            if (!incluso) {
                                Telefone t = new Telefone();
                                t.setPessoa(p.getId());
                                t.setDdd(ddd);
                                t.setNumero(numero);
                                telefone.add(t);
                            }
                        } else {
                            retorno.add("Telefone: " + telAtual + " inválido.");
                        }
                    }
                }
            }

            p.setNome(nome);
            p.setSobrenome(sobrenome);
            p.setRg(rg);
            p.setEmail(email);
            p.setTelefone(telefone);

            if (!Validacao.validaTamanhoString(p.getNome(), 0, "maior")) {
                retorno.add("Falta o Nome.");
            } else if (!Validacao.validaTamanhoString(p.getNome(), 31, "menor")) {
                retorno.add("Nome muito comprido.");
            }

            if (!Validacao.validaTamanhoString(p.getSobrenome(), 0, "maior")) {
                retorno.add("Falta o Sobrenome.");
            } else if (!Validacao.validaTamanhoString(p.getSobrenome(), 61, "menor")) {
                retorno.add("Sobrenome muito comprido.");
            }
            if (!Validacao.validarCPF(p.getCpf())) {
                retorno.add("CPF inválido.");
            }
            if (!Validacao.validaTamanhoString(p.getRg(), 0, "maior")) {
                retorno.add("Falta o RG.");
            } else if (!Validacao.validaTamanhoString(p.getRg(), 12, "menor")) {
                retorno.add("RG muito comprido.");
            }
            if (!p.getEmail().equals("")) {
                if (!Validacao.validarEmail(p.getEmail())) {
                    retorno.add("Email inválido.");
                }
            }
            if (!senha.equals(Formatacao.formataString(senha))) {
                retorno.add("Senha possui caracteres inválidos.");
            }

            if (retorno.isEmpty()) {
                if (p.getId() == 0) {
                    retorno.add(pDAO.salvar(p, 'i'));
                    p = (Pessoa) pDAO.consultarCpf(cpf);
                } else {
                    retorno.add(pDAO.atualizar(p));
                }
                usuario.setPessoa(p);
                usuario.setTipo("Usuário");
                usuario.setSenha(senha);
                retorno.add(uDAO.salvar(usuario, 'i'));
            }
        }

        try {
            if (retorno.get(0) == null && retorno.get(1) == null) {
                resposta.getWriter().println("Sucesso!Usuário cadastrado com sucesso!");
            } else {
                String erro = "Erro!";
                for (int i = 0; i < retorno.size(); i++) {
                    erro = erro + retorno.get(i) + "\n";
                }
                resposta.getWriter().println(erro);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void visualizarMovimentacao() {
        int id = Integer.parseInt(requisicao.getParameter("id"));

        Movimentacao mov = (Movimentacao) new MovimentacaoDAO().consultarId(id);

        if (mov.getId() != 0) {
            requisicao.setAttribute("movimentacao", mov);
            requisicao.setAttribute("mensagem", "Visualizar Movimentação");
            encaminharPagina("visualizaMovimentacao.jsp");
        } else {
            this.retorno = new ArrayList<>();
            retorno.add("Movimentação não encontrada!");
            requisicao.setAttribute("retorno", retorno);
            requisicao.setAttribute("paginaRetorno", "listaMovimentacao.jsp");
            encaminharPagina("erro.jsp");
        }
    }

    public void relatorioAtraso() {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            File reportFile = new File(DIRETORIO_RELATORIOS + "RelAtraso.jasper");
            Map parameters = new HashMap();
            byte[] relatorio = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            requisicao.setAttribute("relatorio", relatorio);
            encaminharPagina("relatorio.jsp");
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
    }

    public void relatorioLivros() {
        String tipo = requisicao.getParameter("tipo");
        String complementoSQL = "";
        if (tipo.equals("Retirado")) {
            complementoSQL = "AND cl.estado = 'Retirado'";
        } else if (tipo.equals("Estoque")) {
            complementoSQL = "AND cl.estado = 'Disponível'";
        }
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            File reportFile = new File(DIRETORIO_RELATORIOS + "relLivro.jasper");
            Map parameters = new HashMap();
            parameters.put("complementoSQL", complementoSQL);
            byte[] relatorio = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            requisicao.setAttribute("relatorio", relatorio);
            encaminharPagina("relatorio.jsp");
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
    }

    public void historicoLivro() {
        String complementoSQL = "";
        String complementoSQLMov = "";
        String dataInicial = requisicao.getParameter("data_inicial");
        String dataFinal = requisicao.getParameter("data_final");
        String idLivro = requisicao.getParameter("livro");
        try { //organiza data inicial e final
            Date inicial = Formatacao.transformarParaDataWeb(dataInicial);
            Date dfinal = Formatacao.transformarParaDataWeb(dataFinal);
            if (inicial.compareTo(dfinal) > 0) {
                String dtinicial = dataFinal;
                dataFinal = dataInicial;
                dataInicial = dtinicial;
            }
            complementoSQL = " AND (m.data_reserva BETWEEN '" + dataInicial + "' AND '" + dataFinal + "' OR m.data_retirada BETWEEN '" + dataInicial + "' AND '" + dataFinal + "' OR m.data_devolver BETWEEN '" + dataInicial + "' AND '" + dataFinal + "' OR mhl.data_devolucao BETWEEN '" + dataInicial + "' AND '" + dataFinal + "')";
        } catch (Exception e) {
            //alguma data não está definida
            if (dataInicial.isEmpty() && !dataFinal.isEmpty()) {
                complementoSQL = " AND (m.data_reserva BETWEEN '01/01/1900' AND '" + dataFinal + "' OR m.data_retirada BETWEEN '01/01/1900' AND '" + dataFinal + "' OR m.data_devolver BETWEEN '01/01/1900' AND '" + dataFinal + "' OR mhl.data_devolucao BETWEEN '01/01/1900' AND '" + dataFinal + "')";
            } else if (!dataInicial.isEmpty() && dataFinal.isEmpty()) {
                complementoSQL = " AND (m.data_reserva BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "' OR m.data_retirada BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "' OR m.data_devolver BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "' OR mhl.data_devolucao BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "')";
            }
        }
        complementoSQLMov = complementoSQL;
        if (!idLivro.isEmpty()) {
            complementoSQL = complementoSQL + " AND l.id = " + idLivro;
        }
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            File reportFile = new File(DIRETORIO_RELATORIOS + "relLivroPeriodo.jasper");
            Map parameters = new HashMap();
            parameters.put("complementoSQL", complementoSQL);
            parameters.put("complementoSQLMov", complementoSQLMov);
            byte[] relatorio = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            requisicao.setAttribute("relatorio", relatorio);
            encaminharPagina("relatorio.jsp");
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
    }

    public void historicoPessoa() {
        String complementoSQL = "";
        String dataInicial = requisicao.getParameter("data_inicial");
        String dataFinal = requisicao.getParameter("data_final");
        String idPessoa = requisicao.getParameter("pessoa");
        String tipo = requisicao.getParameter("tipo");
        try { //organiza data inicial e final
            Date inicial = Formatacao.transformarParaDataWeb(dataInicial);
            Date dfinal = Formatacao.transformarParaDataWeb(dataFinal);
            if (inicial.compareTo(dfinal) > 0) {
                String dtinicial = dataFinal;
                dataFinal = dataInicial;
                dataInicial = dtinicial;
            }
            complementoSQL = " AND (m.data_reserva BETWEEN '" + dataInicial + "' AND '" + dataFinal + "' OR m.data_retirada BETWEEN '" + dataInicial + "' AND '" + dataFinal + "' OR m.data_devolver BETWEEN '" + dataInicial + "' AND '" + dataFinal + "' OR mhl.data_devolucao BETWEEN '" + dataInicial + "' AND '" + dataFinal + "')";
        } catch (Exception e) {
            //alguma data não está definida
            if (dataInicial.isEmpty() && !dataFinal.isEmpty()) {
                complementoSQL = " AND (m.data_reserva BETWEEN '01/01/1900' AND '" + dataFinal + "' OR m.data_retirada BETWEEN '01/01/1900' AND '" + dataFinal + "' OR m.data_devolver BETWEEN '01/01/1900' AND '" + dataFinal + "' OR mhl.data_devolucao BETWEEN '01/01/1900' AND '" + dataFinal + "')";
            } else if (!dataInicial.isEmpty() && dataFinal.isEmpty()) {
                complementoSQL = " AND (m.data_reserva BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "' OR m.data_retirada BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "' OR m.data_devolver BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "' OR mhl.data_devolucao BETWEEN '" + dataInicial + "' AND '" + Formatacao.getDataAtual() + "')";
            }
        }
        if (!idPessoa.isEmpty()) {
            complementoSQL = complementoSQL + " AND p.id = " + idPessoa;
        }
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            File reportFile;
            if (tipo.equals("Multa")) {
                reportFile = new File(DIRETORIO_RELATORIOS + "relMultas.jasper");
            } else {
                reportFile = new File(DIRETORIO_RELATORIOS + "relPessoa.jasper");
            }
            Map parameters = new HashMap();
            parameters.put("complementoSQL", complementoSQL);
            byte[] relatorio = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            requisicao.setAttribute("relatorio", relatorio);
            encaminharPagina("relatorio.jsp");
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
    }

    private void csvMovimentacoes() {
        String status = Formatacao.formataString(requisicao.getParameter("status"));
        String pessoa = Formatacao.formataString(requisicao.getParameter("pessoa"));
        String consulta = "WHERE status = '" + status + "'";
        if (!pessoa.equals("")) {
            consulta = consulta + " AND pessoa = " + pessoa;
        }
        ArrayList<Object> Movimentacao = new MovimentacaoDAO().consultar(consulta);
        Arquivo arquivo = new Arquivo(DIRETORIO_CSV + "movimentacao.csv");

        if (arquivo.abrirEscrita(false)) {
            String saida = "";
            if (status.equals("Retirado") || status.equals("Fechado")) {
                saida = "Id Mov;Pessoa;Status;Data retirada;Devolver dia;Observação";
                arquivo.escreverLinha(saida);
                for (int i = 0; i < Movimentacao.size(); i++) {
                    Movimentacao atual = (Movimentacao) Movimentacao.get(i);
                    saida = atual.getId() + ";"
                            + atual.getPessoa().getNome() + " " + atual.getPessoa().getSobrenome() + ";"
                            + atual.getStatus() + ";"
                            + atual.getData_retirada() + ";"
                            + atual.getData_devolver() + ";"
                            + atual.getObservacao();
                    arquivo.escreverLinha(saida);
                }
            } else {
                saida = "Id Mov;Pessoa;Status;Data reserva;Observação";
                arquivo.escreverLinha(saida);
                for (int i = 0; i < Movimentacao.size(); i++) {
                    Movimentacao atual = (Movimentacao) Movimentacao.get(i);
                    saida = atual.getId() + ";"
                            + atual.getPessoa().getNome() + " " + atual.getPessoa().getSobrenome() + ";"
                            + atual.getStatus() + ";"
                            + atual.getData_reserva() + ";"
                            + atual.getObservacao();
                    arquivo.escreverLinha(saida);
                }
            }
        }

        arquivo.fecharArquivo();

        try {
            Thread.sleep(3000);
            resposta.sendRedirect("csv/movimentacao.csv");
        } catch (Exception e) {
            System.out.println("erro sendRedirect: " + e);
        }
    }

    private void gerarCSV() {
        String tipo = Formatacao.formataString(requisicao.getParameter("tipo"));
        Arquivo arquivo = new Arquivo(DIRETORIO_CSV + tipo + ".csv");
        String saida = "";
        if (tipo.equals("livro")) {
            ArrayList<Object> Livros = new LivroDAO().consultarTodos();
            if (arquivo.abrirEscrita(false)) {
                saida = "Id Livro;Título;Publicação;Edição;Idioma;Editora;Autores;Gêneros";
                arquivo.escreverLinha(saida);
                for (int i = 0; i < Livros.size(); i++) {
                    Livro atual = (Livro) Livros.get(i);
                    saida = atual.getId() + ";"
                            + atual.getTitulo() + ";"
                            + atual.getPublicacao() + ";"
                            + atual.getEdicao() + ";"
                            + atual.getIdioma().getSigla() + ";"
                            + atual.getEditora().getNome() + ";" + '"';
                    for (int j = 0; j < atual.getAutor().size(); j++) {
                        Autor autor = atual.getAutor().get(j);
                        saida = saida + autor.getNome() + " " + autor.getSobrenome();
                        if (j != atual.getAutor().size() - 1) {
                            saida = saida + "\r\n";
                        }
                    }
                    saida = saida + '"' + ';' + '"';
                    for (int k = 0; k < atual.getGenero().size(); k++) {
                        Genero genero = atual.getGenero().get(k);
                        saida = saida + genero.getNome();
                        if (k != atual.getGenero().size() - 1) {
                            saida = saida + "\r\n";
                        }
                    }
                    saida = saida + '"';
                    arquivo.escreverLinha(saida);
                }
            }
        } else if (tipo.equals("pessoa")) {
            ArrayList<Object> Pessoas = new PessoaDAO().consultarTodos();
            if (arquivo.abrirEscrita(false)) {
                saida = "Id Pessoa;Nome;Sobrenome;CPF;RG;Email";
                arquivo.escreverLinha(saida);
                for (int i = 0; i < Pessoas.size(); i++) {
                    Pessoa atual = (Pessoa) Pessoas.get(i);
                    saida = atual.getId() + ";"
                            + atual.getNome() + ";"
                            + atual.getSobrenome() + ";"
                            + atual.getCpf() + ";"
                            + atual.getRg() + ";"
                            + atual.getEmail() + ";";
                    arquivo.escreverLinha(saida);
                }
            }
        } else if (tipo.equals("usuario")) {
            ArrayList<Object> Usuarios = new UsuarioDAO().consultarTodos();
            if (arquivo.abrirEscrita(false)) {
                saida = "CPF;Nome Completo;Tipo;Ativo";
                arquivo.escreverLinha(saida);
                for (int i = 0; i < Usuarios.size(); i++) {
                    Usuario atual = (Usuario) Usuarios.get(i);
                    saida = atual.getPessoa().getCpf() + ";"
                            + atual.getPessoa().getNome() + " " + atual.getPessoa().getSobrenome() + ";"
                            + atual.getTipo() + ";";
                    if (atual.isAtivo()) {
                        saida = saida + "Sim";
                    } else {
                        saida = saida + "Não";
                    }
                    arquivo.escreverLinha(saida);
                }
            }
        }

        arquivo.fecharArquivo();

        try {
            Thread.sleep(3000);
            resposta.sendRedirect("csv/" + tipo + ".csv");
        } catch (Exception e) {
            System.out.println("erro sendRedirect: " + e);
        }
    }
}
