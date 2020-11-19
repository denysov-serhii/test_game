package name.serhiibobrov.test_game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import name.serhiibobrov.test_game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1600;
		config.height = 850;

		new LwjglApplication(new Game(), config);
	}
}
