package project.template;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceBishop extends Piece{

//	private int xposition;
//	private int yposition;
//	private int type;
	private Image image;
//	private ImageView imageView = new ImageView();

	public PieceBishop(int type, int xposition, int yposition) {
		super(type, xposition, yposition);
		name = "Bishop";
//		this.type = type;
//		this.xposition = xposition;
//		this.yposition = yposition;
		// TODO Auto-generated constructor stub
		if(type==1){
			image = new Image("file:src/pieces_resource/White_Bishop.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}
		else{
			image = new Image("file:src/pieces_resource/Black_Bishop.png");
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
		int y = this.yposition + 1;
		chessBoard.colorSquare(this.xposition, this.yposition, true);
		if (chessBoard.checkState && !this.isASavior)
			return;
		if (gameLogic.horizontalProtection(chessBoard, this.xposition, this.yposition, this.type) || gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type))
			return;
		if (!gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
		{
			for(int x = this.xposition + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++)
			{
				if (chessBoard.getBoardpositionition(x, y) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if (chessBoard.getBoardpositionition(x, y) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
			y = this.yposition - 1;
			for(int x = this.xposition - 1; x >= 0 && y >= 0; x--, y--)
			{
				if (chessBoard.getBoardpositionition(x, y) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if (chessBoard.getBoardpositionition(x, y) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
		}
		if (!gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
		{
			y = this.yposition + 1;
			for (int x = this.xposition - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++)
			{
				if (chessBoard.getBoardpositionition(x, y) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if (chessBoard.getBoardpositionition(x, y) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
			y = this.yposition - 1;
			for (int x = this.xposition + 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--)
			{
				if (chessBoard.getBoardpositionition(x, y) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
				}
				else if (chessBoard.getBoardpositionition(x, y) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, y, this.type))
							chessBoard.colorSquare(x, y, false);
					}
					else
						chessBoard.colorSquare(x, y, false);
					break;
				}
			}
		}
	}
}
