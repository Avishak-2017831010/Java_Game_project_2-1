package project.template;

//Chess  application

//imports
import static java.awt.Color.black;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.INDEFINITE;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.SADDLEBROWN;
import static javafx.scene.paint.Color.SIENNA;
import static javafx.scene.paint.Color.SILVER;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;


public class maingame extends Application {
        
	private Pane mainlayout;                             //layout which allows items to be positionitioned on top of each other
	private mainControl cc_custom;
        private Pane watermark;
        private Pane main_pane;
        private Pane intro_pane;
        private Pane BackWater;
        
        Group group;
        MediaPlayer player;
        Media media;
        Group root=new Group();
        ImageView view1,firstView,spaceview;
        Button level_button;
        double radius;
        Button play;
        Scene scene;
        MediaPlayer videoplay;
        Button spacebutton;
        Image space;
        MediaPlayer introplay;
        
                                                               //control which has a board and detects mouse and keyboard events
        
       
	@Override
	public void init() {
               
                
                Image back=new Image("file:///C:/Users/Avi/Desktop/background_watermark.jpg");
                Image img=new Image("file:///C:/Users/Avi/Desktop/test.jpg");
                
                Image intro=new Image("file:///C:/Users/Avi/Desktop/opening.jpg");
                Image intromark=new Image("file:///C:/Users/Avi/Desktop/intromark.jpg");
                
                
                
                
                
                
                 
                
                ImageView spaceview=new ImageView(space);
                ImageView backview=new ImageView(back);
                ImageView introview=new ImageView(intro);
                ImageView introMarkView=new ImageView(intromark);
                
               
                
                
               
                backview.setFitHeight(1000+10+50);
                backview.setFitWidth(1800+110+50);
                introview.setFitHeight(1060);
                introview.setFitWidth(1960);
                introMarkView.setFitHeight(600);
                introMarkView.setFitWidth(1960);
                introMarkView.setOpacity(0.1);
                backview.setOpacity(0.3);
                ImageView view=new ImageView(img);
                view.setOpacity(0.1);
                double radius=2.0;
		mainlayout = new StackPane(); 
               
                
                
                watermark=new StackPane();
                intro_pane=new StackPane();
                BackWater=new StackPane();
		cc_custom = new mainControl();
                
		mainlayout.getChildren().add(cc_custom);
                //mainlayout.getChildren().add(spacebutton);
                watermark.getChildren().add(view);
                BackWater.getChildren().add(backview);
                BackWater.setMouseTransparent(true);
                watermark.setPickOnBounds(false);
                watermark.setMouseTransparent(true);
                intro_pane.getChildren().add(introview);
                intro_pane.getChildren().add(introMarkView);        
                         
	}
	

	public void start(Stage primaryStage) throws Exception {
           
                
		primaryStage.setTitle("Chess game");
                Scene scene =new Scene(root,1900,1000);
               
                 
                Button play=new Button("",new ImageView("file:///C:/Users/Avi/Desktop/play.png")); 
                play.setPadding(Insets.EMPTY);
                Button spacebutton=new Button("",new ImageView("file:///C:/Users/Avi/Desktop/spacebuttonview.png"));
                spacebutton.setPadding(Insets.EMPTY);
                Button horrorbutton=new Button("",new ImageView("file:///C:/Users/Avi/Desktop/horrorbuttonimage.png"));
                horrorbutton.setPadding(Insets.EMPTY);
                play.setTranslateX(600);
                play.setTranslateY(200+100);
                spacebutton.setTranslateX(-500-300);
                spacebutton.setTranslateY(-50-20);
                horrorbutton.setTranslateX(-800);
                horrorbutton.setLayoutY(-100-20);
                Button midnightbutton=new Button("",new ImageView("file:///C:/Users/Avi/Desktop/midnightbutton.jpg"));
                Button backbutton=new Button("",new ImageView("file:///C:/Users/Avi/Desktop/backbutton.png"));
                backbutton.setPadding(Insets.EMPTY);
                backbutton.setTranslateX(-800);
                backbutton.setTranslateY(-480+5);
                midnightbutton.setTranslateX(-800);
                midnightbutton.setTranslateY(-150+10);
                midnightbutton.setPadding(Insets.EMPTY);
                spacebutton.setMaxSize(200,59);
                spacebutton.setMinSize(200,59);
                
                
                
                Image first=new Image("file:///C:/Users/Avi/Desktop/intro_image.jpg");
                ImageView firstView=new ImageView(first);
                firstView.setFitHeight(1000);
                firstView.setFitWidth(1900);
                
                Image img1=new Image("file:///C:/Users/Avi/Desktop/background.jpg");
                 
                 
                Image tickimage=new Image("file:///C:/Users/Avi/Desktop/tickimage.gif");
                ImageView tickview=new ImageView(tickimage);
                tickview.setLayoutX(1000+500+200);
                tickview.setLayoutY(500+10);
                tickview.setFitHeight(150);
                tickview.setFitWidth(150);
                tickview.setOpacity(0.5);
                
                
                
                 Scene intro=new Scene(intro_pane,1900,1000);
                 Image space=new Image("file:///C:/Users/Avi/Desktop/Space.gif");
                 Image horror=new Image("file:///C:/Users/Avi/Desktop/horror.gif");
                 Image midnight=new Image("file:///C:/Users/Avi/Desktop/midnightsky.gif");
                 
                 ImageView view1=new ImageView(img1);
                 
                 
                 
                    
                
                intro_pane.getChildren().add(play);
               
                view1.setFitHeight(1000+10);
                view1.setFitWidth(1900+10);
                
                BackWater.setLayoutX(0);
                BackWater.setLayoutY(0);
                
                
                ImageView spaceview=new ImageView(space);
                
                
                Media introsound = new Media("file:///C:/Users/Avi/Desktop/intro_sound.wav");      
                MediaPlayer introplay=new MediaPlayer(introsound);
                introplay.setVolume(2);
                introplay.setAutoPlay(true);
                introplay.setCycleCount(INDEFINITE);
                
                Media ticksound=new Media("file:///C:/Users/Avi/Desktop/tick.wav");
                MediaPlayer tickplay=new MediaPlayer(ticksound);
                tickplay.setVolume(0.5);
                
                Media horroraudio=new Media("file:///C:/Users/Avi/Desktop/horroraudio.wav");
                MediaPlayer horrorplay=new MediaPlayer(horroraudio);
                
                Media spaceaudio=new Media("file:///C:/Users/Avi/Desktop/spacesound.wav");
                MediaPlayer spaceplay=new MediaPlayer(spaceaudio);
                
                MediaPlayer mainplay=new MediaPlayer(new Media("file:///C:/Users/Avi/Desktop/mainaudio.wav"));
                MediaPlayer midnightplay1=new MediaPlayer(new Media("file:///C:/Users/Avi/Desktop/midnightsound1.wav"));
                MediaPlayer click=new MediaPlayer(new Media("file:///C:/Users/Avi/Desktop/click.wav"));
                MediaPlayer midnightplay2=new MediaPlayer(new Media("file:///C:/Users/Avi/Desktop/midnightsound2.wav"));
                mainplay.setVolume(0.3);
                 play.setOnAction(e->{
                     click.play();
                     primaryStage.setScene(scene);
                     introplay.stop();
                        mainplay.play();
                        tickplay.setAutoPlay(true);
                        tickplay.setCycleCount(INDEFINITE);
                        mainplay.setCycleCount(INDEFINITE);
                         });
                backbutton.setOnAction(e->{
                    click.play();
                    primaryStage.setScene(scene);
                     introplay.stop();
                        mainplay.play();
                        tickplay.setAutoPlay(true);
                        tickplay.setCycleCount(INDEFINITE);
                        mainplay.setCycleCount(INDEFINITE);
                        view1.setImage(img1);
                        horrorplay.stop();
                        spaceplay.stop();
                        midnightplay1.stop();
                        midnightplay2.stop();
                         });
                
                
                 horrorbutton.setOnAction(e->{
                    click.play();
                    view1.setImage(horror);
                    horrorplay.play();
                    //horrorplay.setAutoPlay(true);
                    horrorplay.setCycleCount(INDEFINITE);
                    spaceplay.stop();
                    mainplay.stop();
                    
                    
                
        });
                spacebutton.setOnAction(e->{
                    click.play();
                    view1.setImage(space);
                    spaceplay.play();
                    //spaceplay.setAutoPlay(true);
                    spaceplay.setCycleCount(INDEFINITE);
                    horrorplay.stop();
                    mainplay.stop();
                });
                
                midnightbutton.setOnAction(e->{
                    click.play();
                    view1.setImage(midnight);
                    midnightplay1.play();
                    midnightplay2.play();
                    horrorplay.stop();
                    spaceplay.stop();
                    mainplay.stop();
                    midnightplay1.setCycleCount(INDEFINITE);
                    midnightplay2.setCycleCount(INDEFINITE);
                    
                });
                
                
                mainlayout.getChildren().add(spacebutton);
                mainlayout.getChildren().add(horrorbutton);
                mainlayout.getChildren().add(backbutton);
                mainlayout.getChildren().add(midnightbutton);
                root.getChildren().add(BackWater);
                root.getChildren().add(view1);
                root.getChildren().add(tickview);
                
                
                StackPane main_pane=new StackPane(mainlayout,watermark);
                
                main_pane.setPrefSize(950, 900);
                main_pane.setLayoutX(1950/2-500+80);
                main_pane.setLayoutY(50-5);
                
               
                
                
                main_pane.setBorder(new Border(new BorderStroke(SADDLEBROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(20))));
                root.getChildren().add(main_pane);
               
              
		primaryStage.setScene(intro);
		primaryStage.setMinWidth(300);
		primaryStage.setMinHeight(300);
                primaryStage.setResizable(false);
                
		primaryStage.show();
	}

	// overridden stop method
	@Override
	public void stop() {
	}
	
	// entry point into our program to launch our JavaFX application
	public static void main(String[] args) {
			launch(args);
	}
	
	
}
