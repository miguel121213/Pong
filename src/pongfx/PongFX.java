/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongfx;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
 *
 * @author 1DAW08
 */
public class PongFX extends Application {
    // PANEL
    public static final double anchoPanel = 590;
    public static final double altoPanel = 360;
    //VELOCIDAD
    public static double velocidadPelotaX = 2;
    public static double velocidadPelotaY = 2;
    
    //Movimiento
    public static double movimientoYbarra = 200;
    public static double posBarraX = 15;

    //Rectangulo
    private int xRectangulo = 0;
    private int yRectangulo = 0;
    private int anchoRectangulo = 15;
    private int altoRectangulo = 60; 
    private int altoScene = 600;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        // Crear pantalla
        Scene scene = new Scene (root, altoScene, 400, Color.WHITE);
        primaryStage.setTitle("PongFx");
        primaryStage.setScene(scene);
        primaryStage.show();
        Circle pelota = new Circle(10, 30, 7, Color.BLACK); // Primer valor CentroY segundo centroX y tercero radio
        root.getChildren().add(pelota);
        pelota.setTranslateX(300);
        pelota.setTranslateY(200);
        //Crear barra
        Rectangle barra = new Rectangle(anchoRectangulo, altoRectangulo, Color.BLACK);
        root.getChildren().add(barra);
        barra.setTranslateX(1);
        barra.setTranslateY(200);
       //Mover Barra
        scene.setOnKeyPressed(e ->{
         if(e.getCode() == KeyCode.DOWN){
             movimientoYbarra  = movimientoYbarra + 20;
             barra.setTranslateY(movimientoYbarra);
             System.out.println(movimientoYbarra);
         }
         else if(e.getCode() == KeyCode.UP){
             movimientoYbarra = movimientoYbarra - 20;
             barra.setTranslateY(movimientoYbarra);
         }
        });
        
            // Shapes




        //Movimiento pelota
        AnimationTimer movimientoPelota = new AnimationTimer() {
             public void handle(long now) {
                 //Direccion de bola
                 if(pelota.getTranslateX() ==  posBarraX && pelota.getTranslateX() == (movimientoYbarra - 60)|| pelota.getTranslateX() > anchoPanel) {
                    velocidadPelotaX *= -1;
                }  
                 pelota.setTranslateX(pelota.getTranslateX() + velocidadPelotaX);
                 
                 if(pelota.getTranslateY() < -20 || pelota.getTranslateY() > altoPanel) {
                    velocidadPelotaY *= -1;
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
