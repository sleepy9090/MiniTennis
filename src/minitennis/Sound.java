package minitennis;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("sound/ball.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("sound/gameover.wav"));
	public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("sound/back.wav"));
}
