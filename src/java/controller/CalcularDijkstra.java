package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grafo;
import model.No;

/**
 * @author Gabriel
 */
public class CalcularDijkstra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Grafo grafo = (Grafo) request.getSession().getAttribute("grafo");
        String nomeNoOrigem = (String) request.getAttribute("noOrigem");
        String nomeNoDestino = (String) request.getAttribute("noDestino");
        No noOrigem = grafo.getNo(nomeNoOrigem);
        if ("todosNos".equals(nomeNoDestino)) {
            Map<No, Integer> mapaDijkstra = grafo.calcularDijkstra(noOrigem);
            request.setAttribute("mapaDijkstra", mapaDijkstra);
        } else {
            No noDestino = grafo.getNo(nomeNoDestino);
            int distancia = grafo.calcularDijkstra(noOrigem, noDestino);
            request.setAttribute("intDijkstra", distancia);
            request.setAttribute("noDestino", nomeNoDestino);
        }
        request.setAttribute("NomeNoOrigem", nomeNoOrigem);
        getServletContext().getRequestDispatcher("/dijkstra.jsp").forward(request, response);
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
