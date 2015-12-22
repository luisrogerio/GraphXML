package model;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XML {

    private static XStream xstream = new XStream();
    
    private static void inicializaXStream() {
        xstream.alias("graph", Grafo.class);
        xstream.useAttributeFor(Grafo.class, "tipo");
        xstream.useAttributeFor(Grafo.class, "id");
        xstream.aliasField("edgedefault", Grafo.class, "tipo");
        
        xstream.alias("node", No.class);
        xstream.alias("edge", Aresta.class);
        
        xstream.addImplicitCollection(Grafo.class, "nos", No.class);
        xstream.addImplicitCollection(Grafo.class, "arestas", Aresta.class);
        
        xstream.useAttributeFor(No.class, "id");
        
        xstream.useAttributeFor(Aresta.class, "id");
        xstream.useAttributeFor(Aresta.class, "origem");
        xstream.useAttributeFor(Aresta.class, "destino");
        xstream.registerLocalConverter(Aresta.class, "origem", new NoConverter());
        xstream.registerLocalConverter(Aresta.class, "destino", new NoConverter());
        
        xstream.aliasField("source", Aresta.class, "origem");
        xstream.aliasField("target", Aresta.class, "destino");
    }

    public static void salvaGrafo(Grafo grafo, String path) {
        inicializaXStream();
        try {
            File arquivo = new File(path + "../../Grafo/graph.xml"); 
            xstream.toXML(grafo, new FileWriter(arquivo));
            System.out.println("CAMINHO PARA O ARQUIVO: "+arquivo.getCanonicalPath());
        } catch (IOException ex) {
            System.err.println("Erro ao abrir arquivo! ");
        }
    }
    
    public static void salvaGrafo(Grafo grafo, String path, String nome) {
        inicializaXStream();
        try {
            File arquivo = new File(path + "../../Grafo/"+nome+".xml"); 
            xstream.toXML(grafo, new FileWriter(arquivo));
            System.out.println("CAMINHO PARA O ARQUIVO: "+arquivo.getCanonicalPath());
        } catch (IOException ex) {
            System.err.println("Erro ao abrir arquivo! ");
        }
    }
    
    public static Grafo abrirGrafo(File file) {
        inicializaXStream();
        Grafo grafo = (Grafo) xstream.fromXML(file);
        return grafo;
    }
}
