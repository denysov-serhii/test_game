package name.serhiibobrov.test_game.gameprocess

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import name.serhiibobrov.test_game.*

class GameProcessScreen(private val screenSwitcher: ScreenSwitcher) : GameScreen {

    private val FRAME_COLS = 12
    private val FRAME_ROWS = 4

    private val batch: SpriteBatch = SpriteBatch()

    private val walkRightAnimation: Animation<TextureRegion>
    private val walkLeftAnimation: Animation<TextureRegion>
    private val characterSheet: Texture = Texture(Gdx.files.internal("pGGbv.png"))
    private val idleRightAnimation: Animation<TextureRegion>
    private val idleLeftAnimation: Animation<TextureRegion>

    private val playerFrame: PlayerFrame
    private val player: Player

    var stateTime:Float = 0f

    private val gameMap = GameMap()
    private val shapeRenderer: ShapeRenderer = ShapeRenderer()
    private val physicsManager: PhysicsManager

    init {
        val temp = TextureRegion
                .split(characterSheet, characterSheet!!.width / FRAME_COLS, characterSheet!!.height / FRAME_ROWS)
        val walkRightFrames = arrayOfNulls<TextureRegion>(FRAME_COLS) as Array<TextureRegion>
        val walkLeftFrames = arrayOfNulls<TextureRegion>(FRAME_COLS) as Array<TextureRegion>
        val idleFLeftFrames = arrayOfNulls<TextureRegion>(1) as Array<TextureRegion>
        val idleRightFrames = arrayOfNulls<TextureRegion>(1) as Array<TextureRegion>
        idleFLeftFrames[0] = temp[1][10]
        idleRightFrames[0] = temp[2][9]
        for (i in 0 until FRAME_COLS) {
            walkRightFrames[i] = temp[2][i]
            walkLeftFrames[i] = temp[1][i]
        }
        walkRightAnimation = Animation(0.04f, *walkRightFrames)
        walkLeftAnimation = Animation(0.04f, *walkLeftFrames)
        idleLeftAnimation = Animation(1f, *idleFLeftFrames)
        idleRightAnimation = Animation(1f, *idleRightFrames)
        playerFrame = PlayerFrame(
                walkRightAnimation,
                walkLeftAnimation,
                idleRightAnimation,
                idleLeftAnimation,
                idleRightAnimation,
                idleLeftAnimation
        )
        player = Player(Rectangle(50f, 300f, 95.1666666667f, 158.75f))

        physicsManager = PhysicsManager(player, gameMap)
    }

    override fun render() {
        stateTime += Gdx.graphics.deltaTime
        physicsManager.update()
        player.update(Gdx.graphics.deltaTime)


        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val playerPosition = player.getPosition()

        batch.begin()
        batch.draw(playerFrame.getCurrentFrame(player, stateTime), playerPosition.x, playerPosition.y);
        batch.end()

        shapeRenderer.color = Color.YELLOW
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.rect(playerPosition.x, playerPosition.y, player.rectangle.width, player.rectangle.height)
        shapeRenderer.end()


        for (thing in gameMap.getThings()) {
            shapeRenderer.color = thing.color
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
            val rect = thing.rectangle
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height)
            shapeRenderer.end()
        }
    }

    override fun dispose() {
        batch.dispose()
        characterSheet.dispose()
        shapeRenderer.dispose()
    }

    override fun activate() {
        Gdx.input.inputProcessor = PlayerKeyBoardController(player, screenSwitcher)
    }

    override fun deactivate() {
        Gdx.input.inputProcessor = null
    }
}