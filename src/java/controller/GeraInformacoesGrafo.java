package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.Grafo;
import model.No;
import model.NosComparator;

/**
 *
 * @author Gabriel
 */
public class GeraInformacoesGrafo extends HttpServlet {

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
        Grafo grafoCarregado = (Grafo) request.getSession().getAttribute("grafo");
        
        NosComparator comparator = new NosComparator();
        Collections.sort(grafoCarregado.getNos(), comparator);
        Map mapaOrganizadoGraus = new TreeMap(comparator);

        request.setAttribute("tipoGrafoArestas", grafoCarregado.getTipoAresta());
        if (grafoCarregado.getTipo().equals("directed")) {
            Map mapaOrganizadoGrauEmissao = new TreeMap(comparator);
            Map mapaOrganizadoGrauRecepcao = new TreeMap(comparator);
            
            mapaOrganizadoGrauEmissao.putAll(grafoCarregado.getGrausDeEmissao());
            request.setAttribute("grauDeEmissao", mapaOrganizadoGrauEmissao);
           
            mapaOrganizadoGrauRecepcao.putAll(grafoCarregado.getGrausDeRecepcao());
            request.setAttribute("grauDeRecepcao", mapaOrganizadoGrauRecepcao);
            request.setAttribute("nosAntecessores", grafoCarregado.getNosAntecessores());
            request.setAttribute("nosSucessores", grafoCarregado.getNosSucessores());
            
            
            //request.setAttribute("listaNosAntecessores", CarregaGrafoController.criaListaAntecessores(grafoCarregado));
            //request.setAttribute("listaNosSucessores", CarregaGrafoController.criaListaSucessores(grafoCarregado));
        }
        request.setAttribute("mapaVerticesAdj", grafoCarregado.getMapaVerticesAdjacentes());
        request.setAttribute("mapaArestasAdj", grafoCarregado.getMapaArestasAdjacentes());
        mapaOrganizadoGraus.putAll(grafoCarregado.getGraus());
        request.setAttribute("nosComGrau", mapaOrganizadoGraus);
        request.setAttribute("ordemGrafo", grafoCarregado.getOrdem());
        request.setAttribute("mapaVerticesIndependentes", grafoCarregado.getVerticesIndependentes());
        request.setAttribute("mapaArestasIndependentes", grafoCarregado.getArestasIndependentes());
        
        getServletContext().getRequestDispatcher("/visualizarGrafo.jsp").forward(request, response);
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
