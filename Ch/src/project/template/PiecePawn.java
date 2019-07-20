package project.template;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PiecePawn extends Piece{

//	private int xposition;
//	private int yposition;
//	private int type;
	private Image image;
//	private ImageView imageView = new ImageView(); 
	
	public PiecePawn(int type, int xposition, int yposition) {
		super(type, xposition, yposition);
		name = "Pawn";
		// TODO Auto-generated constructor stub
		// TODO tester les paramètres 
		if(type==1){
			image = new Image("file:src/pieces_resource/White_Pawn.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/pieces_resource/Black_Pawn.png");
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
		if (chessBoard.checkState && !this.isASavior)
			return;
		if (gameLogic.horizontalProtection(chessBoard, this.xposition, this.yposition, this.type))
			return;
		if (this.type == 1)
		{
			if (!gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
			{
				if (this.yposition - 1 >= 0 && chessBoard.getBoardpositionition(this.xposition, this.yposition - 1) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, this.yposition - 1, this.type))
							chessBoard.colorSquare(this.xposition, this.yposition - 1, false);
					}
					else
						chessBoard.colorSquare(this.xposition, this.yposition - 1, false);
				}
				if (this.isFirstTime == true && chessBoard.getBoardpositionition(this.xposition, this.yposition - 2) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, this.yposition - 2, this.type))
							chessBoard.colorSquare(this.xposition, this.yposition - 2, false);
					}
					else
						chessBoard.colorSquare(this.xposition, this.yposition - 2, false);
				}
			}
			if (!gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type))
			{
				if (!gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
				{
					if (this.yposition - 1 >= 0 && this.xposition - 1 >= 0 && chessBoard.getBoardpositionition(this.xposition - 1, this.yposition - 1) != this.type && chessBoard.getBoardpositionition(this.xposition - 1, this.yposition - 1) != 0)
					{
						if (chessBoard.checkState)
						{
							if (gameLogic.isThisProtecting(chessBoard, this.xposition - 1, this.yposition - 1, this.type))
								chessBoard.colorSquare(this.xposition - 1, this.yposition - 1, false);
						}
						else
							chessBoard.colorSquare(this.xposition - 1, this.yposition - 1, false);
					}
				}
				if (!gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
				{
					if (this.yposition - 1 >= 0 && this.xposition + 1 < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(this.xposition + 1, this.yposition - 1) != this.type && chessBoard.getBoardpositionition(this.xposition + 1, this.yposition - 1) != 0)
					{
						if (chessBoard.checkState)
						{
							if (gameLogic.isThisProtecting(chessBoard, this.xposition + 1, this.yposition - 1, this.type))
								chessBoard.colorSquare(this.xposition + 1, this.yposition - 1, false);
						}
						else
							chessBoard.colorSquare(this.xposition + 1, this.yposition - 1, false);
					}
				}
			}
		}
		else if (this.type == 2)
		{
			if (!gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type) && !gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
			{
				if (this.yposition + 1 < chessBoard.getBoardHeight() && chessBoard.getBoardpositionition(this.xposition, this.yposition + 1) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, this.yposition + 1, this.type))
							chessBoard.colorSquare(this.xposition, this.yposition + 1, false);
					}
					else
						chessBoard.colorSquare(this.xposition, this.yposition + 1, false);
				}
				if (this.isFirstTime == true && chessBoard.getBoardpositionition(this.xposition, this.yposition + 2) == 0)
				{
					if (chessBoard.checkState)
					{
						if (gameLogic.isThisProtecting(chessBoard, this.xposition, this.yposition + 2, this.type))
							chessBoard.colorSquare(this.xposition, this.yposition + 2, false);
					}
					else
						chessBoard.colorSquare(this.xposition, this.yposition + 2, false);
				}
			}
			if (!gameLogic.verticalProtection(chessBoard, this.xposition, this.yposition, this.type))
			{
				if (!gameLogic.backslashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
				{
					if (this.yposition + 1 < chessBoard.getBoardHeight() && this.xposition - 1 >= 0 && chessBoard.getBoardpositionition(this.xposition - 1, this.yposition + 1) != this.type && chessBoard.getBoardpositionition(this.xposition - 1, this.yposition + 1) != 0)
					{
						if (chessBoard.checkState)
						{
							if (gameLogic.isThisProtecting(chessBoard, this.xposition - 1, this.yposition + 1, this.type))
								chessBoard.colorSquare(this.xposition - 1, this.yposition + 1, false);
						}
						else
							chessBoard.colorSquare(this.xposition - 1, this.yposition + 1, false);
					}
				}
				if (!gameLogic.slashDiagonalProtection(chessBoard, this.xposition, this.yposition, this.type))
				{
					if (this.yposition + 1 < chessBoard.getBoardHeight() && this.xposition + 1 < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(this.xposition + 1, this.yposition + 1) != this.type && chessBoard.getBoardpositionition(this.xposition + 1, this.yposition + 1) != 0)
					{
						if (chessBoard.checkState)
						{
							if (gameLogic.isThisProtecting(chessBoard, this.xposition + 1, this.yposition + 1, this.type))
								chessBoard.colorSquare(this.xposition + 1, this.yposition + 1, false);
						}
						else
							chessBoard.colorSquare(this.xposition + 1, this.yposition + 1, false);
					}
				}
			}
		}
	}
}
