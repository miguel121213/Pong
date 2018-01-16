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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public static double movimientoYbarra2 = 200;
    public static double posBarraX = 15;
    

    
    
    //Rectangulo
    private int xRectangulo = 0;
    private int yRectangulo = 0;
    private int anchoRectangulo = 15;
    private int anchoRectangulo2 = 15;
    private int altoRectangulo2 = 60;
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
        Rectangle barra2 = new Rectangle (anchoRectangulo2, altoRectangulo2, Color.BLACK);
        root.getChildren().add(barra2);
        root.getChildren().add(barra);
        barra.setTranslateX(1);
        barra.setTranslateY(200);
        barra2.setTranslateX(585);
        barra2.setTranslateY(200);
       //Mover Barra
        scene.setOnKeyPressed(e ->{
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
        
        //Game Over
               /*
                Group rootGameOver = new Group();
                Scene sceneGameOver = new Scene( root );
                primaryStage.setScene( sceneGameOver );

                Canvas canvas = new Canvas( 400, 200 );
                rootGameOver.getChildren().add( canvas );

                GraphicsContext gc = canvas.getGraphicsContext2D();

                gc.setFill( Color.RED );
                gc.setStroke( Color.BLACK );
                gc.setLineWidth(2);
                Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
                gc.setFont( theFont );
                gc.fillText( "Gana el Jugador 1", 60, 50 );
                gc.strokeText( "Gana el Jugador 1", 60, 50 );

                   */
               
            
                if (pelota.getTranslateY()< 600){
                primaryStage.show();
                }
            
        
               

        

        //Movimiento pelota
        AnimationTimer movimientoPelota = new AnimationTimer() {

        
             public void handle(long now) {
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
                 
                 if(pelota.getTranslateY() < -20 || pelota.getTranslateY() > altoPanel) {
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
