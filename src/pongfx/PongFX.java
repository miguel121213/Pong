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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    final int ANCHORECTANGULO = 7;
    final int ANCHORECTANGULO2 = 7;
    final int ALTORECTANGULO = 50;
    final int ALTORECTANGULO2 = 50;
    final int TEXT_SIZE = 20;
    int barraPosY = (sceneTamañoY - ALTORECTANGULO) / 2;
    int barraPosY2 = (sceneTamañoY - ALTORECTANGULO2) / 2;
    int Score;
    int ScoreJ2;
    double incrementoVelocidadPelota;
    
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
        //Crear red
        for (int i= 0; i<sceneTamañoY; i+=30){
            Line line = new Line (sceneTamañoX/2, i ,sceneTamañoX/2, i+10);
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(4);
            root.getChildren().add(line);
        }
        //PUNTUACIONES
            //LAYOUT PRINCIPAL
        HBox  paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(sceneTamañoX);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(100);
        root.getChildren().add(paneScores);
            //LAYOUT PARA PUNTUACION
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScore);
            //lAYOUT PARA PUNTUACION JUGADOR 2
        HBox paneCurrentScoreJ2 = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScoreJ2);
            //TEXTO ETIQUETA PUNTUACION
        Text textTitleScore = new Text("Score:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.BLACK);
            //TEXTO PUNTUACION
        Text textScore = new  Text (" 0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.BLACK);
            //TEXTO ETIQUETA PUNTUACION J2
        Text textTitleScoreJ2 = new Text("Score:");
        textTitleScoreJ2.setFont(Font.font(TEXT_SIZE));
        textTitleScoreJ2.setFill(Color.BLACK);
            //TEXTO PUNTUACION J2
        Text textScoreJ2 = new  Text (" 0");
        textScoreJ2.setFont(Font.font(TEXT_SIZE));
        textScoreJ2.setFill(Color.BLACK);
            //AÑADIRLOS AL ROOT
        paneCurrentScore.getChildren().add(textTitleScore);
        paneCurrentScore.getChildren().add(textScore);
        paneCurrentScoreJ2.getChildren().add(textTitleScoreJ2);
        paneCurrentScoreJ2.getChildren().add(textScoreJ2);
        //Crear barra
        Rectangle barra = new Rectangle(ANCHORECTANGULO, ALTORECTANGULO, Color.BLACK);
        Rectangle barra2 = new Rectangle (ANCHORECTANGULO2, ALTORECTANGULO2, Color.BLACK);
        root.getChildren().add(barra2);
        root.getChildren().add(barra);
        barra.setTranslateX(50);
        barra.setTranslateY(0);
        barra2.setTranslateX(550);
        barra2.setTranslateY(0);
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
        /*
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
        textScore.setText(String.valueOf(" 0"));
        textScoreJ2.setText(String.valueOf(" 0"));
        });
        */
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
                 //Segunda barra
                 if  (colisionVacia2 == false ) {
                    incrementoVelocidadPelota = velocidadPelotaX - 1.5;
                    velocidadPelotaX = -2 - incrementoVelocidadPelota;
                    System.out.println("Velocidad X: " + velocidadPelotaX);
                    
                }  
                //Primera barra
                 if (colisionVacia == false) {
                    velocidadPelotaX = 2 + incrementoVelocidadPelota;
                 }
                 pelota.setTranslateX(pelota.getTranslateX() + velocidadPelotaX);
                 
                 if( pelota.getTranslateY() > 370) {
                    velocidadPelotaY  = -2 - incrementoVelocidadPelota;
                    System.out.println("Velocidad Y: " + velocidadPelotaY);
                }
                 if (pelota.getTranslateY() < -20 ){
                     velocidadPelotaY = 2 + incrementoVelocidadPelota;
                 }
                 if (pelota.getTranslateX()> 600){
                    Score++;
                    textScore.setText(String.valueOf(Score));
                    pelota.setTranslateX(300);
                    pelota.setTranslateY(200);
                 }
                  if (pelota.getTranslateX()< 0){
                    ScoreJ2++;
                    textScoreJ2.setText(String.valueOf(ScoreJ2));
                    pelota.setTranslateX(300);
                    pelota.setTranslateY(200);
                 }


                
                 pelota.setTranslateY(pelota.getTranslateY() + velocidadPelotaY);
                 //Colider con techo y suelo de la barra
                  barraPosY += velocidadBarra;
                  if (barraPosY < 0) {
                      barraPosY = 0;
                  }  else {
                      if (barraPosY > sceneTamañoY - ALTORECTANGULO){
                          barraPosY = sceneTamañoY - ALTORECTANGULO;
                      }
                  }
                  barra.setY(barraPosY);

                  // barra2

                  barraPosY2 += velocidadBarra2;
                  if (barraPosY2 < 0) {
                      barraPosY2 = 0;
                  }  else {
                      if (barraPosY2 > sceneTamañoY - ALTORECTANGULO2){
                          barraPosY2 = sceneTamañoY - ALTORECTANGULO2;
                      }
                  }
                  barra2.setY(barraPosY2);
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
