import java.io.*;
import java.util.*;
public class CompresorHuffman {

    public static Map<Character, Integer> calcularFrecuencias(String archivoTexto) throws IOException {
        Map<Character, Integer> frecuencias = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoTexto))) {
            int c;
            while ((c = reader.read()) != -1) {
                char caracter = (char) c;
                frecuencias.put(caracter, frecuencias.getOrDefault(caracter, 0) + 1);
            }
        }
        return frecuencias;
    }

    public static Nodo construirArbolHuffman(Map<Character, Integer> frecuencias) {
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(n -> n.frecuencia));
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            cola.offer(new Nodo(entry.getKey(), entry.getValue(), null, null));
        }

        while (cola.size() > 1) {
            Nodo izquierda = cola.poll();
            Nodo derecha = cola.poll();
            Nodo padre = new Nodo('\0', izquierda.frecuencia + derecha.frecuencia, izquierda, derecha);
            cola.offer(padre);
        }

        return cola.poll();
    }

    public static void guardarArbol(Nodo raiz, String archivoArbol) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(archivoArbol))) {
            output.writeObject(raiz);
        }
    }

    public static void generarArchivoHuffman(Nodo raiz, String archivoHuffman) throws IOException {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(archivoHuffman))) {
            escribirArbolEnArchivo(raiz, output);
        }
    }

    private static void escribirArbolEnArchivo(Nodo nodo, DataOutputStream output) throws IOException {
        if (nodo.esHoja()) {
            output.writeBoolean(true);
            output.writeChar(nodo.caracter);
        } else {
            output.writeBoolean(false);
            escribirArbolEnArchivo(nodo.izquierda, output);
            escribirArbolEnArchivo(nodo.derecha, output);
        }
    }

    public static void main(String[] args) throws IOException {
        String archivoTexto = "texto_prueba.txt";
        String archivoArbol = "arbol.tree";
        String archivoHuffman = "arbol.huff";

        // Calcular frecuencias de caracteres en el archivo de texto
        Map<Character, Integer> frecuencias = calcularFrecuencias(archivoTexto);

        // Construir el árbol de Huffman
        Nodo raiz = construirArbolHuffman(frecuencias);

        // Guardar el árbol de Huffman en un archivo .tree
        guardarArbol(raiz, archivoArbol);

        // Generar archivo .huff con el árbol de Huffman
        generarArchivoHuffman(raiz, archivoHuffman);

        System.out.println("Compresión completada. Archivos " + archivoArbol + " y " + archivoHuffman + " creados.");
    }
}