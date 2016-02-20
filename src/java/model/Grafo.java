package model;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {

    private String id;
    private String tipo;
    private boolean tipoAresta;
    private List<No> nos;
    private List<Aresta> arestas;

    public Grafo(String id, String tipo, boolean tipoAresta, List<No> nos, List<Aresta> arestas) {
        this.id = id;
        this.tipo = tipo;
        this.tipoAresta = tipoAresta;
        this.nos = nos;
        this.arestas = arestas;
    }

    public int getOrdem() {
        return this.getNos().size();
    }

    public Map<No, Integer> getGraus() {
        Map<No, Integer> grausDosNos = new HashMap<No, Integer>();
        for (No no : this.nos) {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getDestino().getId().equals(no.getId())) {
                    grau++;
                }
                if (aresta.getOrigem().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        }
        return grausDosNos;
    }

    public Map<No, Integer> getGrausDeEmissao() {
        Map<No, Integer> grausDosNos = new HashMap<No, Integer>();
        for (No no : this.nos) {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getOrigem().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        }
        return grausDosNos;
    }

    public Map<No, Integer> getGrausDeRecepcao() {
        Map<No, Integer> grausDosNos = new HashMap<No, Integer>();
        for (No no : this.nos) {
            int grau = 0;
            for (Aresta aresta : this.arestas) {
                if (aresta.getDestino().getId().equals(no.getId())) {
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        }
        return grausDosNos;
    }

    public int[][] getMatrizAdjacencia() {
        int matriz[][] = new int[this.nos.size()][this.nos.size()];
        Map<String, Integer> nosDoGrafo = new HashMap<String, Integer>();
        int i = 0;
        for (No no : this.nos) {
            nosDoGrafo.put(no.getId(), i);
            i++;
        }
        for (Aresta aresta : this.arestas) {
            matriz[nosDoGrafo.get(aresta.getOrigem().getId())][nosDoGrafo.get(aresta.getDestino().getId())] = 1;
            matriz[nosDoGrafo.get(aresta.getDestino().getId())][nosDoGrafo.get(aresta.getOrigem().getId())] = 1;
        }
        return matriz;
    }

    public int[][] getMatrizIncidencia() {
        int matriz[][] = new int[this.nos.size()][this.arestas.size()];
        Map<String, Integer> nosDoGrafo = new HashMap<String, Integer>();
        Map<String, Integer> arestasDoGrafo = new HashMap<String, Integer>();
        int i = 0;
        for (No no : this.nos) {
            nosDoGrafo.put(no.getId(), i);
            i++;
        }
        i = 0;
        for (Aresta aresta : this.arestas) {
            arestasDoGrafo.put(aresta.getId(), i);
            i++;
        }
        for (Aresta aresta : this.arestas) {
            matriz[nosDoGrafo.get(aresta.getOrigem().getId())][arestasDoGrafo.get(aresta.getId())] = 1;
            matriz[nosDoGrafo.get(aresta.getDestino().getId())][arestasDoGrafo.get(aresta.getId())] = 1;
        }
        return matriz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<No> getNos() {
        return nos;
    }

    public No getNo(String id) {
        return No.getNoById(id, this.getNos());
    }

    public void setNos(List<No> nos) {
        this.nos = nos;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public Aresta getAresta(String id) {
        return Aresta.getArestaById(id, this.getArestas());
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public boolean getTipoAresta() {
        return tipoAresta;
    }

    public void setTipoAresta(boolean tipoAresta) {
        this.tipoAresta = tipoAresta;
    }

    public Map<String, String> calcularDijkstra(No inicio) {
        Map<String, Integer> dijkstraListaEstimativas = new HashMap<String, Integer>();
        Map<String, String> dijkstraListaPrecedentes = new HashMap<String, String>();
        List<String> listaVarridos = new ArrayList<String>();
        List<Aresta> arestasAdjacentes = null;
        String noSelecionado = null;
        int menorEstimativa = 0;
        for (No no : this.nos) {
            dijkstraListaPrecedentes.put(no.getId(), "0");
            if (inicio.getId().equals(no.getId())) {
                noSelecionado = no.getId();
                dijkstraListaEstimativas.put(no.getId(), 0);
                dijkstraListaPrecedentes.put(no.getId(), no.getId());
            } else {
                dijkstraListaEstimativas.put(no.getId(), Integer.MAX_VALUE);
            }
        }
        while (listaVarridos.size() != this.nos.size()) {
            listaVarridos.add(noSelecionado);
            arestasAdjacentes = getArestasQueSaemDoNoAtualDijkstra(No.getNoById(noSelecionado, this.nos), listaVarridos);
            if (!arestasAdjacentes.isEmpty()) {
                if (arestasAdjacentes.get(0).getOrigem().getId().equals(noSelecionado)) {
                    menorEstimativa = dijkstraListaEstimativas.get(arestasAdjacentes.get(0).getDestino().getId());
                }

                for (Aresta arestaAdjacente : arestasAdjacentes) {
                    if (arestaAdjacente.getOrigem().getId().equals(noSelecionado)) {
                        dijkstraListaPrecedentes.replace(arestaAdjacente.getDestino().getId(), noSelecionado);
                        dijkstraListaEstimativas.replace(arestaAdjacente.getDestino().getId(),
                                arestaAdjacente.getValor()
                                + dijkstraListaEstimativas.get(noSelecionado));
                    }
                    if (arestaAdjacente.getOrigem().getId().equals(noSelecionado)) {
                        if (menorEstimativa > dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId())) {
                            dijkstraListaPrecedentes.replace(arestaAdjacente.getDestino().getId(), noSelecionado);
                            menorEstimativa = dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId());
                        }
                    }
                }
            noSelecionado = this.menorEstimativaDijkstra(dijkstraListaEstimativas, listaVarridos);
            }
        }
        return dijkstraListaPrecedentes;
    }

    public String menorEstimativaDijkstra(Map<String, Integer> listaEstimativas, List<String> listaVarridos) {
        String verticeMenor = null;
        int menorEstimativa = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> mapa : listaEstimativas.entrySet()) {
            String origem = mapa.getKey();
            int estimativa = mapa.getValue();
            if ((menorEstimativa > estimativa) && !listaVarridos.contains(origem)) {
                menorEstimativa = estimativa;
                verticeMenor = origem;
            }
        }
        return verticeMenor;
    }

    public List<No> calcularDijkstra(No inicio, No destino) {
        Map<String, String> listaPrecedentes = calcularDijkstra(inicio);
        List<No> caminho = new ArrayList<No>();
        String noAnterior = destino.getId();
        do {
            caminho.add(this.getNo(noAnterior));
            noAnterior = listaPrecedentes.get(noAnterior);
        } while (!noAnterior.equals(inicio.getId()));
        caminho.add(inicio);
        Collections.reverse(caminho);
        return caminho;
    }

    public List<Aresta> getArestasDoNoAtual(No no) {
        List<Aresta> arestasDoNoAtual = new ArrayList();
        for (Aresta aresta : this.getArestas()) {
            if (aresta.getOrigem().getId().equals(no.getId()) || aresta.getDestino().getId().equals(no.getId())) {
                arestasDoNoAtual.add(aresta);
            }
        }
        return arestasDoNoAtual;
    }

    public List<Aresta> getArestasQueSaemDoNoAtualDijkstra(No no, List<String> listaVarridos) {
        List<Aresta> arestasDoNoAtual = new ArrayList();
        for (Aresta aresta : this.getArestas()) {
            if ((aresta.getOrigem().getId().equals(no.getId())) && !listaVarridos.contains(aresta.getDestino().getId())) {
                arestasDoNoAtual.add(aresta);
            }
        }
        return arestasDoNoAtual;
    }

    public List<No> gerarVerticesAdjacentes(No no) {//funcao para criar a lista de vertices adjacentes.
        List<Aresta> arestasDoNo = this.getArestasDoNoAtual(no);
        List<No> nosAdjacentes = new ArrayList();

        for (Aresta arestaAtual : arestasDoNo) {
            nosAdjacentes.add(arestaAtual.getDestino());
            if (no.getId().equals(arestaAtual.getOrigem().getId())) {
                nosAdjacentes.add(arestaAtual.getOrigem());
            }
        }
        return nosAdjacentes;
    }

    public Map<String, List<String>> getMapaArestasAdjacentes() {
        int[][] matrizIncidencia = this.getMatrizIncidencia();
        Map<Integer, String> posicaoArestasDoGrafo = new HashMap<Integer, String>();
        Map<String, List<String>> arestasAdjacentes = new HashMap<String, List<String>>();
        //List<Aresta> arestasDoGrafo = this.getArestas();
        List<Integer> nosAdjacentes = new ArrayList();
        List<String> listaArestasAdjacentes = new ArrayList();
        Set<String> listaSemRepeticoes = new HashSet<String>();
        int numeroTotalArestas = this.getArestas().size();
        int i = 0, j = 0, k = 0;

        for (Aresta aresta : this.getArestas()) {
            posicaoArestasDoGrafo.put(i, aresta.getId());
            i++;
        }
        // for (Aresta arestaAtual : arestasDoGrafo) {
        for (j = 0; j < numeroTotalArestas; j++) {
            listaArestasAdjacentes = new ArrayList<String>();
            nosAdjacentes = new ArrayList<Integer>();
            listaSemRepeticoes = new HashSet<String>();
            for (i = 0; i < this.getNos().size(); i++) {
                if (matrizIncidencia[i][j] == 1) { //guardo a posição da linha da matriz
                    nosAdjacentes.add(i);
                }

            }
            for (int linhaAtual : nosAdjacentes) {
                for (k = 0; k < numeroTotalArestas; k++) {
                    if (matrizIncidencia[linhaAtual][k] == 1) {
                        listaArestasAdjacentes.add(posicaoArestasDoGrafo.get(k));
                    }
                }//não estou varrendo as aresta direitos, na linha abaixo não vou para a proxima aresta pq estou usando o i e nao outra variavel?
            }
            listaSemRepeticoes.addAll(listaArestasAdjacentes);
            listaArestasAdjacentes.clear();
            listaArestasAdjacentes.addAll(listaSemRepeticoes);
            listaArestasAdjacentes.remove(posicaoArestasDoGrafo.get(j));
            arestasAdjacentes.put(posicaoArestasDoGrafo.get(j), listaArestasAdjacentes);
        }
        return arestasAdjacentes;
    }

    public Map<String, List<String>> getMapaVerticesAdjacentes() {
        Map<Integer, String> posicaoNosDoGrafo = new HashMap<Integer, String>();
        int[][] matrizAdj = this.getMatrizAdjacencia();
        int i = 0, j;
        String nomeNo = null;
        Map<String, List<String>> mapaVerticesAdj = new HashMap<String, List<String>>();
        List<String> nosAdj = null;

        for (No no : this.getNos()) {
            posicaoNosDoGrafo.put(i, no.getId());
            i++;
        }
        i = 0;
        while (i < this.getNos().size()) {
            nosAdj = new ArrayList();
            for (j = 0; j < this.getNos().size(); j++) {
                if (matrizAdj[j][i] == 1) {
                    nomeNo = posicaoNosDoGrafo.get(j);
                    nosAdj.add(nomeNo);
                }
            }
            if (!nosAdj.isEmpty()) {
                mapaVerticesAdj.put(posicaoNosDoGrafo.get(i), nosAdj);
            }
            i++;
        }
        return mapaVerticesAdj;
    }

    public Map<String, List<No>> getVerticesIndependentes() {
        Set<No> gerarNosIndependentes = null;
        Map<String, List<No>> nosIndependentes = new HashMap<String, List<No>>();
        List<No> nosAdjacentes = null;
        List<No> listaNosIndependentes = null;
        Map<String, List<String>> verticesAdjacentes = this.getMapaVerticesAdjacentes();

        for (Map.Entry<String, List<String>> entry : verticesAdjacentes.entrySet()) {
            String verticeAtual = entry.getKey();
            List<String> listaVerticesAdjacentes = entry.getValue();
            gerarNosIndependentes = new HashSet<No>();
            listaNosIndependentes = new ArrayList();
            nosAdjacentes = new ArrayList();

            for (String vertice : listaVerticesAdjacentes) {
                nosAdjacentes.add(this.getNo(vertice));
            }
            nosAdjacentes.add(this.getNo(verticeAtual));

            for (No noDoGrafo : this.getNos()) {
                if (!nosAdjacentes.contains(noDoGrafo)) {
                    listaNosIndependentes.add(noDoGrafo);
                }
            }

            nosIndependentes.put(verticeAtual, listaNosIndependentes);
        }
        return nosIndependentes;
    }

    public Map<String, List<Aresta>> getArestasIndependentes() {
        Map<String, List<String>> arestasAdjacentes = this.getMapaArestasAdjacentes();
        Map<String, List<Aresta>> arestasIndependentes = new HashMap<String, List<Aresta>>();
        List<Aresta> listaArestasAdjacentes = null;
        List<Aresta> listaArestas = null;

        for (Map.Entry<String, List<String>> entry : arestasAdjacentes.entrySet()) {
            String arestaAtual = entry.getKey();
            List<String> value = entry.getValue();
            listaArestasAdjacentes = new ArrayList<Aresta>();
            listaArestas = new ArrayList<Aresta>();
            listaArestas.add(this.getAresta(arestaAtual));

            for (String idAresta : value) {
                listaArestas.add(this.getAresta(idAresta));
            }

            for (Aresta arestaDoGrafo : this.getArestas()) {
                if (!listaArestas.contains(arestaDoGrafo)) {
                    listaArestasAdjacentes.add(arestaDoGrafo);
                }
            }
            arestasIndependentes.put(arestaAtual, listaArestasAdjacentes);
        }
        return arestasIndependentes;
    }

    public Grafo algoritmoDeKruskal() {
        Grafo subGrafo = new Grafo(this.getId(), this.getTipo(), this.getTipoAresta(), this.getNos(), new ArrayList<Aresta>());
        List<Aresta> arestasDoGrafo = new ArrayList<Aresta>();
        arestasDoGrafo.addAll(this.getArestas());
        Map<String, Integer> nosDoGrafo = new HashMap<String, Integer>();
        int numeroVertices = this.getNos().size(), i = 0;
        int[][] matrizComponentes = new int[2][this.getNos().size()];
        Collections.sort(arestasDoGrafo, new ArestaComparator());
        for (No no : this.nos) {
            nosDoGrafo.put(no.getId(), i);
            matrizComponentes[0][i] = i;
            matrizComponentes[1][i] = i;
            i++;
        }
        while ((subGrafo.getArestas().size() <= numeroVertices - 1) && !arestasDoGrafo.isEmpty()) {
            Aresta aresta = arestasDoGrafo.get(0);
            arestasDoGrafo.remove(aresta);
            int componente_u = this.getComponenteKruskal(matrizComponentes, nosDoGrafo.get(aresta.getOrigem().getId()));
            int componente_v = this.getComponenteKruskal(matrizComponentes, nosDoGrafo.get(aresta.getDestino().getId()));
            if (componente_u != componente_v) {
                this.mergeComponenteKruskal(matrizComponentes, componente_u, componente_v);
                subGrafo.adicionarAresta(aresta);
            }
        }
        return subGrafo;
    }

    public int getComponenteKruskal(int[][] matriz, int componente) {
        int valor = matriz[1][componente];
        return valor;
    }

    public int[][] mergeComponenteKruskal(int[][] matriz, int componente_1, int componente_2) {
        int i, componenteATrocada = matriz[1][componente_2];
        for (i = 0; i < matriz[0].length; i++) {
            if (matriz[1][i] == componenteATrocada) {
                matriz[1][i] = matriz[1][componente_1];
            }
        }
        return matriz;
    }

    public void adicionarAresta(Aresta aresta) {
        List<Aresta> arestas = this.getArestas();
        arestas.add(aresta);
        this.setArestas(arestas);
    }

    public Grafo algoritmoDePrim() {
        Grafo subGrafo = new Grafo(this.getId(), this.getTipo(), this.getTipoAresta(), new ArrayList<No>(), new ArrayList<Aresta>());
        List<String> verticesDoGrafo = new ArrayList<String>();
        for (No no : this.getNos()) {
            verticesDoGrafo.add(no.getId());
        }
        List<String> verticesDoSubGrafo = new ArrayList<String>();
        verticesDoSubGrafo.add(verticesDoGrafo.get(0));
        while (!(verticesDoSubGrafo.size() == this.getNos().size())) {
            Aresta aresta = getArestaPrim(verticesDoGrafo, verticesDoSubGrafo);
            subGrafo.adicionarAresta(aresta);
            verticesDoSubGrafo.add(aresta.getOrigem().getId());
        }
        List<No> nosDoSubGrafo = new ArrayList<No>();
        for (String vertice : verticesDoSubGrafo) {
            nosDoSubGrafo.add(new No(vertice));
        }
        subGrafo.setNos(nosDoSubGrafo);
        return subGrafo;
    }

    public Aresta getArestaPrim(List<String> verticesOriginal, List<String> verticesArvore) {
        List<Aresta> arestasOriginal = new ArrayList<Aresta>();
        arestasOriginal.addAll(this.getArestas());
        Collections.sort(arestasOriginal, new ArestaComparator());
        List<String> diferencaEntreArestas = new ArrayList<String>();
        verticesOriginal.removeAll(verticesArvore);
        diferencaEntreArestas.addAll(verticesOriginal);
        for (Aresta aresta : arestasOriginal) {
            if ((diferencaEntreArestas.contains(aresta.getOrigem().getId()) && verticesArvore.contains(aresta.getDestino().getId()))) {
                return aresta;
            }
            if ((diferencaEntreArestas.contains(aresta.getDestino().getId()) && verticesArvore.contains(aresta.getOrigem().getId()))) {
                return new Aresta(aresta.getId(), aresta.getDestino(), aresta.getOrigem(), aresta.getValor());
            }
        }
        return null;
    }
    
    public Map<String, List<String>> getNosAntecessores(){
        Map<String, List<String>> listaNosAntecessores = new HashMap<String, List<String>>();
        for(No no : this.getNos()){
            listaNosAntecessores.put(no.getId(), this.getNosAntecessores(no));
        }
        return listaNosAntecessores;
    }
    
    public List<String> getNosAntecessores(No no){
        List<String> nosAntecessores = new ArrayList<String>();
        for (Aresta aresta : this.getArestas()){
            if(aresta.getDestino().getId().equals(no.getId())){
                nosAntecessores.add(aresta.getOrigem().getId());
            }
        }
        return nosAntecessores;
    }
    
    public Map<String, List<String>> getNosSucessores(){
        Map<String, List<String>> listaNosSucessores = new HashMap<String, List<String>>();
        for(No no : this.getNos()){
            listaNosSucessores.put(no.getId(), this.getNosSucessores(no));
        }
        return listaNosSucessores;
    }
    
    public List<String> getNosSucessores(No no){
        List<String> nosSucessores = new ArrayList<String>();
        for (Aresta aresta : this.getArestas()){
            if(aresta.getOrigem().getId().equals(no.getId())){
                nosSucessores.add(aresta.getDestino().getId());
            }
        }
        return nosSucessores;
    }

}
