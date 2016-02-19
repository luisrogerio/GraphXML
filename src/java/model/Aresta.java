/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author LuísRogério
 */
public class Aresta {

    private String id;
    private No origem;
    private No destino;
    private Integer valor;

    public Aresta(String id, No origem, No destino, Integer valor) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    public Aresta(String id, No origem, No destino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
    }
    
    
    public Aresta(No origem, No destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public No getOrigem() {
        return origem;
    }

    public void setOrigem(No origem) {
        this.origem = origem;
    }

    public No getDestino() {
        return destino;
    }

    public void setDestino(No destino) {
        this.destino = destino;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public static Aresta getArestaById(String id, List<Aresta> arestas) {
        for (Aresta aresta : arestas) {
            if (aresta.getId().equals(id)) {
                return aresta;
            }
        }
        return null;
    }
}
