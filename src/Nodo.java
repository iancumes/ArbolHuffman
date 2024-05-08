import java.io.*;
import java.util.*;

class Nodo implements Comparable<Nodo>, Serializable {
    char caracter;
    int frecuencia;
    Nodo izquierda, derecha;

    public Nodo(char caracter, int frecuencia, Nodo izquierda, Nodo derecha) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public boolean esHoja() {
        return izquierda == null && derecha == null;
    }

    @Override
    public int compareTo(Nodo otro) {
        return this.frecuencia - otro.frecuencia;
    }
}
