package project.template;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceKnight extends Piece{

//	private int xposition;
//	private int yposition;
//	private int type;
	private Image image;
//	private ImageView imageView = new ImageView(); 
	
	public PieceKnight(int type, int xposition, int yposition) {
		super(type, xposition, yposition);
		name = "Knight";
		// TODO Auto-generated constructor stub
		if(type==1){
			image = new Image("file:src/pieces_resource/White_Knight.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/pieces_resource/Black_Knight.png");
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
		int x = 0;
		chessBoard.colorSquare(this.xposition, this.yposition, true);
		if (chessBoard.checkState && !this.isASavior)
			return;
		if (gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type) || gameLogic.horizontalProtection(chessBoard, this.xposition, this.yposition, this.type) || 
			gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) || gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
			return;
		for (int y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (this.yposition + y >= 0 && this.yposition + y < chessBoard.getBoardHeight() && this.xposition - x >= 0 && this.xposition - x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(this.xposition - x, this.yposition + y) != this.type)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition - x, this.yposition + y, this.type))
							chessBoard.colorSquare(this.xposition - x, this.yposition + y, false);
					}
					else
						chessBoard.colorSquare(this.xposition - x, this.yposition + y, false);
				}
				if (this.yposition + y >= 0 && this.yposition + y < chessBoard.getBoardHeight() && this.xposition + x >= 0 && this.xposition + x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(this.xposition + x, this.yposition + y) != this.type)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition + x, this.yposition + y, this.type))
							chessBoard.colorSquare(this.xposition + x, this.yposition + y, false);
					}
					else
						chessBoard.colorSquare(this.xposition + x, this.yposition + y, false);
				}
			}
		}
	}	
}
