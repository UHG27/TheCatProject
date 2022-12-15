/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.thecatproject;

import java.io.IOException;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author herna
 */
public class TheCatProject {

    public static Stack<Gato> stackGatos = new Stack<>();
    
    public static void mostrarGato() {
        System.out.println("Último Gato añadido: ");
        Gato g = stackGatos.peek();
        System.out.println("ID: " + g.getId() + "\nURL: " + g.getUrl());
    }
    
    public static void isEmpty() {
        if ( !stackGatos.empty() )
            System.out.println("El stack no está vacío.");
        else
            System.out.println("El stack está vacío.");
    }
    
    public static void main(String[] args) throws IOException {
        stackGatos stackGatos = new stackGatos();

        int opcionMenu = -1;
        String[] opciones = {
            ".1 Ver Gatos",
            ".2 Mostrar gatos",
            ".3 Ver si la pila esta llena",
            ".4 Eliminar gato",
            ".5 Salir"
        };

        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos java", "Menu principal", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            for (int i = 0; i < opciones.length; i++) {
                if (opcion.equals(opciones[i])) {
                    opcionMenu = i;
                }
            }

            GatoService service = new GatoService();

            switch (opcionMenu) {
                case 0:{
                    System.out.println("Vas a ver a un gato");
                    service.getGatos();
                }
                    break;
                case 1:
                    System.out.println("Vas a salir del sistema");
            }

        } while (opcionMenu != 1);

    }
}
