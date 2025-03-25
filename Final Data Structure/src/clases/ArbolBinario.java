package clases;
import clases.NodoArbol.Nodo; 
import java.util.*; 

class NodoLista {
    NodoArbol.Nodo informacion;
    NodoLista siguiente;

    public NodoLista() {
        this.informacion = null;
        this.siguiente = null;
    }
}

class Pila {
    NodoLista cabeza;

    public Pila() {
        this.cabeza = null;
    }

    public void push(NodoArbol.Nodo nodo) {
        NodoLista nuevoNodo = new NodoLista();
        nuevoNodo.informacion = nodo;

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
    }

    public NodoArbol.Nodo pop() {
        if (cabeza == null) {
            return null;
        }

        NodoArbol.Nodo nodo = cabeza.informacion;
        cabeza = cabeza.siguiente;

        return nodo;
    }

    public boolean vacia() {
        return cabeza == null;
    }
}

class Cola {
    NodoLista cabeza;
    NodoLista cola;
    int tamaño;

    public Cola() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    public void insert(NodoArbol.Nodo elemento) {
        NodoLista nuevoNodo = new NodoLista();
        nuevoNodo.informacion = elemento;

        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }

        tamaño++;
    }

    public NodoArbol.Nodo remove() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return null;
        }

        NodoArbol.Nodo elemento = cabeza.informacion;
        cabeza = cabeza.siguiente;

        if (cabeza == null) {
            cola = null;
        }

        tamaño--;
        return elemento;
    }

    public NodoArbol.Nodo cabeza() {
        if (estaVacia()) {
            System.out.println("La cola está vacía");
            return null;
        }

        return cabeza.informacion;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }
}


public class ArbolBinario {
    public NodoArbol.Nodo raiz;
    public int numeroNodos;

    public ArbolBinario() {
        this.raiz = null;
        this.numeroNodos = 0;
    }

    public void insertarNodo(String i) {
        NodoArbol.Nodo nodo = new NodoArbol.Nodo(i);
        if (raiz == null) { //Si la raiz del arbol es null, el nodo se convierte en la raiz del árbol
            raiz = nodo;
        } else {
            NodoArbol.Nodo actual = raiz;
            while (true) { //busca el lugar correcto para insertar el nuevo nodo en el árbol
                if (nodo.info.compareTo(actual.info) < 0) { //Comparacion para ver si es menor o mayor el tamaño del nodo
                    if (actual.izquierda == null) {     // si el valor comparado es menor, va a la rama izquierda
                        actual.izquierda = nodo;
                        break;
                    } else {
                        actual = actual.izquierda;
                    }
                } else {
                    if (actual.derecha == null) {
                        actual.derecha = nodo;
                        break;
                    } else {
                        actual = actual.derecha;
                    }
                }
            }
        }
        this.numeroNodos++; // Incrementar el número de nodos cada vez que se inserte un nodo
    }

    public int getNumeroNodos() {
        return this.numeroNodos;
    }

    public boolean buscarNodo(String info, NodoArbol.Nodo raiz2) {
        if (raiz2 == null) {
            return false;
        } else if (info.compareTo(raiz2.info) == 0) { //comparacion valor buscado con nodo actual
            return true;
        } else if (info.compareTo(raiz2.info) < 0) { 
            return buscarNodo(info, raiz2.izquierda);
        } else {
            return buscarNodo(info, raiz2.derecha);
        }
    }

    public void eliminarNodo(String info) {
        raiz = eliminarNodo(raiz, info);
    }

    private NodoArbol.Nodo eliminarNodo(NodoArbol.Nodo nodo, String info) {
        if (nodo == null) { // si es null llego al final y no encontro el valor
            System.out.println("El nodo " + info + " no se encuentra en el árbol");
            return nodo;
        }
        if (info.compareTo(nodo.info) < 0) { //Si la informacion del nodo a eliminar es menor a la informacion del nodo actual, se busca el nodo en el subarbol izquierdo
            nodo.izquierda = eliminarNodo(nodo.izquierda, info);
        } else if (info.compareTo(nodo.info) > 0) { //Si la informacion del nodo a eliminar es mayor a la informacion del nodo actual, se busca el nodo en el subarbol derecho
            nodo.derecha = eliminarNodo(nodo.derecha, info);
        } else {
            if (nodo. izquierda == null) { //Si el nodo actual no tiene un hijo izquierdo, se retorna el hijo derecho del nodo actual
            return nodo.derecha;           //Si el nodo actual no tiene un hijo derecho, se retorna el hijo izquierdo del nodo actual 
        } else if (nodo.derecha == null) {  //si el nodo actual tiene hijo izquierdo y derecho, se busca el sucesor que se encuentra en el subarbol derecho y es el menor valor
            return nodo.izquierda;
        } else {
        NodoArbol.Nodo sucesor = buscarMenor(nodo.derecha);
        nodo.info = sucesor.info;
        nodo.derecha = eliminarNodo(nodo.derecha, sucesor.info);
        }
        }
            return nodo;
    }

    private NodoArbol.Nodo buscarMenor(NodoArbol.Nodo nodo) { //se encarga de buscar el nodo con el menor valor del subarbol derecho del nodo que se desea eliminar
        while (nodo.izquierda != null) {
        nodo = nodo.izquierda;
        }
        return nodo;
    }

    public void posorden(NodoArbol.Nodo raiz2) {
    if (raiz2 != null) {
        posorden(raiz2.izquierda);
        posorden(raiz2.derecha);
        System.out.print(raiz2.info + ", ");
    }
    }

    public void preorden() {
    if (raiz == null) {
        return;
    }

    Pila pila = new Pila();
    pila.push(raiz);

    while (!pila.vacia()) { // se ejecuta mientra la pila no este vacia
        NodoArbol.Nodo nodo = pila.pop(); //extrae el nodo superior de la pila, imprime su valor y, si tiene hijos, los agrega a la pila(Primero derecho, luego izquierdo)
        System.out.println(nodo.info);

        if (nodo.derecha != null) {
            pila.push(nodo.derecha);
        }

        if (nodo.izquierda != null) {
            pila.push(nodo.izquierda);
        }
    }
    }

    public void inorden(NodoArbol.Nodo raiz2) {
        if (raiz2 != null) {
            inorden(raiz2.izquierda);
            System.out.print(raiz2.info + ", ");
            inorden(raiz2.derecha);
        }
    }

    public int nivelProfundidad() {
        if (raiz == null) {
            return 0;
        }

        Cola cola = new Cola();
        cola.insert(raiz);
        int profundidad = 0;

        while (!cola.estaVacia()) {
            int nivel = cola.tamaño();

            while (nivel > 0) {
                NodoArbol.Nodo nodoActual = cola.remove();

                if (nodoActual.izquierda != null) {
                    cola.insert(nodoActual.izquierda);
                }

                if (nodoActual.derecha != null) {
                    cola.insert(nodoActual.derecha);
                }

                nivel--;
            }

            profundidad++;
        }

        return profundidad;
    }


    

    public void insertarIzquierda(Nodo padre, String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        padre.izquierda = nuevoNodo;
    }

    public void insertarDerecha(Nodo padre, String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        padre.derecha = nuevoNodo;
    }
    
    public void construirArbol(String expresion) {
        Pila pila = new Pila();
        Set<String> operadores = new HashSet<>(Arrays.asList("+", "-", "*", "/", "^"));
    
        for (int i = expresion.length() - 1; i >= 0; i--) {
            String simbolo = expresion.substring(i, i + 1);
    
            if (operadores.contains(simbolo)) {
                Nodo nodo = new Nodo(simbolo);
                nodo.izquierda = pila.pop();
                nodo.derecha = pila.pop();
                pila.push(nodo);
            } else {
                pila.push(new Nodo(simbolo));
            }
        }
    
        raiz = pila.pop();
    }
    /*public void infijo(Nodo nodo) {
        if (nodo != null) {
            if (nodo.izquierda != null || nodo.derecha != null) {
                System.out.print("(");
            }
            infijo(nodo.izquierda);
            System.out.print(nodo.info);
            infijo(nodo.derecha);
            if (nodo.izquierda != null || nodo.derecha != null) {
                System.out.print(")");
                
            }
        }
    } */
    public void infijo(NodoArbol.Nodo nodo) {
        Pila pila = new Pila();
        NodoArbol.Nodo nodoActual = nodo;

        while (nodoActual != null || !pila.vacia()) {
            while (nodoActual != null) {
                if (nodoActual.izquierda != null || nodoActual.derecha != null) {
                    System.out.print("(");
                }

                pila.push(nodoActual);
                nodoActual = nodoActual.izquierda;
            }

            nodoActual = pila.pop();
            System.out.print(nodoActual.info);
            nodoActual = nodoActual.derecha;

            if (nodoActual != null || !pila.vacia()) {
                System.out.print(")");
            }
        }
    }

    
    public void posfijo(Nodo nodo) {
        if (nodo != null) {
            posfijo(nodo.izquierda);
            posfijo(nodo.derecha);
            System.out.print(nodo.info);
        }
    }
    
    public void prefijo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.info);
            prefijo(nodo.izquierda);
            prefijo(nodo.derecha);
        }
    }

}


