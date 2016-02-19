package model;

import java.util.ArrayList;
import java.util.Collection;
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
        String noSelecionado = null, proximoNoSelecionado = null;
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
        do {
            listaVarridos.add(noSelecionado);
            arestasAdjacentes = getArestasDoNoAtual(No.getNoById(noSelecionado, this.nos));
            menorEstimativa = dijkstraListaEstimativas.get(arestasAdjacentes.get(0).getDestino().getId());

            for (Aresta arestaAdjacente : arestasAdjacentes) {
                dijkstraListaPrecedentes.replace(arestaAdjacente.getDestino().getId(), noSelecionado);
                dijkstraListaEstimativas.replace(arestaAdjacente.getDestino().getId(),
                        arestaAdjacente.getValor()
                        + dijkstraListaEstimativas.get(noSelecionado));
                if (menorEstimativa >= dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId())) {
                    menorEstimativa = dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId());
                    proximoNoSelecionado = arestaAdjacente.getDestino().getId();
                }
            }
            noSelecionado = proximoNoSelecionado;
        } while (listaVarridos.size() != this.nos.size());

        return dijkstraListaPrecedentes;
    }

    public List<No> calcularDijkstra(No inicio, No destino) {
        Map<String, String> listaPrecedentes = calcularDijkstra(inicio);
        List<No> caminho = new ArrayList<No>();
        String noAnterior = destino.getId();
        do {
            caminho.add(this.getNo(noAnterior));
            noAnterior = listaPrecedentes.get(destino.getId());
        } while (!noAnterior.equals(inicio.getId()));

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
}
