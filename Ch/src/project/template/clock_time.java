package project.template;

import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class clock_time {
	public int whiteclock_time = 900;
	public int blackclock_time = 900;
	public int playerTurn = 0;
	public boolean timeIsOver = false;
	private chessBoard chessBoard;
	
	public clock_time(chessBoard _chessBoard) {
		chessBoard = _chessBoard;
	}
	
	public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if (playerTurn == 1 && !timeIsOver && !chessBoard.checkmate && !chessBoard.stalemate)
			{
				whiteclock_time -= 1;
				chessBoard.getTopBarPanel().whitePlayerclock_time.setText("White clock_time: " + TimeUnit.SECONDS.toMinutes(whiteclock_time) + ":" + (whiteclock_time % 60));
			}
			else if (playerTurn == 2 && !timeIsOver)
			{
				blackclock_time -= 1;
				chessBoard.getTopBarPanel().blackPlayerclock_time.setText("Black clock_time: " + TimeUnit.SECONDS.toMinutes(blackclock_time) + ":" + (blackclock_time % 60));
			}
			if (!timeIsOver && (whiteclock_time == 0 || blackclock_time == 0))
			{
				//chessBoard.clock_timeOver(playerTurn);
				timeIsOver = true;
			}
		}
	}));
}