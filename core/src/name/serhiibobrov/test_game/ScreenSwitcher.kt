package name.serhiibobrov.test_game

interface ScreenSwitcher {
    fun switch(gameState: ScreenCoordinator.GameState)
}