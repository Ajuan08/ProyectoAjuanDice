package riosfernandez.antoniojuan.ajuandice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {
    Pane root = new Pane();
    @Override
    public void start(Stage stage) {

        //REACION DE LA PANTALLA
        int tamXPantalla = 500;
        int tamYPantalla = 500;
        var scene = new Scene( root, tamXPantalla, tamYPantalla);
        stage.setTitle("AJUAN DICE");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false); //SU FUNCION ES QUE NO SE PUEDA REDIMENSIONAR LA ESCENA.
        
        final int TAMAÑO_ARR = 200; //TAMAÑO FIJO DEL ARRAY ALEATORIO
       
       
        Tablero tablero = new Tablero();
        Logica logica = new Logica ((short)TAMAÑO_ARR,(short)4, tablero);
        
        //LLAMADAS A METODOS
            tablero.Screen(root,logica);
            tablero.Panel(root);
            
        logica.mostrarTableroConsola();
        logica.tableroJugador(TAMAÑO_ARR, 4);
        tablero.crearLabelPuntos(root);
        logica.mostrararrayJug();
        logica.mostrarNuevoColor();
        
    }
}
    