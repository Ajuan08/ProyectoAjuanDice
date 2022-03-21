package riosfernandez.antoniojuan.ajuandice;

/**
 *
 * @author JUAN
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


public class Tablero extends Pane {
    
    //VARIABLES//

    Timeline controldelFlash;
    
    Label labelPuntos;
    
    Logica logica;
    ImageView fondo;
    ImageView botonCheck;
    int pulsacion;
    int posicion = -1;
    boolean cambioColor = false;
    int botonCheckPosX = 225;
    int botonCheckPosY = 245;
    
    Rectangle recrojo = new Rectangle(0, 50, 200,200);
    Rectangle recamarillo = new Rectangle(300, 50, 200,200);
    Rectangle recverde = new Rectangle(300, 300, 200,200);
    Rectangle recazul = new Rectangle(0, 300, 200,200);
    
    
    
    public void Screen(Pane root, Logica logica){
        this.logica = logica;
        Image fondoImg = new Image(getClass().getResourceAsStream("/images/fondo1.jpg")); // CARGA LA IMAGEN DE FONDO
        Image checkImg = new Image(getClass().getResourceAsStream("/images/check.png")); // CARGA LA IMAGEN DEL BOTON
        fondo = new ImageView(fondoImg); // CREA EL OBJETO fondoImg
        botonCheck = new ImageView(checkImg); // CREA EL OBJETO checkImg
        root.getChildren().add(fondo); //AÑADIMOS AL ROOT EL FONDO
        root.getChildren().add(botonCheck); //AÑADIMOS AL ROOT EL BOTON DE COMPROBACION
        botonCheck.setLayoutX(botonCheckPosX); //POSICION X DEL BOTON EN LA PANTALLA
        botonCheck.setLayoutY(botonCheckPosY); //POSICION Y DEL BOTON EN LA PANTALLA
        controlDeFlash(); //LLAMAMOS AL METODO DE CONTROL DEL FLASH
       
    }
    //METODO PARA CREAR LABEL DE PUNTOS
    public void crearLabelPuntos(Pane root){
        labelPuntos = new Label("PUNTOS: "+logica.puntos);
        Font font = Font.font("Arial Black", FontWeight.BOLD, FontPosture.REGULAR, 25);
        labelPuntos.setFont(font); //ASIGNAMOS UNA FUENTE AL LABEL
        labelPuntos.setTextFill(Color.WHITE); //COLOR DEL LABEL
        labelPuntos.setTranslateX(175); //POSICION X DEL LABEL
        labelPuntos.setTranslateY(3); //POSICION Y DEL LABEL
        root.getChildren().add(labelPuntos); //AÑADIMOS EL LABEL AL ROOT
    }
    //METODO PARA EL CAMBIO DE PUNTOS DE LA PARTIDA
    public void cambiarLabelPuntos(){
        labelPuntos.setText("");
        labelPuntos.setText("PUNTOS: "+logica.puntos);
    }
    
     public void Panel(Pane root){ 
          // Colorear cada elemento
            recrojo.setFill(Color.RED); //LE DA EL COLOR ROJO AL RECROJO
            recamarillo.setFill(Color.YELLOW); //LE DA EL COLOR AMARILLO AL RECAMARILLO
            recverde.setFill(Color.GREEN); //LE DA EL COLOR VERDE AL RECVERDE
            recazul.setFill(Color.BLUE); //LE DA EL COLOR AZUL AL RECAZUL
    
          // Agrupar todos los elementos
            Group grouptablero = new Group(); //CREACION DE UN NUEVO GRUPO
            grouptablero.getChildren().add(recrojo); //AÑADIMOS RECROJO AL GRUPO CREADO ANTERIORMENTE
            grouptablero.getChildren().add(recamarillo);  //AÑADIMOS RECAMARILLO AL GRUPO CREADO ANTERIORMENTE
            grouptablero.getChildren().add(recverde);  //AÑADIMOS RECVERDE AL GRUPO CREADO ANTERIORMENTE
            grouptablero.getChildren().add(recazul);  //AÑADIMOS RECAZUL AL GRUPO CREADO ANTERIORMENTE
       
         // Posicionar el grupo en la pantalla
            grouptablero.setLayoutX(0); //POSICION X DEL GRUPO
            grouptablero.setLayoutY(0); //POSICION Y DEL GRUPO
//            
          //Añadir el grupo al contenedor principal
             root.getChildren().add(grouptablero); //AÑADIMOS EL GRUPO AL ROOT
            
        //BOTON DE COMPROBACION
        botonCheck.setOnMousePressed((MouseEvent mouseEvent) -> { //EVENTO DEL CLICK DEL RATON
            logica.generarNuevoNumAleatorio(); //LLAMA AL METODO GENERAR NUMERO ALEATORIO QUE SE ENCUENTRA EN LOGICA
            logica.compararArr(); //LLAMA AL METODO COMPARAR ARRAY QUE SE ENCUENTRA EN LOGICA
            logica.mostrarTableroConsola(); //LLAMA AL METODO MOSTRAR TABLERO CONSOLA QUE SE ENCUENTRA EN LOGICA
            logica.vaciarArrJug(); //LLAMA AL METODO VACIAR ARRAY JUGADOR QUE SE ENCUENTRA EN LOGICA
            logica.posicionAMostrar++; //INCREMENTA EN 1 LA VARIABLE POSICION A MOSTRAR DE LOGICA
            posicion=-1;
            logica.mostrarNuevoColor();
            
        });
             
        recrojo.setOnMousePressed((MouseEvent mouseEvent) -> { //EVENTO DEL CLICK DEL RATON
            iluminarRojo(); //LLAMA A METODO DE ILUMINAR EL COLOR ROJO
            pulsacion = 1;
            if(posicion<logica.tamXJug-1){ //SI LA POSICION ES MENOR QUE EL TAMX DEL ARRAY DEL JUGADOR
                posicion++; //INREMENTA LA POSICION EN 1
                System.out.println("la posicon es: "+posicion);
                System.out.println("Has pulsado el cuadrado rojo");
                logica.cambioArrayJug(pulsacion, posicion); //LLAMA AL METODO DE CAMBIO DEL ARRAY JUGADOR Y COGE LOS PARAMETROS DE PULSACION Y POSICION
                logica.mostrarTableroConsola();//LLAMA AL METODO DE MOSTRAR EL TABLERO POR LA CONSOLA QUE SE ENCUENTRA EN LOGICA
                logica.mostrararrayJug(); //LLAMA A METODO DE MOSTRAR EL ARRAY DEL JUGADOR POR CONSOLA QUE SE ENCUENTRA EN LOGICA
            }else{
                System.out.println("Has llegado al final del array");
            }
            
        });
        recamarillo.setOnMousePressed((MouseEvent mouseEvent) -> { //EVENTO DEL CLICK DEL RATON
            iluminarAmarillo(); //LLAMA A METODO DE ILUMINAR EL COLOR AMARILLO
            pulsacion = 2;
            if(posicion<logica.tamXJug-1){ //SI LA POSICION ES MENOR QUE EL TAMX DEL ARRAY DEL JUGADOR
                posicion++; //INREMENTA LA POSICION EN 1
                System.out.println("la posicon es: "+posicion);
                System.out.println("Has pulsado el cuadrado amarillo");
                logica.cambioArrayJug(pulsacion, posicion); //LLAMA AL METODO DE CAMBIO DEL ARRAY JUGADOR Y COGE LOS PARAMETROS DE PULSACION Y POSICION
                logica.mostrarTableroConsola();//LLAMA AL METODO DE MOSTRAR EL TABLERO POR LA CONSOLA QUE SE ENCUENTRA EN LOGICA
                logica.mostrararrayJug(); //LLAMA A METODO DE MOSTRAR EL ARRAY DEL JUGADOR POR CONSOLA QUE SE ENCUENTRA EN LOGICA
            }else{
                System.out.println("Has llegado al final del array");
            }
            
        });
        recazul.setOnMousePressed((MouseEvent mouseEvent) -> { //EVENTO DEL CLICK DEL RATON
            iluminarAzul(); //LLAMA A METODO DE ILUMINAR EL COLOR AZUL
            pulsacion = 3;
            if(posicion<logica.tamXJug-1){ //SI LA POSICION ES MENOR QUE EL TAMX DEL ARRAY DEL JUGADOR
                posicion++;  //INREMENTA LA POSICION EN 1
                System.out.println("la posicon es: "+posicion);
                System.out.println("Has pulsado el cuadrado azul");
                logica.cambioArrayJug(pulsacion, posicion); //LLAMA AL METODO DE CAMBIO DEL ARRAY JUGADOR Y COGE LOS PARAMETROS DE PULSACION Y POSICION
                logica.mostrarTableroConsola(); //LLAMA AL METODO DE MOSTRAR EL TABLERO POR LA CONSOLA QUE SE ENCUENTRA EN LOGICA
                logica.mostrararrayJug(); //LLAMA A METODO DE MOSTRAR EL ARRAY DEL JUGADOR POR CONSOLA QUE SE ENCUENTRA EN LOGICA
            }else{
                System.out.println("Has llegado al final del array");
            }

        });
        recverde.setOnMousePressed((MouseEvent mouseEvent) -> { //EVENTO DEL CLICK DEL RATON
            iluminarVerde(); //LLAMA A METODO DE ILUMINAR EL COLOR VERDE
            pulsacion = 4;
            if(posicion<logica.tamXJug-1){  //SI LA POSICION ES MENOR QUE EL TAMX DEL ARRAY DEL JUGADOR
                posicion++; //INREMENTA LA POSICION EN 1
                System.out.println("la posicon es: "+posicion);
                System.out.println("Has pulsado el cuadrado verde");
                logica.cambioArrayJug(pulsacion, posicion); //LLAMA AL METODO DE CAMBIO DEL ARRAY JUGADOR Y COGE LOS PARAMETROS DE PULSACION Y POSICION
                logica.mostrarTableroConsola(); //LLAMA AL METODO DE MOSTRAR EL TABLERO POR LA CONSOLA QUE SE ENCUENTRA EN LOGICA
                logica.mostrararrayJug(); //LLAMA A METODO DE MOSTRAR EL ARRAY DEL JUGADOR POR CONSOLA QUE SE ENCUENTRA EN LOGICA
            }else{
                System.out.println("Has llegado al final del array");
            }

        });
    }
    //CREACION DEL METODO DEL CONTROL DEL FLASH
     public void controlDeFlash(){
         
             controldelFlash = new Timeline(
                new KeyFrame(Duration.seconds(1), (ActionEvent ae) -> {
                    recrojo.setFill(Color.RED);
                    recamarillo.setFill(Color.YELLOW);
                    recverde.setFill(Color.GREEN);
                    recazul.setFill(Color.BLUE); 
                })
        );
        controldelFlash.setCycleCount(Timeline.INDEFINITE); // HACE QUE SE EJECUTE INDEFINIDAMENTE
        controldelFlash.play(); // EJECUTA EL SCROLL DEL FONDO
         
     }
     //CREACION DEL METODO PARA ILUMINAR EL COLOR ROJO
    public void iluminarRojo(){
        recrojo.setFill(Color.rgb(255, 115, 115));
    }
    //CREACION DEL METODO PARA ILUMINAR EL COLOR AMARILLO
    public void iluminarAmarillo(){
        recamarillo.setFill(Color.rgb(214, 246, 89));
    }
    //CREACION DEL METODO PARA ILUMINAR EL COLOR VERDE
    public void iluminarVerde(){
        recverde.setFill(Color.rgb(91, 233, 104));
    }
    //CREACION DEL METODO PARA ILUMINAR EL COLOR AZUL
    public void iluminarAzul(){
        recazul.setFill(Color.rgb(110, 174, 255));
    }
    
}

   