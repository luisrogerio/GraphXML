/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LuísRogério
 */
public class Grafo {

    private String id;
    private String tipo;
    private List<No> nos;
    private List<Aresta> arestas;

    public Grafo(String id, String tipo, List<No> nos, List<Aresta> arestas) {
        this.id = id;
        this.tipo = tipo;
        this.nos = nos;
        this.arestas = arestas;
    }
    
    public int getOrdem(){
        return this.nos.size();
    }
    
    public Map<No, Integer> getGraus(){
        Map<No, Integer> grausDosNos = new HashMap<No, Integer>();
        for (No no : this.nos) {
            int grau = 0;
            for(Aresta aresta : this.arestas){
                if(aresta.getDestino().getId().equals(no.getId())){
                    grau++;
                }
                if(aresta.getOrigem().getId().equals(no.getId())){
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        }
        return grausDosNos;
    }
    
    public Map<No, Integer> getGrausDeEmissao(){
        Map<No, Integer> grausDosNos = new HashMap<No, Integer>();
        for (No no : this.nos) {
            int grau = 0;
            for(Aresta aresta : this.arestas){
                if(aresta.getOrigem().getId() == no.getId()){
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        }
        return grausDosNos;
    }
    
    public Map<No, Integer> getGrausDeRecepcao(){
        Map<No, Integer> grausDosNos = new HashMap<No, Integer>();
        for (No no : this.nos) {
            int grau = 0;
            for(Aresta aresta : this.arestas){
                if(aresta.getDestino().getId() == no.getId()){
                    grau++;
                }
                grausDosNos.put(no, grau);
            }
        }
        return grausDosNos;
    }
    
    public int[][] getMatrizAdjacencia(Grafo grafo){
        int matriz[][] = new int[grafo.nos.size()][grafo.nos.size()];
        Map<No, Integer> nosDoGrafo = new HashMap<No, Integer>();
        int i = 0;
        for(No no: grafo.nos){
            nosDoGrafo.put(no, i);
            i++;
        }
        for(Aresta aresta: grafo.arestas){
            matriz[nosDoGrafo.get(aresta.getOrigem())][nosDoGrafo.get(aresta.getDestino())] = 1;
            matriz[nosDoGrafo.get(aresta.getDestino())][nosDoGrafo.get(aresta.getOrigem())] = 1;
        }
        return matriz;
    }
    
    public int[][] getMatrizIncidencia(Grafo grafo){
        int matriz[][] = new int[grafo.nos.size()][grafo.arestas.size()];
        Map<No, Integer> nosDoGrafo = new HashMap<No, Integer>();
        Map<Aresta, Integer> arestasDoGrafo = new HashMap<Aresta, Integer>();
        int i = 0;
        for(No no: grafo.nos){
            nosDoGrafo.put(no, i);
            i++;
        }
        i=0;
        for(Aresta aresta: grafo.arestas){
            arestasDoGrafo.put(aresta, i);
            i++;
        }
        for(Aresta aresta: grafo.arestas){
            matriz[nosDoGrafo.get(aresta.getOrigem())][arestasDoGrafo.get(aresta)] = 1;
            matriz[nosDoGrafo.get(aresta.getDestino())][arestasDoGrafo.get(aresta)] = 1;
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

    public void setNos(List<No> nos) {
        this.nos = nos;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

}
