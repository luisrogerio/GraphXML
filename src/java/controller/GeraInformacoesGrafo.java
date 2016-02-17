package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        int[][] matriz = grafoCarregado.getMatrizAdjacencia();
        if (grafoCarregado.getTipo().equals("directed")) {
            Map mapaOrganizadoGrauEmissao = new TreeMap(comparator);
            Map mapaOrganizadoGrauRecepcao = new TreeMap(comparator);
            
            mapaOrganizadoGrauEmissao.putAll(grafoCarregado.getGrausDeEmissao());
            request.setAttribute("grauDeEmissao", mapaOrganizadoGrauEmissao);
           
            mapaOrganizadoGrauRecepcao.putAll(grafoCarregado.getGrausDeRecepcao());
            request.setAttribute("grauDeRecepcao", mapaOrganizadoGrauRecepcao);
            
            //request.setAttribute("listaNosAntecessores", CarregaGrafoController.criaListaAntecessores(grafoCarregado));
            //request.setAttribute("listaNosSucessores", CarregaGrafoController.criaListaSucessores(grafoCarregado));
        }// else {
        mapaOrganizadoGraus.putAll(grafoCarregado.getGraus());
        request.setAttribute("nosComGrau", mapaOrganizadoGraus);
        request.setAttribute("ordemGrafo", grafoCarregado.getOrdem());
        //request.setAttribute("nosIsolados", grafoCarregado.);
        //}
        //request.setAttribute("listaVerticesIndependentes", CarregaGrafoController.listaVerticesIndependentes(grafoCarregado, grafoCarregado.getMatrizAdjacencia()));
        //request.setAttribute("listaArestasIndependentes", CarregaGrafoController.listaArestasIndependentes(grafoCarregado, grafoCarregado.getMatrizAdjacencia()));
        
        getServletContext().getRequestDispatcher("/visualizarGrafo.jsp").forward(request, response);
    }

    private static List<No> listaNosIsolados(Map<No,Integer> mapa) {
        List<No> listaDeNosIsolados = new ArrayList();
        for (No noAtual : mapa.keySet()) {
            if (mapa.get(noAtual) == 0) {
                listaDeNosIsolados.add(noAtual);
            }
        }
        return listaDeNosIsolados;
    }

    private static List<No> listaNosFolhas(Map<No, Integer> mapa) { //Conferir se essa função também funciona para grafos direcionados.
        List<No> listaDeNosFolhas = new ArrayList();
        for (No noAtual : mapa.keySet()) {
            if (mapa.get(noAtual) == 1) {
                listaDeNosFolhas.add(noAtual);
            }
        }
        return listaDeNosFolhas;
    }

    private static List<List<Aresta>> listaArestasIndependentes(Grafo grafo, int matriz[][]) {
        List<List<Aresta>> listaArestasIndependentes = new ArrayList();
        List<Aresta> listaArestas = new ArrayList();
        Map<Integer, No> nosDoGrafo = new HashMap();
        Map<Integer, Aresta> arestasDoGrafo = new HashMap();
        int i = 0, j = 0;
        for (No no : grafo.getNos()) {
            nosDoGrafo.put(i, no);
            i++;
        }
        i = 0;
        for (Aresta aresta : grafo.getArestas()) {
            arestasDoGrafo.put(i, aresta);
            i++;
        }
        for (i = 0; i < matriz.length; i++) {
            listaArestas.clear();
            for (j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 0) {
                    listaArestas.add(arestasDoGrafo.get(j));
                }
            }
            listaArestasIndependentes.add(listaArestas);
        }
        return listaArestasIndependentes;
    }

    private static List<List<No>> listaVerticesIndependentes(Grafo grafo, int matriz[][]) {
        List<List<No>> listaVerticesIndependentes = new ArrayList();
        List<No> listaVertices = new ArrayList();
        Map<Integer, No> nosDoGrafo = new HashMap();
        Map<Integer, Aresta> arestasDoGrafo = new HashMap();
        int i = 0, j = 0;
        for (No no : grafo.getNos()) {
            nosDoGrafo.put(i, no);
            i++;
        }
        i = 0;
        for (Aresta aresta : grafo.getArestas()) {
            arestasDoGrafo.put(i, aresta);
            i++;
        }
        for (i = 0; i < matriz[0].length; i++) {
            listaVertices.clear();
            for (j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == 0) {
                    listaVertices.add(nosDoGrafo.get(i));
                }
            }
            listaVerticesIndependentes.add(listaVertices);
        }
        return listaVerticesIndependentes;
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
