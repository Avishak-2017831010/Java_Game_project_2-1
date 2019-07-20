package project.template;


import java.io.File;
import java.util.ArrayList;
import static java.util.Collections.rotate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.transform.Transform.rotate;
import static sun.audio.AudioPlayer.player;

public class chessBoard extends Pane {
        private int boardWidth = 8;
	private int boardHeight = 8;
	private int[][] board;

	private Piece[][] pieces;
	private tile[][] tiles;
        // pieceName_color_number
	private PieceRook rook_2_1; 
	private PieceKnight knight_2_1;
	private PieceBishop bishop_2_1;
	private PieceQueen queen_2; 
	private PieceKing king_2; 
	private PieceBishop bishop_2_2;
	private PieceKnight knight_2_2;
	private PieceRook rook_2_2;
	private PiecePawn pawn_2_1;
	private PiecePawn pawn_2_2;
	private PiecePawn pawn_2_3;
	private PiecePawn pawn_2_4;
	private PiecePawn pawn_2_5;
	private PiecePawn pawn_2_6;
	private PiecePawn pawn_2_7;
	private PiecePawn pawn_2_8;
	
	private PieceRook rook_1_1; 
	private PieceKnight knight_1_1;
	private PieceBishop bishop_1_1;
	private PieceQueen queen_1; 
	private PieceKing king_1; 
	private PieceBishop bishop_1_2;
	private PieceKnight knight_1_2;
	private PieceRook rook_1_2;
	private PiecePawn pawn_1_1;
	private PiecePawn pawn_1_2;
	private PiecePawn pawn_1_3;
	private PiecePawn pawn_1_4;
	private PiecePawn pawn_1_5;
	private PiecePawn pawn_1_6;
	private PiecePawn pawn_1_7;
	private PiecePawn pawn_1_8;
		
	private Piece selectedPiece = null;

	private TopBarPanel TopBarPanel = null;
              
	// GameLogic linked variable
	private GameLogic gameLogic = new GameLogic();
	public List<Piece> checkPieces = new ArrayList<Piece>();
	public List<Piece> saviorPieces = new ArrayList<Piece>();
	public int	playerOneRook = 2;
	public int	playerOneBishopLightSquare = 1;
	public int	playerOneBishopDarkSquare = 1;
	public int	playerOneKnight = 2;
	public int	playerOneQueen = 1;
	public int	playerOnePawn = 8;
	public int	playerTwoRook = 2;
	public int	playerTwoBishopLightSquare = 1;
	public int	playerTwoBishopDarkSquare = 1;
	public int	playerTwoKnight = 2;
	public int	playerTwoQueen = 1;
	public int	playerTwoPawn = 8;
	private Alert alert;
		
	private Rectangle background;
	private double cell_width;
	private double cell_height;
	private int current_player;
	private boolean isBlack = false; 
	public boolean checkmate = false;
	public boolean checkState = false;
	public boolean stalemate = false;
	
	private final int EMPTY = 0;
	private final int PlayerWhite = 1;
	private final int PlayerBlack = 2;
	
       
        	public Piece getKing(int type)
	{
		if (type == 1)
			return (king_1);
		return (king_2);
	}

	public int getBoardHeight()
	{
		return (this.boardHeight);
	}
	
	public int getBoardWidth()
	{
		return (this.boardWidth);
	}
	
	public int getBoardpositionition(int x, int y)
	{
		return (this.board[x][y]);
	}
	
	public void setBoard(int x, int y, int type)
	{
		this.board[x][y] = type;
	}
	
	public Piece getPiece(int x, int y)
	{
		return (pieces[x][y]);
	}
	
	public void setPiece(int x, int y, Piece piece)
	{
		this.pieces[x][y] = piece;
	}
	
	public TopBarPanel getTopBarPanel()
	{
		return (TopBarPanel);
	}
	


	public chessBoard(TopBarPanel newTopBarPanel) {
		// initalize the board: background, data structures, inital layout of
		// pieces
		TopBarPanel = newTopBarPanel;
		TopBarPanel.whitePlayerAlert.setText("");
		TopBarPanel.blackPlayerAlert.setText("");
                
                
		

		background = new Rectangle();
		background.setFill(Color.WHITE);
                

		getChildren().add(background);

		// initialize board array to the correct size
		board = new int[boardWidth][boardHeight];

		// initialize pieces array to the correct size
		pieces = new Piece[boardWidth][boardHeight];

		// initialize tiles array to the correct size
		tiles = new tile[boardWidth][boardHeight];

		// for loop to populate all arrays to default values and add the tiles
		// to the board
		for (int i = 0; i < 8; i++) {
			if(i%2 == 0 || i ==0){
				isBlack =false;
			}
			else 
				isBlack = true;
			for (int j = 0; j < 8; j++) {
				board[i][j] = EMPTY;
				if(isBlack){
					tiles[i][j] = new tile(0); //Black 
					isBlack=false;
				}else{
					tiles[i][j] = new tile(1); //White
					isBlack = true; 
				}
				getChildren().add(tiles[i][j]);
				pieces[i][j] = null;
			}
		}

		// set the current player to white
		current_player = PlayerWhite;
		initPiece();
		clock_time = new clock_time(this);
		clock_time.timeline.setCycleCount(Timeline.INDEFINITE);
		clock_time.timeline.play();
		clock_time.playerTurn = current_player;
	}
		
	public void initPiece()
	{
		
		
		
		
		rook_1_1 = new PieceRook(1, 0, 7); 
		knight_1_1 = new PieceKnight(1, 1, 7);
		bishop_1_1 = new PieceBishop(1, 2, 7);
		queen_1 = new PieceQueen(1, 3, 7);
		king_1 = new PieceKing(1, 4, 7);
		bishop_1_2 = new PieceBishop(1, 5, 7);
		knight_1_2 = new PieceKnight(1, 6, 7);
		rook_1_2 = new PieceRook(1, 7, 7);
		pawn_1_1 = new PiecePawn(1, 0, 6);
		pawn_1_2 = new PiecePawn(1, 1, 6);
		pawn_1_3 = new PiecePawn(1, 2, 6);
		pawn_1_4 = new PiecePawn(1, 3, 6);
		pawn_1_5 = new PiecePawn(1, 4, 6);
		pawn_1_6 = new PiecePawn(1, 5, 6);
		pawn_1_7 = new PiecePawn(1, 6, 6);
		pawn_1_8 = new PiecePawn(1, 7, 6);
                
                rook_2_1 = new PieceRook(2, 0, 0); 
		knight_2_1 = new PieceKnight(2, 1, 0);
		bishop_2_1 = new PieceBishop(2, 2, 0);
		queen_2 = new PieceQueen(2, 3, 0); 
		king_2 = new PieceKing(2, 4, 0);
		bishop_2_2 = new PieceBishop(2, 5, 0);
		knight_2_2 = new PieceKnight(2, 6, 0);
		rook_2_2 = new PieceRook(2, 7, 0);
		pawn_2_1 = new PiecePawn(2, 0, 1);
		pawn_2_2 = new PiecePawn(2, 1, 1);
		pawn_2_3 = new PiecePawn(2, 2, 1);
		pawn_2_4 = new PiecePawn(2, 3, 1);
		pawn_2_5 = new PiecePawn(2, 4, 1);
		pawn_2_6 = new PiecePawn(2, 5, 1);
		pawn_2_7 = new PiecePawn(2, 6, 1);
		pawn_2_8 = new PiecePawn(2, 7, 1);
                
                pieces[0][7] = rook_1_1;
		pieces[1][7] = knight_1_1;
		pieces[2][7] = bishop_1_1;
		pieces[3][7] = queen_1;
		pieces[4][7] = king_1;
		pieces[5][7] = bishop_1_2;
		pieces[6][7] = knight_1_2;
		pieces[7][7] = rook_1_2;
		
		pieces[0][0] = rook_2_1;
		pieces[1][0] = knight_2_1;
		pieces[2][0] = bishop_2_1;
		pieces[3][0] = queen_2;
		pieces[4][0] = king_2;
		pieces[5][0] = bishop_2_2;
		pieces[6][0] = knight_2_2;
		pieces[7][0] = rook_2_2;
		
		pieces[0][1] = pawn_2_1;
		pieces[1][1] = pawn_2_2;
		pieces[2][1] = pawn_2_3;
		pieces[3][1] = pawn_2_4;
		pieces[4][1] = pawn_2_5;
		pieces[5][1] = pawn_2_6;
		pieces[6][1] = pawn_2_7;
		pieces[7][1] = pawn_2_8;
                
                
                pieces[0][6] = pawn_1_1;
		pieces[1][6] = pawn_1_2;
		pieces[2][6] = pawn_1_3;
		pieces[3][6] = pawn_1_4;
		pieces[4][6] = pawn_1_5;
		pieces[5][6] = pawn_1_6;
		pieces[6][6] = pawn_1_7;
		pieces[7][6] = pawn_1_8;

		
		
		for (int y = 2; y < 6; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				pieces[x][y] = null;
			}
		}
		
		
		
		for (int y = 0; y < boardHeight; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				if (y == 0 || y == 1)
					board[x][y] = 2;
				else if (y == 6 || y == 7)
					board[x][y] = 1;
				else
					board[x][y] = 0;
			}
		}

		for(int i = 0; i < 8; i++){
			getChildren().addAll(pieces[i][0].getImage(), pieces[i][1].getImage(), pieces[i][6].getImage(), pieces[i][7].getImage());
		}
                
                
	}

	// resize method
	@Override
	public void resize(double width, double height) {
		// call the superclass resize method
		super.resize(width, height);

		// resize the rectangle to take the full size of the widget
		background.setWidth(width);
		background.setHeight(height);

		// calculate the width and height of a cell in which a tiles and a
		// piece will sit
		cell_width = width / 8.0;
		cell_height = height / 8.0;

		// nested for loop to reset the sizes and positionitions of all pieces that
		// were already placed
		// and update the positionition of the tiles in the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != 0) {
					pieces[i][j].relocate(i * cell_width, j * cell_height);
					pieces[i][j].resize(cell_width, cell_height);
				}
				tiles[i][j].relocate(i * cell_width, j * cell_height);
				tiles[i][j].resize(cell_width, cell_height);
			}
		}
	}
        
       

	// reset game method
	public void resetGame() {
		clock_time.playerTurn = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = 0;
				if (pieces[i][j] != null)
				getChildren().remove(pieces[i][j].getImage());
				getChildren().remove(pieces[i][j]);
				pieces[i][j] = null;
			}
		}
		current_player = PlayerWhite;
		initPiece();
		for(int i = 0; i < 8; i++){
			pieces[i][0].resetPiece();
			pieces[i][1].resetPiece();
			pieces[i][6].resetPiece();
			pieces[i][7].resetPiece();
		}
		unhighlighttile();
		TopBarPanel.whitePlayerAlert.setText("White First");
		TopBarPanel.blackPlayerAlert.setText("");
		TopBarPanel.winner.setText("");
		checkmate = false;
		checkState = false;
		stalemate = false;
		selectedPiece = null;
		playerOneRook = 2;
		playerOneBishopLightSquare = 1;
		playerOneBishopDarkSquare = 1;
		playerOneKnight = 2;
		playerOneQueen = 1;
		playerOnePawn = 8;
		playerTwoRook = 2;
		playerTwoBishopLightSquare = 1;
		playerTwoBishopDarkSquare = 1;
		playerTwoKnight = 2;
		playerTwoQueen = 1;
		playerTwoPawn = 8;
		checkPieces.clear();
		saviorPieces.clear();
		clock_time.timeIsOver = false;
		clock_time.whiteclock_time = 900;
		clock_time.blackclock_time = 900;
		clock_time.playerTurn = current_player;
		clock_time.timeline.play();
	}
	
	// select piece method
	public void selectPiece(final double x, final double y){
		int indexx = (int) (x/ cell_width);
		int indexY = (int) (y/ cell_height);
		
		if (!checkmate && !stalemate && !clock_time.timeIsOver)
		{
			if (tiles[indexx][indexY].isHighlighted())
			{
				movePiece(x, y);
				unhighlighttile();
				selectedPiece = null;
			}
			else
			{
				//add condition to know if the player is in check
				if(board[indexx][indexY] == current_player){
					unhighlighttile();
					pieces[indexx][indexY].SelectPiece(this);
					selectedPiece = pieces[indexx][indexY];
				}
			}
		}
	}
        
       
	// move piece method
        Media media = new Media("file:///C:/Users/Avi/Desktop/move.wav");      
        MediaPlayer player=new MediaPlayer(media);
       
        
        Rotate rotate=new Rotate();
        public int cnt=0;
        
	public void movePiece(final double x, final double y){
		int indexx = (int) (x/ cell_width);
		int indexY = (int) (y/ cell_height);
		
                       
		selectedPiece.MovePiece(this, indexx, indexY);
		
		selectedPiece.setFirstTime(false);
		
		if (current_player == PlayerWhite)
		{
                    
                         Media media = new Media("file:///C:/Users/Avi/Desktop/move.wav");      
                         MediaPlayer player=new MediaPlayer(media);
                         player.setVolume(1);
                   
                         player.setAutoPlay(true);
                        
			current_player = PlayerBlack;
			TopBarPanel.whitePlayerAlert.setText("");
			checkState = false;
			for(Iterator<Piece> piece = saviorPieces.iterator(); piece.hasNext(); ) {
			    Piece item = piece.next();
			    item.isASavior = false;
			}
			if (gameLogic.isCheck(this, king_2.xposition, king_2.yposition, current_player, true))
			{
				checkPieces.clear();
				saviorPieces.clear();
				checkState = true;
				gameLogic.findAllCheckPieces(this, king_2.xposition, king_2.yposition, current_player);
				if (gameLogic.isCheckmate(this, king_2.xposition, king_2.yposition, current_player))
				{
					checkmate = true;
					TopBarPanel.blackPlayerAlert.setText("Black is in checkmate");
					TopBarPanel.winner.setText("Grey player won !");
				}
				else
					TopBarPanel.blackPlayerAlert.setText("Black player is in check");
			}
			else if (gameLogic.isStalemate(this, king_2, current_player))
				TopBarPanel.winner.setText("Stalemate !");
			else
				TopBarPanel.blackPlayerAlert.setText("Black");
		}
		else
		{
                    
                        Media media = new Media("file:///C:/Users/Avi/Desktop/move.wav");      
                        MediaPlayer player=new MediaPlayer(media);
                        player.setVolume(1);
                        player.setAutoPlay(true);
                    
			current_player = PlayerWhite;
			TopBarPanel.blackPlayerAlert.setText("");
			checkState = false;
			for(Iterator<Piece> piece = saviorPieces.iterator(); piece.hasNext(); ) {
			    Piece item = piece.next();
			    item.isASavior = false;
			}
			if (gameLogic.isCheck(this, king_1.xposition, king_1.yposition, current_player, true))
			{
				checkPieces.clear();
				saviorPieces.clear();
				checkState = true;
				gameLogic.findAllCheckPieces(this, king_1.xposition, king_1.yposition, current_player);
				if (gameLogic.isCheckmate(this, king_1.xposition, king_1.yposition, current_player))
				{
					checkmate = true;
					TopBarPanel.whitePlayerAlert.setText("White is checkmated");
					TopBarPanel.winner.setText("Green player won !");
				}
				else
					TopBarPanel.whitePlayerAlert.setText("White player is in check");
			}
			else if (gameLogic.isStalemate(this, king_1, current_player))
				TopBarPanel.winner.setText("Stalemate !");
			else
				TopBarPanel.whitePlayerAlert.setText("White");
		}
		clock_time.playerTurn = current_player;
	}
	
	public void createPromotePiece(Piece piece)
	{
		Piece promotedPiece;
		
	 {
			promotedPiece = new PieceQueen(piece.type, piece.xposition, piece.yposition);
			getChildren().remove(piece.getImage());
			getChildren().add(promotedPiece.getImage());
			pieces[piece.xposition][piece.yposition] = promotedPiece;
			if (piece.type == 1)
				playerOneQueen++;
			else
				playerTwoQueen++;
		}
	}
	
	public void colorSquare(int x, int y, boolean selectedPiece) {
		if (selectedPiece){
			tiles[x][y].highlighttile(Color.DARKRED);
                          tiles[x][y].setStyle("-fx-border-width: 100;-fx-border-style: solid");
                        
                        
        }
            else{
			tiles[x][y].highlighttile(Color.DARKORANGE);
                         tiles[x][y].setStyle("-fx-border-width: 100;-fx-border-style: solid");
	}
        }
	
	public void unhighlighttile()
	{
		for (int y = 0; y < boardHeight; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				if (tiles[x][y].getRectangle().getStroke() != null)
					tiles[x][y].unhighlight();
			}
		}
	}
	
	
	// Getter and setter method
	

		// private fields
	
	
	private clock_time clock_time;
}
