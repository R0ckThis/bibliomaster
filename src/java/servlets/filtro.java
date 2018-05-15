/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidade.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabricio
 */
@WebFilter("/*")
public class filtro extends HttpServlet implements Filter {

    List<String> urls = new ArrayList<>();

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
            out.println("<title>Servlet filtro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet filtro at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls.add("/bibliomaster");
        urls.add("/bibliomaster/index.jsp");
        urls.add("/bibliomaster/cadUsuario.jsp");
        urls.add("/bibliomaster/acao");
        urls.add("/bibliomaster/js/bootstrap.js");
        urls.add("/bibliomaster/js/bootstrap.min.js");
        urls.add("/bibliomaster/js/npm.js");
        urls.add("/bibliomaster/js/sweetalert.min.js");
        urls.add("/bibliomaster/css/bootstrap.min.css");
        urls.add("/bibliomaster/jquery/jquery.maskedinput.js");
        urls.add("/bibliomaster/fonts/glyphicons-halflings-regular.ttf");
        urls.add("/bibliomaster/fonts/glyphicons-halflings-regular.eot");
        urls.add("/bibliomaster/fonts/glyphicons-halflings-regular.svg");
        urls.add("/bibliomaster/fonts/glyphicons-halflings-regular.woff");
        urls.add("/bibliomaster/fonts/glyphicons-halflings-regular.woff2");
        urls.add("/bibliomaster/css/sweetalert.css");
        urls.add("/bibliomaster/jquery/jquery.min.js");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (urls.contains(req.getRequestURI())) {
            request.setAttribute("parametro", "login");
            chain.doFilter(request, response);
        } else {
            HttpSession sessao = ((HttpServletRequest) request).getSession();
            Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
            if (usuario == null) {
                if (req.getRequestURI().equals("/bibliomaster/cadUsuario.jsp")) {
                    ((HttpServletResponse) response).sendRedirect("cadUsuario.jsp");
                } else {
                    ((HttpServletResponse) response).sendRedirect("index.jsp");
                }
            } else {
                if (req.getRequestURI().equals("/bibliomaster/")) {
                    ((HttpServletResponse) response).sendRedirect("menu.jsp");
                } else if (usuario.getTipo().equals("Usuário")) {
                    ArrayList<String> urlsBloqueadas = new ArrayList<>();
                    urlsBloqueadas.add("/bibliomaster/cadAutor.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadCopia_livro.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadEditora.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadGenero.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadIdioma.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadLivro.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadPessoa.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadRetirada.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadUsuarioAdmin.jsp");
                    urlsBloqueadas.add("/bibliomaster/cadValor.jsp");
                    urlsBloqueadas.add("/bibliomaster/devolverLivro.jsp");
                    urlsBloqueadas.add("/bibliomaster/editCopia.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaAutor.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaCopiaLivro.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaEditora.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaGenero.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaIdioma.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaPessoa.jsp");
                    urlsBloqueadas.add("/bibliomaster/listaUsuario.jsp");
                    urlsBloqueadas.add("/bibliomaster/retirarReserva.jsp");
                    urlsBloqueadas.add("/bibliomaster/relLivros.jsp");
                    urlsBloqueadas.add("/bibliomaster/histLivro.jsp");

                    String urlAcessada = req.getRequestURI();
                    boolean permitido = true;
                    for (String urlAtual : urlsBloqueadas) {
                        if (urlAtual.equals(urlAcessada)) {
                            permitido = false;
                        }
                    }
                    if (!permitido) {
                        ((HttpServletResponse) response).sendRedirect("menu.jsp");
                    }
                }
                System.out.println("Destino: " + req.getRequestURI());
                chain.doFilter(request, response);
            }
        }
    }

}
