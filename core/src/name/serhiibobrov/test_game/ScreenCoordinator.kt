package name.serhiibobrov.test_game

import name.serhiibobrov.test_game.gameprocess.GameProcessScreen
import name.serhiibobrov.test_game.menu.MenuGameScreen

class ScreenCoordinator() : GameScreen, ScreenSwitcher {

    val screens: Map<GameState, GameScreen> = mapOf(
            GameState.GAME_PROCESS to GameProcessScreen(this),
            GameState.MENU to MenuGameScreen(this)
    )
    private var currentScreen: GameScreen

    init {
        currentScreen = screens[GameState.MENU] ?: error("Unexpected error")
        currentScreen.activate()
    }

    override fun render() {
        currentScreen.render()
    }

    override fun dispose() {
        screens.forEach {
            it.value.dispose()
        }
    }

    override fun activate() {
        TODO("Not yet implemented")
    }

    override fun deactivate() {
        TODO("Not yet implemented")
    }

    enum class GameState {
        MENU, GAME_PROCESS, GAME_OVER
    }

    override fun switch(gameState: GameState) {
        val neededScreen = screens[gameState] ?: error("Unexpected error")

        currentScreen.deactivate()
        neededScreen.activate()

        currentScreen = neededScreen
    }
}