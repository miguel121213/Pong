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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
 *
 * @author 1DAW08
 */
public class PongFX extends Application {
    // PANEL
    public static final double anchoPanel = 590;
    public static final double altoPanel = 300;
    //VELOCIDAD
    public static double velocidadPelota = 1;
    
    //Rectangulo
    private int xRectangulo = 0;
    private int yRectangulo = 0;
    private int anchoRectangulo = 15;
    private int altoRectangulo = 60; 
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        // Crear pantalla
        Scene scene = new Scene (root, 600, 400, Color.BLACK);
        primaryStage.setTitle("PongFx");
        primaryStage.setScene(scene);
        primaryStage.show();
        Circle pelota = new Circle(10, 30, 7, Color.WHITE); // Primer valor CentroY segundo centroX y tercero radio
        root.getChildren().add(pelota);
        pelota.setTranslateX(300);
        pelota.setTranslateY(200);
        //Crear barra
        Rectangle barra = new Rectangle(anchoRectangulo, altoRectangulo, Color.WHITE);
        root.getChildren().add(barra);
        barra.setTranslateX(1);
        barra.setTranslateY(200);
       //Mover rectanguloç

        //Movimiento pelota
        AnimationTimer movimientoPelota = new AnimationTimer() {
             public void handle(long now) {
                 //Direccion de bola
                 if(pelota.getTranslateX() < 0 || pelota.getTranslateX() > anchoPanel) {
                    velocidadPelota *= -1;
                }  
                 pelota.setTranslateX(pelota.getTranslateX() + velocidadPelota);
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
