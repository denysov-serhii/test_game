package name.serhiibobrov.test_game;

import com.badlogic.gdx.ApplicationAdapter;

open class Game : ApplicationAdapter() {

	lateinit var screenCoordinator: ScreenCoordinator

	override fun create () {
		screenCoordinator = ScreenCoordinator()
	}

	override fun render () {
		screenCoordinator.render()
	}

	override fun  dispose () {
		screenCoordinator.dispose()
	}
}
