package project.template;

import java.util.Iterator;

public class GameLogic {
	
	//method to detect stalemate
	private boolean isOneKingStalemate(chessBoard chessBoard, Piece king, int type)
	{
		int nbPiece = 0;
		boolean stalemate = true;

		// A Player has only 1 king left, which is not in check positionition and can't move
		for (int y = 0; y < chessBoard.getBoardHeight(); y++)
		{
			for (int x = 0; x < chessBoard.getBoardWidth(); x++)
			{
				if (chessBoard.getBoardpositionition(x, y) == type)
					nbPiece++;
			}
		}
		if (nbPiece == 1)
		{
			for (int y = king.yposition - 1; y <= king.yposition + 1; y++)
			{
				for (int x = king.xposition - 1; x <= king.xposition + 1; x++)
				{
					if(y >= 0 && y < chessBoard.getBoardHeight() && x >= 0 && x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(x, y) != type)
					{
						if (!isCheck(chessBoard, x, y, type, true))
						{
							stalemate = false;
							break;
						}
					}
				}
				if (!stalemate)
					break;
			}
		}
		else
			stalemate = false;
		return (stalemate);
	}
	
	public boolean isLimitPieceStalemate(chessBoard chessBoard)
	{
		// Both Player have less then:
		// A king and a Queen
		// A king and a Rook
		// A king and two knight
		// A king, a bishop and a knight
		// A king and two bishop (one light square, one dark square)
		// A king and at least a pawn
		if (chessBoard.playerOneQueen != 0 || chessBoard.playerTwoQueen != 0)
			return (false);
		else if (chessBoard.playerOneRook != 0 || chessBoard.playerTwoRook != 0)
			return (false);
		else if (chessBoard.playerOneKnight > 1 || chessBoard.playerTwoKnight > 1)
			return (false);
		else if (((chessBoard.playerOneBishopDarkSquare != 0 || chessBoard.playerOneBishopLightSquare != 0) && chessBoard.playerOneKnight != 0) ||
				((chessBoard.playerTwoBishopDarkSquare != 0 || chessBoard.playerTwoBishopLightSquare != 0) && chessBoard.playerTwoKnight != 0))
			return (false);
		else if ((chessBoard.playerOneBishopDarkSquare != 0 && chessBoard.playerOneBishopLightSquare != 0) || (chessBoard.playerTwoBishopDarkSquare != 0 && chessBoard.playerTwoBishopLightSquare != 0))
			return (false);
		else if (chessBoard.playerOnePawn > 1 || chessBoard.playerTwoPawn > 1)
			return (false);
		return (true);
	}
	
	public boolean isStalemate(chessBoard chessBoard, Piece king, int type)
	{
		if (isOneKingStalemate(chessBoard, king, type) || isLimitPieceStalemate(chessBoard))
		{
			chessBoard.stalemate = true;
			return (true);
		}
		return (false);
	}
	
	// Method to check if a piece is protecting the king from a check
	public boolean verticalProtection(chessBoard chessBoard, int xposition, int yposition, int type)
	{
		int y = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		// King on the Vertical Up
		for (y = yposition - 1; y >= 0; y--)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type && chessBoard.getPiece(xposition, y).name == "King")
			{
				for (y = yposition + 1; y < chessBoard.getBoardHeight(); y++)
				{
					if (chessBoard.getBoardpositionition(xposition, y) == type)
						break;
					else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
					{
						if (chessBoard.getPiece(xposition,  y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (chessBoard.getBoardpositionition(xposition, y) != 0)
				break;
		}		
		// King on the Vertical Down
		for (y = yposition + 1; y < chessBoard.getBoardHeight(); y++)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type && chessBoard.getPiece(xposition, y).name == "King")
			{
				for (y = yposition - 1; y >= 0; y--)
				{
					if (chessBoard.getBoardpositionition(xposition, y) == type)
						break;
					else if (chessBoard.getBoardpositionition(xposition,  y) == enemyType)
					{
						if (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;				
			}
			else if (chessBoard.getBoardpositionition(xposition, y) != 0)
				break;
		}
		return (false);
	}
	
	public boolean horizontalProtection(chessBoard chessBoard, int xposition, int yposition, int type)
	{
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		// King on the Horizontal Left
		for (x = xposition - 1; x >= 0; x--)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type && chessBoard.getPiece(x, yposition).name == "King")
			{
				for (x = xposition + 1; x < chessBoard.getBoardWidth(); x++)
				{
					if (chessBoard.getBoardpositionition(x, yposition) == type)
						break;
					else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
					{
						if (chessBoard.getPiece(x,  yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (chessBoard.getBoardpositionition(x, yposition) != 0)
				break;
		}
		// King on the Horizontal Right
		for (x = xposition + 1; x < chessBoard.getBoardWidth(); x++)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type && chessBoard.getPiece(x, yposition).name == "King")
			{
				for (x = xposition - 1; x >= 0; x--)
				{
					if (chessBoard.getBoardpositionition(x, yposition) == type)
						break;
					else if (chessBoard.getBoardpositionition(x,  yposition) == enemyType)
					{
						if (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;				
			}
			else if (chessBoard.getBoardpositionition(x, yposition) != 0)
				break;
		}
		return (false);
	}
	
	public boolean slashDiagonalProtection(chessBoard chessBoard, int xposition, int yposition, int type)
	{
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		// King on the Diagonal / Up
		int y = yposition - 1;
		for (int x = xposition + 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name == "King")
			{
				y = yposition + 1;
				for (x = xposition - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++)
				{
					if (chessBoard.getBoardpositionition(x, y) == type)
						break;
					else if (chessBoard.getBoardpositionition(x, y) == enemyType)
					{
						if (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (chessBoard.getBoardpositionition(x, y) != 0)
				break;
		}
		// King on the Diagonal / Down
		y = yposition + 1;
		for (int x = xposition - 1; x >= 0 && y < chessBoard.getBoardHeight(); x--, y++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name == "King")
			{
				y = yposition - 1;
				for (x = xposition + 1; x < chessBoard.getBoardWidth() && y >= 0; x++, y--)
				{
					if (chessBoard.getBoardpositionition(x, y) == type)
						break;
					else if (chessBoard.getBoardpositionition(x, y) == enemyType)
					{
						if (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (chessBoard.getBoardpositionition(x, y) != 0)
				break;			
		}
		return (false);
	}
	
	public boolean backslashDiagonalProtection(chessBoard chessBoard, int xposition, int yposition, int type)
	{
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		// King on the Diagonal \ Up
		int y = yposition - 1;
		for(int x = xposition - 1; x >= 0 && y >= 0; x--, y--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name == "King")
			{
				y = yposition + 1;
				for(x = xposition + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++)
				{
					if (chessBoard.getBoardpositionition(x, y) == type)
						break;
					else if (chessBoard.getBoardpositionition(x, y) == enemyType)
					{
						if (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (chessBoard.getBoardpositionition(x, y) != 0)
				break;
		}
		// King on the Diagonal \ Down
		y = yposition + 1;
		for(int x = xposition + 1; x < chessBoard.getBoardWidth() && y < chessBoard.getBoardHeight(); x++, y++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name == "King")
			{
				y = yposition - 1;
				for(x = xposition - 1; x >= 0 && y >= 0; x--, y--)
				{
					if (chessBoard.getBoardpositionition(x, y) == type)
						break;
					else if (chessBoard.getBoardpositionition(x, y) == enemyType)
					{
						if (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (chessBoard.getBoardpositionition(x, y) != 0)
				break;
		}		
		return (false);
	}
		
	// Method to check check
	public boolean isCheck(chessBoard chessBoard, int xposition, int yposition, int type, boolean kingCanCapture) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;
		
		// Horizontal Left
		for (x = xposition - 1; x >= 0; x--)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type && chessBoard.getPiece(x, yposition).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
			{
				if (x == xposition - 1 && chessBoard.getPiece(x, yposition) != null && kingCanCapture && chessBoard.getPiece(x, yposition).name == "King")
					return (true);
				else if (chessBoard.getPiece(x, yposition) != null && (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		// Horizontal Right
		for (x = xposition + 1; x < chessBoard.getBoardWidth(); x++)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type && chessBoard.getPiece(x, yposition).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
			{
				if (x == xposition + 1 && chessBoard.getPiece(x, yposition) != null && kingCanCapture && chessBoard.getPiece(x, yposition).name == "King")
					return (true);
				else if (chessBoard.getPiece(x, yposition) != null && (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		// Vertical Up
		for (y = yposition - 1; y >= 0; y--)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type && chessBoard.getPiece(xposition, y).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
			{
				if (y == yposition - 1 && chessBoard.getPiece(xposition, y) != null && kingCanCapture && chessBoard.getPiece(xposition, y).name == "King")
					return (true);
				else if (chessBoard.getPiece(xposition, y) != null && (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		// Vertical Down
		for (y = yposition + 1; y < chessBoard.getBoardHeight(); y++)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type && chessBoard.getPiece(xposition, y).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
			{
				if (y == yposition + 1 && chessBoard.getPiece(xposition, y) != null && kingCanCapture && chessBoard.getPiece(xposition, y).name == "King")
					return (true);
				else if (chessBoard.getPiece(xposition, y) != null && (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		// Diagonal 1 \ Up
		for (y = yposition - 1, x = xposition - 1; y >= 0 && x >= 0; y--, x--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition - 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && ((kingCanCapture && chessBoard.getPiece(x, y).name == "King") || (type == 1 && chessBoard.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		// Diagonal 1 \ Down
		for (y = yposition + 1, x = xposition + 1; y < chessBoard.getBoardHeight() && x < chessBoard.getBoardWidth(); y++, x++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition + 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && ((kingCanCapture && chessBoard.getPiece(x, y).name == "King") || (type == 2 && chessBoard.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		// Diagonal 2 / Up
		for (y = yposition - 1, x = xposition + 1; y >= 0 && x < chessBoard.getBoardWidth(); y--, x++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition - 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && ((kingCanCapture && chessBoard.getPiece(x, y).name == "King") || (type == 1 && chessBoard.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		// Diagonal 2 / Down
		for (y = yposition + 1, x = xposition - 1; y < chessBoard.getBoardHeight() && x >= 0; y++, x--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type && chessBoard.getPiece(x, y).name != "King")
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition + 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && ((kingCanCapture && chessBoard.getPiece(x, y).name == "King") || (type == 2 && chessBoard.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (chessBoard.getBoardpositionition(x, y) != 0 && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}		
		// Knight
		for (y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (yposition + y >= 0 && yposition + y < chessBoard.getBoardHeight() && xposition - x >= 0 && xposition - x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(xposition - x, yposition + y) != type && chessBoard.getBoardpositionition(xposition - x, yposition + y) != 0)
				{
					if (chessBoard.getPiece(xposition - x, yposition + y) != null && chessBoard.getPiece(xposition - x, yposition + y).name == "Knight")
						return (true);
				}
				if (yposition + y >= 0 && yposition + y < chessBoard.getBoardHeight() && xposition + x >= 0 && xposition + x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(xposition + x, yposition + y) != type && chessBoard.getBoardpositionition(xposition + x, yposition + y) != 0)
				{
					if (chessBoard.getPiece(xposition + x, yposition + y) != null && chessBoard.getPiece(xposition + x, yposition + y).name == "Knight")
						return (true);
				}
			}
		}
		return (false);
	}
	
	// Method to find all the piece that create a check
	public void findAllCheckPieces(chessBoard chessBoard, int xposition, int yposition, int type) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;
		
		// Horizontal Left
		for (x = xposition - 1; x >= 0; x--)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
			{
				if (chessBoard.getPiece(x, yposition) != null && (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, yposition));
				else
					break;
			}
		}
		// Horizontal Right
		for (x = xposition + 1; x < chessBoard.getBoardWidth(); x++)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
			{
				if (chessBoard.getPiece(x, yposition) != null && (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, yposition));
				else
					break;
			}
		}
		// Vertical Up
		for (y = yposition - 1; y >= 0; y--)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
			{
				if (chessBoard.getPiece(xposition, y) != null && (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(xposition, y));
				else
					break;
			}
		}
		// Vertical Down
		for (y = yposition + 1; y < chessBoard.getBoardHeight(); y++)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
			{
				if (chessBoard.getPiece(xposition, y) != null && (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook"))
					chessBoard.checkPieces.add(chessBoard.getPiece(xposition, y));
				else
					break;
			}
		}
		// Diagonal 1 \ Up
		for (y = yposition - 1, x = xposition - 1; y >= 0 && x >= 0; y--, x--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition - 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		// Diagonal 1 \ Down
		for (y = yposition + 1, x = xposition + 1; y < chessBoard.getBoardHeight() && x < chessBoard.getBoardWidth(); y++, x++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition + 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		// Diagonal 2 / Up
		for (y = yposition - 1, x = xposition + 1; y >= 0 && x < chessBoard.getBoardWidth(); y--, x++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition - 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		// Diagonal 2 / Down
		for (y = yposition + 1, x = xposition - 1; y < chessBoard.getBoardHeight() && x >= 0; y++, x--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (y == yposition + 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				if (chessBoard.getBoardpositionition(x, y) != 0 && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.checkPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}		
		// Knight
		for (y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (yposition + y >= 0 && yposition + y < chessBoard.getBoardHeight() && xposition - x >= 0 && xposition - x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(xposition - x, yposition + y) != type && chessBoard.getBoardpositionition(xposition - x, yposition + y) != 0)
				{
					if (chessBoard.getPiece(xposition - x, yposition + y) != null && chessBoard.getPiece(xposition - x, yposition + y).name == "Knight")
						chessBoard.checkPieces.add(chessBoard.getPiece(xposition - x, yposition + y));
				}
				if (yposition + y >= 0 && yposition + y < chessBoard.getBoardHeight() && xposition + x >= 0 && xposition + x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(xposition + x, yposition + y) != type && chessBoard.getBoardpositionition(xposition + x, yposition + y) != 0)
				{
					if (chessBoard.getPiece(xposition + x, yposition + y) != null && chessBoard.getPiece(xposition + x, yposition + y).name == "Knight")
						chessBoard.checkPieces.add(chessBoard.getPiece(xposition + x, yposition + y));
				}
			}
		}
	}
	
	// Method to find all the piece that can save the king from a checkmate
	public void findAllSaviorPieces(chessBoard chessBoard, int xposition, int yposition, int type, boolean protect) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;
		
		// Horizontal Left
		for (x = xposition - 1; x >= 0; x--)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
			{
				if (chessBoard.getPiece(x, yposition) != null && (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, yposition));
				else
					break;
			}
		}
		// Horizontal Right
		for (x = xposition + 1; x < chessBoard.getBoardWidth(); x++)
		{
			if (chessBoard.getBoardpositionition(x, yposition) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, yposition) == enemyType)
			{
				if (chessBoard.getPiece(x, yposition) != null && (chessBoard.getPiece(x, yposition).name == "Queen" || chessBoard.getPiece(x, yposition).name == "Rook"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, yposition));
				else
					break;
			}
		}
		// Vertical Up
		for (y = yposition - 1; y >= 0; y--)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
			{
				if (enemyType == 2 && protect == true && y == yposition - 1 && chessBoard.getPiece(xposition, y) != null && chessBoard.getPiece(xposition, y).name == "Pawn")
					chessBoard.saviorPieces.add(chessBoard.getPiece(xposition, y));
				if (enemyType == 2 && protect == true && y == yposition - 2 && chessBoard.getPiece(xposition, y) != null && chessBoard.getPiece(xposition, y).name == "Pawn" && chessBoard.getPiece(xposition, y).isFirstTime())
					chessBoard.saviorPieces.add(chessBoard.getPiece(xposition, y));
				if (chessBoard.getPiece(xposition, y) != null && (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(xposition, y));
				else
					break;
			}
		}
		// Vertical Down
		for (y = yposition + 1; y < chessBoard.getBoardHeight(); y++)
		{
			if (chessBoard.getBoardpositionition(xposition, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(xposition, y) == enemyType)
			{
				if (enemyType == 1 && protect == true && y == yposition + 1 && chessBoard.getPiece(xposition, y) != null && chessBoard.getPiece(xposition, y).name == "Pawn")
					chessBoard.saviorPieces.add(chessBoard.getPiece(xposition, y));
				if (enemyType == 1 && protect == true && y == yposition + 2 && chessBoard.getPiece(xposition, y) != null && chessBoard.getPiece(xposition, y).name == "Pawn" && chessBoard.getPiece(xposition, y).isFirstTime())
					chessBoard.saviorPieces.add(chessBoard.getPiece(xposition, y));
				if (chessBoard.getPiece(xposition, y) != null && (chessBoard.getPiece(xposition, y).name == "Queen" || chessBoard.getPiece(xposition, y).name == "Rook"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(xposition, y));
				else
					break;
			}
		}
		// Diagonal 1 \ Up
		for (y = yposition - 1, x = xposition - 1; y >= 0 && x >= 0; y--, x--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (protect == false && y == yposition - 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		// Diagonal 1 \ Down
		for (y = yposition + 1, x = xposition + 1; y < chessBoard.getBoardHeight() && x < chessBoard.getBoardWidth(); y++, x++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (protect == false && y == yposition + 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		// Diagonal 2 / Up
		for (y = yposition - 1, x = xposition + 1; y >= 0 && x < chessBoard.getBoardWidth(); y--, x++)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (protect == false && y == yposition - 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 1 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				if (chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}
		// Diagonal 2 / Down
		for (y = yposition + 1, x = xposition - 1; y < chessBoard.getBoardHeight() && x >= 0; y++, x--)
		{
			if (chessBoard.getBoardpositionition(x, y) == type)
				break;
			else if (chessBoard.getBoardpositionition(x, y) == enemyType)
			{
				if (protect == false && y == yposition + 1 && chessBoard.getBoardpositionition(x, y) != 0 && chessBoard.getPiece(x, y) != null && (type == 2 && chessBoard.getPiece(x, y).name == "Pawn"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				if (chessBoard.getBoardpositionition(x, y) != 0 && (chessBoard.getPiece(x, y).name == "Queen" || chessBoard.getPiece(x, y).name == "Bishop"))
					chessBoard.saviorPieces.add(chessBoard.getPiece(x, y));
				else
					break;
			}
		}		
		// Knight
		for (y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (yposition + y >= 0 && yposition + y < chessBoard.getBoardHeight() && xposition - x >= 0 && xposition - x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(xposition - x, yposition + y) != type && chessBoard.getBoardpositionition(xposition - x, yposition + y) != 0)
				{
					if (chessBoard.getPiece(xposition - x, yposition + y) != null && chessBoard.getPiece(xposition - x, yposition + y).name == "Knight")
						chessBoard.saviorPieces.add(chessBoard.getPiece(xposition - x, yposition + y));
				}
				if (yposition + y >= 0 && yposition + y < chessBoard.getBoardHeight() && xposition + x >= 0 && xposition + x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(xposition + x, yposition + y) != type && chessBoard.getBoardpositionition(xposition + x, yposition + y) != 0)
				{
					if (chessBoard.getPiece(xposition + x, yposition + y) != null && chessBoard.getPiece(xposition + x, yposition + y).name == "Knight")
						chessBoard.saviorPieces.add(chessBoard.getPiece(xposition + x, yposition + y));
				}
			}
		}
	}

	
	// Method to check checkmate
	public boolean isCheckmate(chessBoard chessBoard, int xposition, int yposition, int type)
	{
		boolean checkmate = true;
		int x = xposition;
		int y = yposition;
		for (y = yposition - 1; y <= yposition + 1; y++)
		{
			for (x = xposition - 1; x <= xposition + 1; x++)
			{
				if(y >= 0 && y < chessBoard.getBoardHeight() && x >= 0 && x < chessBoard.getBoardWidth() && chessBoard.getBoardpositionition(x, y) != type)
				{
					if (!isCheck(chessBoard, x, y, type, true))
					{
						checkmate = false;
						break;
					}
				}
			}
			if (!checkmate)
				break;
		}
		/* NO castle when you are in check
		// Check is the king can castle
		if (chessBoard.getPiece(xposition, yposition).canCastle(chessBoard) == 1 && !isCheck(chessBoard, xposition - 1, yposition, type, true))
			return (false);
		if (chessBoard.getPiece(xposition, yposition).canCastle(chessBoard) == 2 && !isCheck(chessBoard, xposition + 2, yposition, type, true))
			return (false);
		if (chessBoard.getPiece(xposition, yposition).canCastle(chessBoard) == 3 && !isCheck(chessBoard, xposition - 1, yposition, type, true))
			return (false);
		if (chessBoard.getPiece(xposition, yposition).canCastle(chessBoard) == 4 && !isCheck(chessBoard, xposition + 2, yposition, type, true))
			return (false);
				*/
		if (chessBoard.checkPieces.size() < 2)
		{
			Piece checkPiece = chessBoard.checkPieces.get(0);
			canCapture(chessBoard, checkPiece);
			canProtect(chessBoard, xposition, yposition, type, checkPiece);
			if (!chessBoard.saviorPieces.isEmpty())
			{
				for(Iterator<Piece> piece = chessBoard.saviorPieces.iterator(); piece.hasNext(); )
				{
					Piece item = piece.next();
					item.isASavior = true;
					if (verticalProtection(chessBoard, item.xposition, item.yposition, item.type) || horizontalProtection(chessBoard, item.xposition, item.yposition, item.type) ||
				    	slashDiagonalProtection(chessBoard, item.xposition, item.yposition, item.type) || backslashDiagonalProtection(chessBoard, item.xposition, item.yposition, item.type))
				    {
				    	item.isASavior = false;
				    	piece.remove();
				    }
				}
			}
			if (!chessBoard.saviorPieces.isEmpty())
				checkmate = false;
		}
		return (checkmate);
	}
	
	// Method to check is someone can capture the piece that threat the king
	public void canCapture(chessBoard chessBoard, Piece checkPiece)
	{
		findAllSaviorPieces(chessBoard, checkPiece.xposition, checkPiece.yposition, checkPiece.type, false);
	}
	
	// Method to check is someone can capture the threatening piece or protect the king from the piece that threat him
	public void canProtect(chessBoard chessBoard, int xposition, int yposition, int type, Piece checkPiece)
	{
		if (checkPiece.name == "Knight" || checkPiece.name == "Pawn")
			return;
		// Vertical up threat
		if (xposition == checkPiece.xposition && yposition > checkPiece.yposition)
			for (int y = checkPiece.yposition + 1; y < yposition; y++)
				findAllSaviorPieces(chessBoard, checkPiece.xposition, y, checkPiece.type, true);
		// Vertical down threat
		if (xposition == checkPiece.xposition && yposition < checkPiece.yposition)
			for (int y = checkPiece.yposition - 1; y > yposition; y--)
				findAllSaviorPieces(chessBoard, checkPiece.xposition, y, checkPiece.type, true);
		// Horizontal left threat
		if (xposition > checkPiece.xposition && yposition == checkPiece.yposition)
			for (int x = checkPiece.xposition + 1; x < xposition; x++)
				findAllSaviorPieces(chessBoard, x, checkPiece.yposition, checkPiece.type, true);
		// Horizontal right threat
		if (xposition < checkPiece.xposition && yposition == checkPiece.yposition)
			for (int x = checkPiece.xposition - 1; x > xposition; x--)
				findAllSaviorPieces(chessBoard, x, checkPiece.yposition, checkPiece.type, true);
		// Diagonal 1 \ up threat
		int y = checkPiece.yposition + 1;
		if (xposition > checkPiece.xposition && yposition > checkPiece.yposition)
			for (int x = checkPiece.xposition + 1; x < xposition && y < yposition; x++, y++)
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
		// Diagonal 1 \ down threat
		y = checkPiece.yposition - 1;
		if (xposition < checkPiece.xposition && yposition < checkPiece.yposition)
			for (int x = checkPiece.xposition - 1; x > xposition && y > yposition; x--, y--)
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);				
		// Diagonal 2 / up threat
		y = checkPiece.yposition + 1;
		if (xposition < checkPiece.xposition && yposition > checkPiece.yposition)
			for (int x = checkPiece.xposition - 1; x > xposition && y < yposition; x--, y++)
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
		// Diagonal 2 / down threat
		y = checkPiece.yposition - 1;
		if (xposition > checkPiece.xposition && yposition < checkPiece.yposition)
			for (int x = checkPiece.xposition + 1; x < xposition && y > yposition; x++, y--)
				findAllSaviorPieces(chessBoard, x, y, checkPiece.type, true);
	}
	
	public boolean isThisProtecting(chessBoard chessBoard, int xposition, int yposition, int type)
	{
		Piece checkPiece = chessBoard.checkPieces.get(0);
		// Vertical up threat
		if (chessBoard.getKing(type).xposition == checkPiece.xposition && chessBoard.getKing(type).yposition > checkPiece.yposition)
			if (xposition == chessBoard.getKing(type).xposition && yposition < chessBoard.getKing(type).yposition && yposition > checkPiece.yposition)
				return (true);
		// Vertical down threat
		if (chessBoard.getKing(type).xposition == checkPiece.xposition && chessBoard.getKing(type).yposition < checkPiece.yposition)
			if (xposition == chessBoard.getKing(type).xposition && yposition > chessBoard.getKing(type).yposition && yposition < checkPiece.yposition)
				return (true);
		// Horizontal left threat
		if (chessBoard.getKing(type).xposition > checkPiece.xposition && chessBoard.getKing(type).yposition == checkPiece.yposition)
			if (yposition == chessBoard.getKing(type).yposition && xposition < chessBoard.getKing(type).xposition && xposition > checkPiece.xposition)
				return (true);
		// Horizontal right threat
		if (chessBoard.getKing(type).xposition < checkPiece.xposition && chessBoard.getKing(type).yposition == checkPiece.yposition)
			if (yposition == chessBoard.getKing(type).yposition && xposition > chessBoard.getKing(type).xposition && xposition < checkPiece.xposition)
				return (true);
		// Diagonal 1 \ up threat
		int y = checkPiece.yposition;
		if (chessBoard.getKing(type).xposition > checkPiece.xposition && chessBoard.getKing(type).yposition > checkPiece.yposition)
			for (int x = checkPiece.xposition; x < chessBoard.getKing(type).xposition && y < chessBoard.getKing(type).yposition; x++, y++)
				if (xposition == x && yposition == y)
					return (true);
		// Diagonal 1 \ down threat
		y = checkPiece.yposition;
		if (chessBoard.getKing(type).xposition < checkPiece.xposition && chessBoard.getKing(type).yposition < checkPiece.yposition)
			for (int x = checkPiece.xposition; x > chessBoard.getKing(type).xposition && y > chessBoard.getKing(type).yposition; x--, y--)
				if (xposition == x && yposition == y)
					return (true);
		// Diagonal 2 / up threat
		y = checkPiece.yposition;
		if (chessBoard.getKing(type).xposition < checkPiece.xposition && chessBoard.getKing(type).yposition > checkPiece.yposition)
			for (int x = checkPiece.xposition; x > chessBoard.getKing(type).xposition && y < chessBoard.getKing(type).yposition; x--, y++)
				if (xposition == x && yposition == y)
					return (true);
		// Diagonal 2 / down threat
		y = checkPiece.yposition;
		if (chessBoard.getKing(type).xposition > checkPiece.xposition && chessBoard.getKing(type).yposition < checkPiece.yposition)
			for (int x = checkPiece.xposition; x < chessBoard.getKing(type).xposition && y > chessBoard.getKing(type).yposition; x++, y--)
				if (xposition == x && yposition == y)
					return (true);
		return (false);
	}
}
