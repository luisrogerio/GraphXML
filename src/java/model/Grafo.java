package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public public String getId() {
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
        for (No no : this.nos) {
            if (id.equals(no.getId())) {
                return no;
            }
        }
        return null;
    }

    public void setNos(List<No> nos) {
        this.nos = nos;
    }

    public List<Aresta> getArestas() {
        return arestas;
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

    public Map<No, Integer> calcularDijkstra(No inicio) {
        List<Aresta> arestasGrafo = this.getArestas();
        List<No> nosGrafo = this.getNos();
        int valorDistancia;
        Map<No, Integer> dijkstra = new HashMap();
        List<Aresta> arestasDoNoAtual;

        for (No noAtual : nosGrafo) {
            if (inicio.getId().equals(noAtual.getId())) { //se o nó esta tentando calcular a distancia para ele mesmo
                valorDistancia = 0;
                dijkstra.put(noAtual, valorDistancia);
            } else {
                arestasDoNoAtual = 
                for ( ) {

                    if () {

                    }
                }
            }
        }

    }

    public int calcularDijkstra(No inicio, No destino) {
        List<Aresta> arestasGrafo = this.getArestas();
        List<No> nosGrafo = this.getNos();
        int valorDistancia;
        List<Aresta> arestasDoNoAtual;
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
            if (no.getId().equals(arestaAtual.getOrigem())) {
                nosAdjacentes.add(arestaAtual.getOrigem());
            }
        }
        return nosAdjacentes;
    }

    public Map<No, List<No>> getMapaVerticesAdjacentes(List<No> nos) {
        Map<No, List<No>> mapaVerticesAdj = new HashMap();
        List<No> nosAdj = new ArrayList();
        for (No noAtual : nos) {
            nosAdj = this.gerarVerticesAdjacentes(noAtual);
            if (!nosAdj.isEmpty()) {
                mapaVerticesAdj.put(noAtual, nosAdj);
            }
        }
        return mapaVerticesAdj;
    }
}
