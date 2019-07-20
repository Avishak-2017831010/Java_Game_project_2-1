package project.template;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceQueen extends Piece {
	
	private Image image;
//	private ImageView imageView = new ImageView(); 
	
	public PieceQueen(int type, int xposition, int yposition) {
		super(type, xposition, yposition);
		name = "Queen";
		// TODO Auto-generated constructor stub
		if(type==1){
			image = new Image("file:src/pieces_resource/White_Queen.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/pieces_resource/Black_Queen.png");
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
		chessBoard.colorSquare(this.xposition, this.yposition, true);
		// Bishop ability
		int y = this.yposition + 1;
		if (chessBoard.checkState && !this.isASavior)
			return;
		if (!gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.horizontalProtection(chessBoard, this.xposition, this.yposition, this.type))
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
		if (!gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.horizontalProtection(chessBoard, this.xposition, this.yposition, this.type))
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
		// Rook ability
		if (!gameLogic.horizontalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
		{
			for (y = this.yposition - 1; y >= 0; y--)
			{
				if (chessBoard.getBoardpositionition(this.xposition, y) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, y, this.type))
							chessBoard.colorSquare(this.xposition, y, false);
					}
					else
						chessBoard.colorSquare(this.xposition, y, false);
				}
				else if (chessBoard.getBoardpositionition(this.xposition, y) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, y, this.type))
							chessBoard.colorSquare(this.xposition, y, false);
					}
					else
						chessBoard.colorSquare(this.xposition, y, false);
					break;
				}
			}
			for (y = this.yposition + 1; y < chessBoard.getBoardHeight(); y++)
			{
				if (chessBoard.getBoardpositionition(this.xposition, y) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, y, this.type))
							chessBoard.colorSquare(this.xposition, y, false);
					}
					else
						chessBoard.colorSquare(this.xposition, y, false);
				}
				else if (chessBoard.getBoardpositionition(this.xposition, y) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, y, this.type))
							chessBoard.colorSquare(this.xposition, y, false);
					}
					else
						chessBoard.colorSquare(this.xposition, y, false);
					break;
				}
			}
		}
		if (!gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
		{
			for (int x = this.xposition - 1; x >= 0; x--)
			{
				if (chessBoard.getBoardpositionition(x, this.yposition) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, this.yposition, this.type))
							chessBoard.colorSquare(x, this.yposition, false);
					}
					else
						chessBoard.colorSquare(x, this.yposition, false);
				}
				else if (chessBoard.getBoardpositionition(x, this.yposition) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, this.yposition, this.type))
							chessBoard.colorSquare(x, this.yposition, false);
					}
					else
						chessBoard.colorSquare(x, this.yposition, false);
					break;
				}
			}
			for (int x = this.xposition + 1; x < chessBoard.getBoardWidth(); x++)
			{
				if (chessBoard.getBoardpositionition(x, this.yposition) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, this.yposition, this.type))
							chessBoard.colorSquare(x, this.yposition, false);
					}
					else
						chessBoard.colorSquare(x, this.yposition, false);
				}
				else if (chessBoard.getBoardpositionition(x, this.yposition) == this.type)
					break;
				else
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, x, this.yposition, this.type))
							chessBoard.colorSquare(x, this.yposition, false);
					}
					else
						chessBoard.colorSquare(x, this.yposition, false);
					break;
				}
			}
		}
	}
}
