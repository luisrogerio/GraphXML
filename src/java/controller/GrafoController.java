package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.No;
import model.Grafo;
import model.XML;

public class GrafoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //pegar e criar objetos nós
        String nos[] = request.getParameterValues("nos");
        List<No> listaNos = new ArrayList();
        int i = 0;
        for (String no : nos) {//Código para criar nós felizinhos
            No ponto = new No(no.toUpperCase());
            listaNos.add(ponto);
            i++;
        }
        //pegar e criar objetos arestas
        String arestas[] = request.getParameterValues("arestas");
        List<Aresta> listaArestas = new ArrayList();
        i = 0;
        String no1 = null, no2 = null;
        for (String a : arestas) {  //Codigo para criar as areastas
            a = a.toUpperCase();
            a = a.replaceAll(" ", "");
            for (String resultado : a.split(",", 2)) {
                if (i == 0) {
                    no1 = resultado;
                    i++;
                } else {
                    no2 = resultado;
                    i--;
                }
            }
            Aresta aresta = new Aresta(No.getNoById(no1, listaNos), No.getNoById(no2, listaNos));
            listaArestas.add(aresta);
        }
        //pegar e criar o grafo
        String tipo = request.getParameter("direcionado");
        if (tipo == null) {
            tipo = "undirected";
        }
        String id = request.getParameter("nomeDoGrafo");

        Grafo grafo = new Grafo(id, tipo, listaNos, listaArestas);
        String caminhoServer = this.getServletContext().getRealPath("");
        XML.salvaGrafo(grafo, caminhoServer);
        request.setAttribute("mensagem", "Grafo salvo com Sucesso!");
        RequestDispatcher pagina = request.getRequestDispatcher("index.jsp");
        pagina.forward(request, response);
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

}
