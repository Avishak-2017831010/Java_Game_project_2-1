package project.template;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceKing extends Piece{


//	private int xposition;
//	private int yposition;
//	private int type;
	private Image image;
//	private ImageView imageView = new ImageView();
	
	public PieceKing(int type, int xposition, int yposition) {
		super(type, xposition, yposition);
		name = "King";
		// TODO Auto-generated constructor stub
		
		if(type==1){
			image = new Image("file:src/pieces_resource/White_King.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}
		else{
			image = new Image("file:src/pieces_resource/Black_King.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}
	}
	
	@Override
	public ImageView getImage() {
		return (imageView);
	}
	
	@Override
	public void SelectPiece(chessBoard chessBoard) {
		int x = this.xposition;
		int y = this.yposition;
		chessBoard.colorSquare(this.xposition, this.yposition, true);
		for (y = this.yposition - 1; y <= this.yposition + 1; y++)
		{
			for (x = this.xposition - 1; x <= this.xposition + 1; x++)
			{
				if(y >= 0 && y < chessBoard.getBoardHeight() && x >= 0 && x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(x, y) != this.type)
				{
					if (!chessBoard.checkState)
						this.canCastle(chessBoard);
					// Check si echec et mat sur cette case
					if (!gameLogic.isCheck(chessBoard, x, y, this.type, true))
						chessBoard.colorSquare(x, y, false);
				}
			}
		}
		// Mouvement Roque (castling)
		// cliquer sur l'autre pièce pour faire le roque 
		// use canCastle 
		
	}
	
	
	public int canCastle(chessBoard chessBoard){
		int canCastle =0;
		//Black 
		//shortCastle (5 and 6 empty) 
		if(type==2 && this.isFirstTime && chessBoard.getBoardpositionition(5, 0) == 0 && chessBoard.getBoardpositionition(6, 0) == 0 && chessBoard.getPiece(7, 0) != null && chessBoard.getPiece(7, 0).isFirstTime){
			canCastle = 1;
			chessBoard.colorSquare(7, 0, false);
		}
		//longCastle (1 2 3 empty)
		if(type==2 && this.isFirstTime && chessBoard.getBoardpositionition(1, 0) == 0 && chessBoard.getBoardpositionition(2, 0) == 0 && chessBoard.getBoardpositionition(3, 0) == 0 && chessBoard.getPiece(0, 0) != null && chessBoard.getPiece(0, 0).isFirstTime){
			canCastle = 2;
			chessBoard.colorSquare(0, 0, false);
		}
		// White
		//shortCastle (5 and 6 empty) 
		if(type==1 && this.isFirstTime && chessBoard.getBoardpositionition(5, 7) == 0 && chessBoard.getBoardpositionition(6, 7) == 0 && chessBoard.getPiece(7, 7) != null && chessBoard.getPiece(7, 7).isFirstTime){
			canCastle = 3;
			chessBoard.colorSquare(7, 7, false);
		}
		//longCastle (1 2 3 empty)
		if(type==1 && this.isFirstTime && chessBoard.getBoardpositionition(1, 7) == 0 && chessBoard.getBoardpositionition(2, 7) == 0 && chessBoard.getBoardpositionition(3, 7) == 0 && chessBoard.getPiece(0, 7) != null && chessBoard.getPiece(0, 7).isFirstTime){
			canCastle = 4;
			chessBoard.colorSquare(0, 7, false);
		}
		return canCastle; 
	}
}
