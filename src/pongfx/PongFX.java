/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongfx;


import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
/**
 *
 * @author 1DAW08
 */
public class PongFX extends Application {
    // PANEL
    final int sceneTamañoX = 600;
    final int sceneTamañoY = 400;
    //VELOCIDAD
    public static double velocidadPelotaX = 2;
    public static double velocidadPelotaY = 2;
    int velocidadBarra = 0;
    int velocidadBarra2= 0;
    
    //Movimiento
    public static double movimientoYbarra = 200;
    public static double movimientoYbarraY = 0;
    public static double movimientoYbarra2 = 200;
    public static double posBarraX = 15;
    

    
    
    //Rectangulo
    final int anchoRectangulo = 7;
    final int anchoRectangulo2 = 7;
    final int altoRectangulo = 50;
    final int altoRectangulo2 = 50;
    int barraPosY = (sceneTamañoY - altoRectangulo) / 2;
    int barraPosY2 = (sceneTamañoY - altoRectangulo2) / 2;
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        // Crear pantalla
        Scene scene = new Scene (root, sceneTamañoX, sceneTamañoY, Color.WHITE);
        primaryStage.setTitle("PongFx");
        primaryStage.setScene(scene);
        primaryStage.show();
        Circle pelota = new Circle(10, 30, 7, Color.BLACK); // Primer valor CentroY segundo centroX y tercero radio
        root.getChildren().add(pelota);
        pelota.setTranslateX(300);
        pelota.setTranslateY(200);
        //Crear barra
        Rectangle barra = new Rectangle(anchoRectangulo, altoRectangulo, Color.BLACK);
        Rectangle barra2 = new Rectangle (anchoRectangulo2, altoRectangulo2, Color.BLACK);
        root.getChildren().add(barra2);
        root.getChildren().add(barra);
        barra.setTranslateX(50);
        barra.setTranslateY(0);
        barra2.setTranslateX(585);
        barra2.setTranslateY(200);
       //Mover Barra
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case W:
                    velocidadBarra2 = -6;
                    break;
                case S:
                    velocidadBarra2 = 6;
                    break;
                case UP:
                    velocidadBarra= -6;
                    break;
                case DOWN:
                    velocidadBarra= 6;
                    break;
            }
           
        });
        // fix el bug de dos jugadores de movimiento
        scene.setOnKeyReleased((KeyEvent event) -> {
            
                if(event.getCode() == KeyCode.UP ||event.getCode() == KeyCode.DOWN ){
                    velocidadBarra = 0;
                }
                if(event.getCode() == KeyCode.W ||event.getCode() == KeyCode.S ){
                    velocidadBarra2 = 0;
                }
                     
            
        });
        
       
       barraPosY += velocidadBarra;
       if (barraPosY < 0) {
           barraPosY = 0;
       }  else {
           if (barraPosY > sceneTamañoY - altoRectangulo){
               barraPosY = sceneTamañoY - altoRectangulo;
           }
       }
       barra.setY(barraPosY);
       
       // barra2
       
       barraPosY2 += velocidadBarra2;
       if (barraPosY2 < 0) {
           barraPosY2 = 0;
       }  else {
           if (barraPosY2 > sceneTamañoY - altoRectangulo2){
               barraPosY2 = sceneTamañoY - altoRectangulo2;
           }
       }
       barra2.setY(barraPosY2);
       /* scene.setOnKeyPressed(e ->{
         if(e.getCode() == KeyCode.S){
             movimientoYbarra  = movimientoYbarra + 30;
             barra.setTranslateY(movimientoYbarra);
         }
         else if(e.getCode() == KeyCode.W){
             movimientoYbarra = movimientoYbarra - 30;
             barra.setTranslateY(movimientoYbarra);
         }
        if(e.getCode() == KeyCode.DOWN){
             movimientoYbarra2  = movimientoYbarra2 + 30;
             barra2.setTranslateY(movimientoYbarra2);
         }
         else if(e.getCode() == KeyCode.UP){
             movimientoYbarra2 = movimientoYbarra2 - 30;
             barra2.setTranslateY(movimientoYbarra2);
         }
        });
       */
        
        //BOTON RESTART
        Button botonRestart = new Button("Restart");
        botonRestart.setLayoutX(200);
        botonRestart.setLayoutY(375);
        root.getChildren().add(botonRestart);
        botonRestart.setDefaultButton(true);
        botonRestart.setContentDisplay(ContentDisplay.TOP);
        botonRestart.setOnAction(event -> { 
            pelota.setTranslateX(300);
            pelota.setTranslateY(200);
            velocidadPelotaY = 2;
            velocidadPelotaX = 2;
        });
        
        //Movimiento pelota
        AnimationTimer movimientoPelota = new AnimationTimer() {       
             public void handle(long now) {
                barraPosY += velocidadBarra;
                barra.setY(barraPosY);
                //Barra 2
                barraPosY2 += velocidadBarra2;
                barra2.setY(barraPosY2);
                Shape colisionPelotaBarra = Shape.intersect(pelota, barra);
                boolean colisionVacia = colisionPelotaBarra.getBoundsInLocal().isEmpty();
                Shape colisionPelotaBarra2 = Shape.intersect(pelota, barra2);
                boolean colisionVacia2 = colisionPelotaBarra2.getBoundsInLocal().isEmpty();
                 //Direccion de bola
                 if  (colisionVacia2 == false  || colisionVacia == false ) {
                    velocidadPelotaX *= -1 - 0.05;
                    System.out.println("Velocidad X: " + velocidadPelotaX);
                }  
                 pelota.setTranslateX(pelota.getTranslateX() + velocidadPelotaX);
                 
                 if(pelota.getTranslateY() < -20 || pelota.getTranslateY() > 370) {
                    velocidadPelotaY  *= -1 - 0.05;
                    System.out.println("Velocidad Y: " + velocidadPelotaY);
                }


                
                 pelota.setTranslateY(pelota.getTranslateY() + velocidadPelotaY);
              
             }
        };
        
      
             movimientoPelota.start();
        }


    
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
