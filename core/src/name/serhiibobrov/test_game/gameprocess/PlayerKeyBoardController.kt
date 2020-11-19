package name.serhiibobrov.test_game.gameprocess

import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.InputAdapter
import name.serhiibobrov.test_game.ScreenCoordinator
import name.serhiibobrov.test_game.ScreenSwitcher

class PlayerKeyBoardController(private val player: Player, private val screenSwitcher: ScreenSwitcher) : InputAdapter() {

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            RIGHT -> player.moveRight()
            LEFT -> player.moveLeft()
            UP -> player.jump()
            ESCAPE -> screenSwitcher.switch(ScreenCoordinator.GameState.MENU)
        }

        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            RIGHT -> player.stopWalkToRight()
            LEFT -> player.stopWalkToLeft()
        }

        return true
    }
}