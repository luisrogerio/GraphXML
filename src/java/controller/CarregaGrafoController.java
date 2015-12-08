package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aresta;
import model.Grafo;
import model.No;
import model.XML;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CarregaGrafoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; 	// 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Grafo grafoCarregado = null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Formulário sem: enctype=multipart/form-data");
            writer.flush();
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        grafoCarregado = XML.abrirGrafo(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", "Erro: " + ex.getMessage());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        if (grafoCarregado != null) {
            request.getSession().setAttribute("grafo", grafoCarregado);
            request.setAttribute("grafo", grafoCarregado);

            if (grafoCarregado.getTipo().equals("directed")) {
                request.setAttribute("grauDeEmissao", CarregaGrafoController.criaArray(grafoCarregado.getGrausDeEmissao()));
                request.setAttribute("grauDeRecepcao", CarregaGrafoController.criaArray(grafoCarregado.getGrausDeRecepcao()));
                request.setAttribute("nosSumidouros", CarregaGrafoController.criaArrayDeNosFontesOuSumidouros(grafoCarregado.getGrausDeEmissao()));
                request.setAttribute("nosFonte", CarregaGrafoController.criaArrayDeNosFontesOuSumidouros(grafoCarregado.getGrausDeRecepcao()));
                request.setAttribute("listaNosAntecessores", CarregaGrafoController.criaListaAntecessores(grafoCarregado));
                request.setAttribute("listaNosSucessores", CarregaGrafoController.criaListaSucessores(grafoCarregado));
            } else {
                request.setAttribute("nosComGrau", CarregaGrafoController.criaArray(grafoCarregado.getGraus()));
            }

            getServletContext().getRequestDispatcher("/visualizarGrafo.jsp").forward(request, response);

        } else {
            request.setAttribute("mensagem", "Nenhum Grafo foi carregado!");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private static ArrayList<String> criaArray(Map<No, Integer> mapa) {
        if (mapa.isEmpty()) {
            ArrayList<No> nos = (ArrayList<No>) mapa.keySet();
            ArrayList<Integer> valor = (ArrayList<Integer>) mapa.values();
            ArrayList<String> nosMaisValores = new ArrayList();
            for (int i = 0; i < nos.size(); i++) {
                nosMaisValores.add(nos.get(i).getId() + ": " + valor.get(i).toString());
            }
            return nosMaisValores;
        }
        return null;
    }//Essa LINDA gambiarra irá concatenar as keys do mapa com seus valores.(Entrada: A -> 12; Saida: A: 12)

    private static ArrayList<No> criaArrayDeNosFontesOuSumidouros(Map<No, Integer> mapa) {
        ArrayList<No> listaDeNosFontes = new ArrayList();
        for (No no : mapa.keySet()) {
            if (mapa.get(no) == 0) {
                listaDeNosFontes.add(no);
            }
        }
        return listaDeNosFontes;
    }

    private static HashMap<No, List<No>> criaListaAntecessores(Grafo grafo) {
        HashMap<No, List<No>> mapaDeNosAntecessores = new HashMap();
        List<No> listaDeNosAntecessores = new ArrayList();
        for (No noAtual : grafo.getNos()) {

            for (Aresta arestaAtual : grafo.getArestas()) {
                if (noAtual == arestaAtual.getDestino()) {
                    listaDeNosAntecessores.add(arestaAtual.getOrigem());
                }
            }
            mapaDeNosAntecessores.put(noAtual, listaDeNosAntecessores);
        }
        return mapaDeNosAntecessores;
    }

    private static HashMap<No, List<No>> criaListaSucessores(Grafo grafo) {
        HashMap<No, List<No>> mapaDeNosSucessores = new HashMap();
        List<No> listaDeNosSucessores = new ArrayList();
        for (No noAtual : grafo.getNos()) {

            for (Aresta arestaAtual : grafo.getArestas()) {
                if (noAtual == arestaAtual.getOrigem()) {
                    listaDeNosSucessores.add(arestaAtual.getDestino());
                }
            }
            mapaDeNosSucessores.put(noAtual, listaDeNosSucessores);
        }
        return mapaDeNosSucessores;
    }

    private static List<No> listaNosIsolados(Map<No, Integer> mapa) {
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

    private static List<No> listaVerticesIndependentes(int[][] matriz, Grafo grafo) {
        int i = 0;
        Map<No, Integer> nosDoGrafo = new HashMap<No, Integer>();
        Map<Aresta, Integer> arestasDoGrafo = new HashMap<Aresta, Integer>();
        for (No no : grafo.getNos()) {
            nosDoGrafo.put(no, i);
            i++;
        }
        i = 0;
        for (Aresta aresta : grafo.getArestas()) {
            arestasDoGrafo.put(aresta, i);
            i++;
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 0) {
                    
                }
            }
        }
    }

    private static List<Aresta> listaArestasIndependentes(int[][] matriz) {

    }
}
