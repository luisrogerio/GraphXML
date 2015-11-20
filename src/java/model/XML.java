package model;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XML {

    private static XStream xstream = new XStream();

    public static void salvaGrafo(Grafo grafo) {
        xstream.alias("graph", Grafo.class);
        xstream.useAttributeFor(Grafo.class, "type");
        xstream.useAttributeFor(Grafo.class, "id");
        xstream.aliasField("edgedefault", Grafo.class, "type");
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
