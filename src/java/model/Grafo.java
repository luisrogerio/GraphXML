package model;

import java.util.ArrayList;
import java.util.Collection;
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
        return No.getNoById(id, this.nos);
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
            if (arestasAdjacentes.get(0).getOrigem().getId().equals(noSelecionado)) {
                menorEstimativa = dijkstraListaEstimativas.get(arestasAdjacentes.get(0).getDestino().getId());
            } else {
                menorEstimativa = dijkstraListaEstimativas.get(arestasAdjacentes.get(0).getOrigem().getId());
            }

            for (Aresta arestaAdjacente : arestasAdjacentes) {
                if (arestaAdjacente.getOrigem().getId().equals(noSelecionado)) {
                    dijkstraListaPrecedentes.replace(arestaAdjacente.getDestino().getId(), noSelecionado);
                    dijkstraListaEstimativas.replace(arestaAdjacente.getDestino().getId(),
                            arestaAdjacente.getValor()
                            + dijkstraListaEstimativas.get(noSelecionado));
                } else {
                    dijkstraListaPrecedentes.replace(arestaAdjacente.getOrigem().getId(), noSelecionado);
                    dijkstraListaEstimativas.replace(arestaAdjacente.getOrigem().getId(),
                            arestaAdjacente.getValor()
                            + dijkstraListaEstimativas.get(noSelecionado));
                }
                if (arestaAdjacente.getOrigem().getId().equals(noSelecionado)) {
                    if (menorEstimativa >= dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId())) {
                        menorEstimativa = dijkstraListaEstimativas.get(arestaAdjacente.getDestino().getId());
                        proximoNoSelecionado = arestaAdjacente.getDestino().getId();
                    }
                } else {
                    if (menorEstimativa >= dijkstraListaEstimativas.get(arestaAdjacente.getOrigem().getId())) {
                        menorEstimativa = dijkstraListaEstimativas.get(arestaAdjacente.getOrigem().getId());
                        proximoNoSelecionado = arestaAdjacente.getOrigem().getId();
                    }
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
