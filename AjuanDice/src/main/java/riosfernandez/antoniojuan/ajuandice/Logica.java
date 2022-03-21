package riosfernandez.antoniojuan.ajuandice;

// Crear objeto de la lógica del juego

import javafx.animation.Timeline;

public class Logica {
    
//VARIABLES//
    
Tablero tablero;

Timeline mostrarPatronTimeline;
Timeline controlDeColores;
    
short tamXArray;
int tamXJug;
int tamYJug;
short tamYArray;
String [] arrAleatorio;
String [] jugarray;
String VACIO = "0";
static final char JUGADOR1 = '1';

int turno = 0;
int posicionAMostrar = 0;
int contadorGenNumAle=1;

int puntos = 0;


//Método constructor
public Logica(short tamX, short tamY, Tablero tablero){
    this.tablero = tablero;
    tamXArray = tamX;
    tamYArray = tamY;
    arrAleatorio = new String [tamXArray]; //CREACION DEL ARRAY ALEATORIO
    for(int x=0;x<tamXArray;x++){ //BUCLE FOR UE EJECUTA TANTAS VECES COMO SEA EL TAMAÑO DE tamXArray
        arrAleatorio [x] = "0"; //EL ARRAY ESTA FORMADO DE CEROS
    }
    int numero = (int)(Math.random()*4+1);//CREACION DE UN NUMEO ALEATORIO ENTRE 1 Y 4
    String s=String.valueOf(numero);
    arrAleatorio [0] = s; //GUARDAMOS EL ARRAY ALEATORIO EN LA VRIABLE s
}
     
//METODO QUE MUESTRA EL TABLERO EN CONSOLA
    public void mostrarTableroConsola(){
        System.out.println("Array Aleatorio: ");
        for(int x=0;x<tamXArray;x++){ //BUCLE FOR UE EJECUTA TANTAS VECES COMO SEA EL TAMAÑO DE tamXArray
            System.out.print(arrAleatorio[x]); //PNTA EN CONSOLA EL ARRAY ALEATORIO
        }
        System.out.println();
        
    }
    //METODO DE LA CREACION DEL ARRAY DEL JUGADOR
    public void tableroJugador(int tamX, int tamY){
    
     tamXJug = tamX;
     tamYJug = tamY;
     jugarray = new String [tamXJug]; //REACION DEL ARRAY DEL JUGADOR
     for(int x=0;x<tamXJug;x++){//BUCLE FOR QUE EJECUTA TANTAS VECES COMO SEA EL TAMAÑO DE tamXJug
         jugarray [x] = VACIO; //EL ARRAY DEL JUGADOR LO PONE EN VACIO

     }
      
    }
    //METODO PARA MOSTRAR EL ARRAY DEL JUGADOR POR CONSOLA
    public void mostrararrayJug(){
        System.out.println("Array Jugador: ");
            for(int x=0;x<tamXJug;x++){ //BUCLE FOR QUE EJECUTA TANTAS VECES COMO SEA EL TAMAÑO DE tamXJug
                System.out.print(jugarray[x]); //PINTA EN PANTALLA EL ARRAY DEL JUGADOR
            }
            System.out.println();
        
    }
    //METODO PARA CAMBIAR EL ARRAY DEL JUGADOR
    public void cambioArrayJug(int pulsacion, int posicion){
        
        if (pulsacion == 1) //SI PULSACION ES = 1
        {
               jugarray [posicion] = "1"; //EN LA POSICION EN LA QUE SE ENCUENTRE EL ARRAY SE CAMBIA A 1
               
        }
        if (pulsacion == 2) //SI PULSACION ES = 2
        {
               jugarray [posicion] = "2"; //EN LA POSICION EN LA QUE SE ENCUENTRE EL ARRAY SE CAMBIA A 2
               
        }
        if (pulsacion == 3) //SI PULSACION ES = 3
        {
               jugarray [posicion] = "3"; //EN LA POSICION EN LA QUE SE ENCUENTRE EL ARRAY SE CAMBIA A 3
               
        }
        if (pulsacion == 4) //SI PULSACION ES = 4
        {
               jugarray [posicion] = "4"; //EN LA POSICION EN LA QUE SE ENCUENTRE EL ARRAY SE CAMBIA A 4
               
        }
    }
    //METODO PARA MOSTRAR UN NUEVO COLOR
    public void mostrarNuevoColor(){
        switch(arrAleatorio[posicionAMostrar]){
            case "1": //SI LE DAS AL CASO 1, SE TE ILUMINA EL TABLERO A ROJO
                tablero.iluminarRojo();
            break;
            case "2": //SI LE DAS AL CASO 2, SE TE ILUMINA EL TABLERO A AMARILLO
                tablero.iluminarAmarillo();
            break;
            case "3": //SI LE DAS AL CASO 3, SE TE ILUMINA EL TABLERO A AZUL
                tablero.iluminarAzul();
            break;
            case "4": //SI LE DAS AL CASO 4, SE TE ILUMINA EL TABLERO A VERDE
                tablero.iluminarVerde();
            break;
        } 
    }
    
    //METODO PARA GENERAR UN NUEVO NUMERO ALEATORIO
    public void generarNuevoNumAleatorio(){
        int numero = (int)(Math.random()*4+1);//GENERA UN NUMERO ALEATORIO ENTRE 1 Y 4
        String s=String.valueOf(numero);
        arrAleatorio [contadorGenNumAle] = s; //EL CONTADOR DEL ARRAY ALEATORIO LO GUARDA EN LA VARIABLE S
        contadorGenNumAle++; //INCREMENTA EL CONTADOR DE GENERAR NUMERO ALEATORIO, POR DEFECTO ESTA EN -1
    }
    
    //METODO DE COMPARACION ENTRE LOS ARRAYS
    public void compararArr(){
        boolean acierto = false; //SE CREA UNA VARIABLE BOOLEANA QUE POR DEFECTO LA PONEMOS EN FALSE
        for(int y=0; y <= posicionAMostrar; y++){ //BUCLE FOR QUE RECORRE LAS POSICIONES TANTAS VECES COMO HAYAN
                if(jugarray[y].equals(arrAleatorio[y])){ //SI EL ARRAY ALEATORIO ES IGUAL AL ARRAY DEL JUGADOR
                    acierto = true;
                }else{
                    acierto = false;
                }
        }
        if(acierto==true){
            System.out.println("has acertado");
            puntos++; //INCREMENTA LA VARIABLE PUNTOS
            tablero.cambiarLabelPuntos();//CAMBIA EL LABEL DE PUNTOS
            System.out.println(puntos);//PINTA POR PANTALLA LOS PUNTOS
        }else{
            System.out.println("has fallado");
            reinicio(); //LLAMAMOS AL METODO REINICIO
            tablero.cambiarLabelPuntos(); //CAMVIA EL LABEL DE PUNTOS
        }
    }
    
    //METODO PARA VACIAR EL ARRAY DEL JUGADOR
    public void vaciarArrJug(){
        for(int x=0;x<tamXJug;x++){ //BUCLE FOR QUE EJECUTA TANTAS VECES COMO SEA EL TAMAÑO DE tamXArray
            jugarray [x] = VACIO; //EL ARRAY DEL JUGADOR LO PONE A VACIO

        }
    }
    //METODO PARA VACIAR EL ARRAY ALEATORIO
    public void vaciarArrAlt(){
        System.out.println("SE VACIA ARRAY ALEATORIO"); //PINTA POR PANTALLA QUE SE VA A VACIAR EL ARRAY ALEATORIO
        for(int x=0;x<tamXJug;x++){ //BUCLE FOR QUE EJECUTA TANTAS VECES COMO SEA EL TAMAÑO DE tamXArray
            arrAleatorio [x] = VACIO; //EL ARRAY ALEATORIO LO PONE A VACIO

        }
    }
    //METODO PARA EL REINCIO DE LA PARTIDA
    public void reinicio(){
        System.out.println("Reinicio de partida"); //PINTA POR PANTALLA REINICIO
        vaciarArrJug(); //LLAMAMOS AL METODO DE VACIAR ARRAY JUGADOR
        vaciarArrAlt(); //LLAMAMOS AL METODO DE VACIAR ARRAY ALEATORIO
        puntos = 0; //LOS PUNTOS LOS VUELVES A PONER A 0
        posicionAMostrar = -1; //LA POSICION A MOSTRAR EMPIEZA EN -1
        contadorGenNumAle=0; //EL CONTADOR LO PONEMOS A 0
        generarNuevoNumAleatorio(); //GENERAMOS UN NUEVO NUMERO ALEATORIO
        
    }
    
}





//generar array hecho
//generar otro array del mismo tamaño pero en vez de aleatorio de 0 hecho
//asignar cada rectangulo a un numero hecho
// si el jugador le da al rectangulo1 cambia el array y se va almacenando 1000... al siguiente click se guarda 12000... hecho
// la secuencia del array se muestra con el flash al jugador hecho
// comparar el array aleatorio con el que esta escrito por el usuario y hacer derrota HECHO
//reinicio de la partida HECHO


//Tengo que hacer un bucle que recorra todo el array de 0, en la posicion 0 comparar con la posicion 0 del array de aleatorio
//si es correcto que pase a la siguiente sino pierde el jugador.
// hay que comparar el array justamente una vez dado el click.



