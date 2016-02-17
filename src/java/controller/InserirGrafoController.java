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
//MOVER TODOS OS OBJETOS PARA O INICIO DO CODIGO;

public class InserirGrafoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher pagina = request.getRequestDispatcher("index.jsp");
        String caminhoServer = this.getServletContext().getRealPath("");
        request.setAttribute("mensagem", "Grafo salvo com Sucesso!");
        String nos[] = request.getParameterValues("nos");
        String tipo = request.getParameter("direcionado");
        String valorado = request.getParameter("valorado");
        String id = request.getParameter("nomeDoGrafo");
        String arestas[] = request.getParameterValues("arestas");
        List<No> listaNos = new ArrayList();
        List<Aresta> listaArestas = new ArrayList();
        No ponto = null;
        String no1 = null, no2 = null;
        Aresta aresta = null;
        Grafo grafo = null;
        boolean tipoAresta = false;
        int i = 1;

        if (tipo == null) {
            tipo = "undirected";
        }

        for (String no : nos) {
            ponto = new No(no.toUpperCase());
            listaNos.add(ponto);
        }
        if ("valorado".equals(valorado)) {
            tipoAresta = true;
            String valorArestasString[] = request.getParameterValues("valorAresta");
            ArrayList<Integer> valoresAresta = InserirGrafoController.converterParaInt(valorArestasString);
            for (String a : arestas) {  //Codigo para criar as areastas e adicionar valor em cada
                for (int b : valoresAresta) {
                    a = a.toUpperCase();
                    a = a.replaceAll(" ", "");
                    String[] resultado = a.split(",", 2);
                    no1 = resultado[0];
                    no2 = resultado[1];
                    aresta = new Aresta("Aresta " + i, No.getNoById(no1, listaNos), No.getNoById(no2, listaNos), b);
                    listaArestas.add(aresta);
                    valoresAresta.remove((Object) b);
                    i++;
                    break;
                }
            }
        } else {
            
            for (String a : arestas) {  //Codigo para criar as areastas sem adicionar valor
                a = a.toUpperCase();
                a = a.replaceAll(" ", "");
                String[] resultado = a.split(",", 2);
                no1 = resultado[0];
                no2 = resultado[1];
                aresta = new Aresta("A" + i, No.getNoById(no1, listaNos), No.getNoById(no2, listaNos));
                listaArestas.add(aresta);
                i++;
            }
        }
        grafo = new Grafo(id, tipo, tipoAresta, listaNos, listaArestas);
        request.setAttribute("grafo", grafo);
        request.getSession().setAttribute("grafo", grafo);
        XML.salvaGrafo(grafo, caminhoServer);
        pagina.forward(request, response);
    }

    private static ArrayList<Integer> converterParaInt(String valorArestasString[]) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        int valorInt;
        for (String valor : valorArestasString) {
            valorInt = Integer.parseInt(valor);
            lista.add(valorInt);
        }
        return lista;
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
