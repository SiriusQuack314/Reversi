import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Timer {

	static public Timeline timeline;
	
	
	@SuppressWarnings("unchecked")
	static public void start (Game game) {
		Game.resetTimer();
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(
	                new KeyFrame(Duration.seconds(1),
	                  new EventHandler() {
						@Override
						public void handle(Event event) {
							if(game.isBlacksTurn == false)
								game.whiteTime -= 1000;
							else 
								game.blackTime -= 1000;
							
							GameUI.LbPlayer1Time.setText("" + (Game.whiteTime/(1000*60))%60 + "::" + (Game.whiteTime/1000)%60);
							GameUI.LbPlayer2Time.setText("" + (Game.blackTime/(1000*60))%60 + "::" + (Game.blackTime/1000)%60);
							if(game.whiteTime <= 0 || game.blackTime <= 0) {
								timeline.stop();
								try {
									Game.quitGame();
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}
	                }));
		timeline.playFromStart();
	}
	
	static public void stop () {
		timeline.stop();
	}
}
