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
                if(aresta.getDestino().getId() == no.getId()){
                    grau++;
                }
                if(aresta.getOrigem().getId() == no.getId()){
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
