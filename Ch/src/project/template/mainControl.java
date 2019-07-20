package project.template;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;

public class mainControl extends Control {
    
    private chessBoard chessBoard;
    private TopBarPanel TopBarPanel; 
    private int TopBarPanelSize = 50+10;
    public boolean r=false;
    
	
	//similar to previous custom controlls but must handle more
	//complex mouse interactions and key interactions
	public mainControl(){
		setSkin(new mainControlSkin(this));
		
		TopBarPanel = new TopBarPanel();
		chessBoard = new chessBoard(TopBarPanel);
                
                
                
               
                
		getChildren().addAll(TopBarPanel, chessBoard);
                
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				chessBoard.selectPiece(event.getX(), event.getY() - (TopBarPanelSize / 2));
			}
			
		});

		// Add a key listener that will reset the game
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE)
					chessBoard.resetGame();
			}
		});
		
		TopBarPanel.getResetButton().setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				chessBoard.resetGame();
			}
			
		});
		
	}
	
	public void resize(double width, double height){
		super.resize(width, height - TopBarPanelSize);
		chessBoard.setTranslateY(TopBarPanelSize / 2);
		chessBoard.resize(width, height - TopBarPanelSize);
		TopBarPanel.resize(width, TopBarPanelSize);
		TopBarPanel.setTranslateY(-(TopBarPanelSize / 2));
	}
	
		
}
