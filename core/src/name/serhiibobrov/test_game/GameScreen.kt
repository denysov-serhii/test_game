package name.serhiibobrov.test_game

interface GameScreen {
    fun render()
    fun dispose()
    fun activate()
    fun deactivate()
}