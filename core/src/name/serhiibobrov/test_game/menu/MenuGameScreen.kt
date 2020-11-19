package name.serhiibobrov.test_game.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import name.serhiibobrov.test_game.GameScreen
import name.serhiibobrov.test_game.ScreenCoordinator
import name.serhiibobrov.test_game.ScreenSwitcher
import kotlin.system.exitProcess


class MenuGameScreen(private val screenSwitcher: ScreenSwitcher) : GameScreen {

    private val stage: Stage = Stage()
    private val startGameBtn: TextButton
    private val exitBtn: TextButton

    private val startGameListener: ClickListener
    private val exitGameListener: ClickListener

    init {
        val skin = Skin(Gdx.files.internal("neon-ui.json"))

        startGameBtn = TextButton("Start game", skin)
        exitBtn = TextButton("Exit", skin)

        startGameListener = object :  ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                screenSwitcher.switch(ScreenCoordinator.GameState.GAME_PROCESS)
            }
        }

        exitGameListener = object :  ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                exitProcess(0)
            }
        }

        val menuTable = Table()
        menuTable.add(startGameBtn)
        menuTable.row()
        menuTable.add(exitBtn)
        menuTable.setFillParent(true)

        stage.addActor(menuTable)
    }


    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }

    override fun activate() {
        Gdx.input.inputProcessor = stage
        startGameBtn.addListener(startGameListener)
        exitBtn.addListener(exitGameListener)
    }

    override fun deactivate() {
        Gdx.input.inputProcessor = null
        exitBtn.removeListener(exitGameListener)
        startGameBtn.removeListener(startGameListener)
    }
}