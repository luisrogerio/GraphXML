package model;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XML {

    private static XStream xstream = new XStream();

    public static void salvaGrafo(Grafo grafo, String path) {
        xstream.alias("graph", Grafo.class);
        xstream.useAttributeFor(Grafo.class, "tipo");
        xstream.useAttributeFor(Grafo.class, "id");
        xstream.aliasField("edgedefault", Grafo.class, "tipo");
        
        xstream.alias("node", No.class);
        xstream.alias("edge", Aresta.class);
        
        xstream.addImplicitCollection(Grafo.class, "nos");
        xstream.addImplicitCollection(Grafo.class, "arestas");
        
        xstream.useAttributeFor(No.class, "id");
        
        xstream.useAttributeFor(Aresta.class, "origem");
        xstream.useAttributeFor(Aresta.class, "destino");
        xstream.registerLocalConverter(Aresta.class, "origem", new NoConverter());
        xstream.registerLocalConverter(Aresta.class, "destino", new NoConverter());
        
        xstream.aliasField("source", Aresta.class, "origem");
        xstream.aliasField("target", Aresta.class, "destino");
        try {
            File arquivo = new File(path + "../../graph.xml"); 
            xstream.toXML(grafo, new FileWriter(arquivo));
        } catch (IOException ex) {
            System.err.println("Erro ao abrir arquivo! ");
        }
    }
    
    public static Grafo abrirGrafo(File file) {
        return (Grafo) xstream.fromXML(file);
    }
}
