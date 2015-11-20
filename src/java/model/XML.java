package model;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XML {

    private static XStream xstream = new XStream();

    public static void salvaGrafo(Grafo grafo) {
        
        xstream.alias("graph", Grafo.class);
        xstream.useAttributeFor(Grafo.class, "tipo");
        xstream.useAttributeFor(Grafo.class, "id");
        xstream.aliasField("edgedefault", Grafo.class, "tipo");
        
        xstream.alias("node", No.class);
        
        xstream.alias("edge", Aresta.class);
        
        xstream.addImplicitCollection(Grafo.class, "nos");
        xstream.addImplicitCollection(Grafo.class, "arestas");
        
        xstream.useAttributeFor(Aresta.class, "origem");
        xstream.useAttributeFor(Aresta.class, "destino");
        xstream.registerConverter(new NoConverter());
        
        xstream.aliasField("source", Aresta.class, "origem");
        xstream.aliasField("target", Aresta.class, "destino");
        
        try {
            File arquivo = new File("arquivo.xml"); 
            xstream.toXML(grafo, new FileWriter(arquivo));
        } catch (IOException ex) {
            System.err.println("Erro ao abrir arquivo! ");
        }
    }
    
    public static Grafo abrirGrafo(File file) {
        return (Grafo) xstream.fromXML(file);
    }
}
