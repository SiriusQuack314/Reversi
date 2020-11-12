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
	static public void start () {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(
	                new KeyFrame(Duration.seconds(1),
	                  new EventHandler() {
						@Override
						public void handle(Event event) {
							if(Game.isBlacksTurn == false)
								Game.whiteTime -= 1000;
							else 
								Game.blackTime -= 1000;
							
							GameUI.LbPlayer2Time.setText("" + (Game.whiteTime/(1000*60))%60 + ":" + (Game.whiteTime/1000)%60);
							GameUI.LbPlayer1Time.setText("" + (Game.blackTime/(1000*60))%60 + ":" + (Game.blackTime/1000)%60);
							if(Game.whiteTime <= 0 || Game.blackTime <= 0) {
								timeline.stop();
								try {
									GameUI.timeOut();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}
	                }));
		timeline.playFromStart();
	}
	
}
