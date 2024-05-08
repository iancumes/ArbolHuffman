
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class Tests {

    @Test
    public void testCalcularFrecuencias() throws IOException {
        String archivoTexto = "texto_prueba.txt";
        Map<Character, Integer> frecuencias = CompresorHuffman.calcularFrecuencias(archivoTexto);
        
        assertEquals(4366, frecuencias.get('a').intValue());
    }

    @Test
    public void testConstruirArbolHuffman() throws IOException {
        String archivoTexto = "texto_prueba.txt";
        Map<Character, Integer> frecuencias = CompresorHuffman.calcularFrecuencias(archivoTexto);
        Nodo raiz = CompresorHuffman.construirArbolHuffman(frecuencias);

        assertEquals('\0', raiz.caracter);
        assertEquals(66580, raiz.frecuencia);
    }

    @Test
    public void testGuardarYGenerarArchivoHuffman() throws IOException {
        String archivoTexto = "texto_prueba.txt";
        String archivoArbol = "arbol.tree";
        String archivoHuffman = "arbol.huff";

        Map<Character, Integer> frecuencias = CompresorHuffman.calcularFrecuencias(archivoTexto);
        Nodo raiz = CompresorHuffman.construirArbolHuffman(frecuencias);
        CompresorHuffman.guardarArbol(raiz, archivoArbol);
        CompresorHuffman.generarArchivoHuffman(raiz, archivoHuffman);

    
    }
}
