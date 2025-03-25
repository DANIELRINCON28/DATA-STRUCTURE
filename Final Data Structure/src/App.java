import java.util.Scanner;
import clases.ArbolBinario;
import clases.NodoArbol;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        NodoArbol nodoarbol = new NodoArbol(null);
        ArbolBinario arbolBinario = new ArbolBinario();
        int opcion = 0;

        do {
            mostrarMenu();
            System.out.print("Ingrese una opción: ");
            opcion = in.nextInt();
            in.nextLine();

            switch(opcion) {
                case 1:
                    System.out.print("Ingrese el valor del nodo para insertar: ");
                    String valor = in.nextLine();
                    arbolBinario.insertarNodo(valor);
                    System.out.println("Nodo insertado con éxito.");
                    break;

                case 2:
                    System.out.print("Ingrese el nodo para buscar: ");
                    String valorBuscar = in.nextLine();
                    boolean encontrado = arbolBinario.buscarNodo(valorBuscar, arbolBinario.raiz);
                    if (encontrado) {
                        System.out.println("El nodo buscado se encuentra en el árbol.");
                    } else {
                        System.out.println("El nodo buscado no se encuentra en el árbol.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el valor del nodo a eliminar: ");
                    valor = in.nextLine();
                    if (arbolBinario.buscarNodo(valor, arbolBinario.raiz)) {
                        arbolBinario.eliminarNodo(valor);
                        System.out.println("El nodo " + valor + " ha sido eliminado del árbol");
                    } else {
                        System.out.println("El nodo " + valor + " no se encuentra en el árbol");
                    }
                    break;

                case 4:
                    System.out.println("Arbol en recorrido POSORODEN: ");
                    arbolBinario.posorden(arbolBinario.raiz);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Arbol en recorrido PREORODEN: ");
                    arbolBinario.preorden();
                    break;

                case 6:
                    System.out.println("Arbol en recorrido INORODEN: ");
                    arbolBinario.inorden(arbolBinario.raiz);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("El número de nodos en el árbol es: " + arbolBinario.getNumeroNodos());
                    break;

                case 8:
                int profundidad = arbolBinario.nivelProfundidad();
                System.out.println("La profundidad del árbol binario es: " + profundidad);
                break;
               
                 
                case 9:                
                Scanner scanner = new Scanner(System.in);

                System.out.print("Ingrese la expresion en notación prefija: ");
                String expresion = scanner.nextLine().replace(" ", "");

                arbolBinario.construirArbol(expresion);

                System.out.println("Recorrido Infijo:");
                arbolBinario.infijo(arbolBinario.raiz);
                System.out.println();

                System.out.println("Recorrido Posfijo:");
                arbolBinario.posfijo(arbolBinario.raiz);
                System.out.println();

                System.out.println("Recorrido Prefijo:");
                arbolBinario.prefijo(arbolBinario.raiz);
                System.out.println();
                

                    break;

                case 10:
                    System.out.println("El programa ha finalizado.");
                    break;

                default:
                    System.out.println("Ingrese una opción válida.");
                    break;
            }

            if (opcion != 10) {
                if (!Continuar(in)) {
                    opcion = 10; 
                }
            }
        } while (opcion != 10);
    }

    public static void mostrarMenu() {
        System.out.println("------------------------------");
        System.out.println("ARBOLES BINARIOS \nDANIEL ALEJANDRO RINCON VALENCIA \nElija una opción para continuar: ");
        System.out.println("------------------------------");
        System.out.println("1. Insertar nodo en arbol");
        System.out.println("2. Buscar nodo en arbol");
        System.out.println("3. Eliminar nodo del arbol");
        System.out.println("4. Mostrar arbol posorden");
        System.out.println("5. Mostrar arbol preorden");
        System.out.println("6. Mostrar arbol inorden");
        System.out.println("7. Numero nodos arbol");
        System.out.println("8. Nivel profundidad arbol");
        System.out.println("9. Notacion en prefijo, posfijo e infijo");
        System.out.println("10. Salir");
        System.out.println("------------------------------");
    }

    public static boolean Continuar(Scanner in) {
        int respuesta;
        while (true) {
            System.out.print("Desea seleccionar otra opción? (1 = Si, 2 = No): ");
            if (in.hasNextInt()) {
                respuesta = in.nextInt();
                in.nextLine();
                if (respuesta == 1 || respuesta == 2) {
                    break;
                }
            }
            System.out.print("Por favor, ingrese una opción válida");
            in.nextLine();
        }
        return respuesta == 1;
    }
    
    
}


