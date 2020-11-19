package name.serhiibobrov.test_game.gameprocess

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import name.serhiibobrov.test_game.gameprocess.Player.State.*
import name.serhiibobrov.test_game.gameprocess.Player.State.BaseState.*

class Player(val rectangle: Rectangle) {

    companion object {
        const val BASIC_SPEED:Float = 200f
    }

    private var speed: Vector2 = Vector2()

    private var state: State = DOWN_RIGHT

    fun getState(): State = state

    fun moveRight() {
        println("move right")
        speed.x = BASIC_SPEED
    }

    fun moveLeft() {
        speed.x = -BASIC_SPEED
    }

    fun stopWalkToRight() {
        if (speed.x > 0) {
            speed.x = 0f
        }
    }

    fun stopWalkToLeft() {
        if (speed.x < 0) {
            speed.x = 0f
        }
    }

    fun jump() {
        if (speed.y == 0f) {
            speed.y = BASIC_SPEED * 5
        }
    }

    fun stopFalling() {
        if (speed.y < 0f) {
            speed.y = 0f
        }
    }

    fun startFalling() {
        speed.y -= 10
    }

    fun stopJumping() {
//        speed.y = 0f
    }

    fun update(passedTime: Float) {
        rectangle.x += passedTime * speed.x
        rectangle.y += passedTime * speed.y
    }

    fun getPosition(): Vector2 {
        return rectangle.getPosition(Vector2())
    }


    enum class State(val baseState: BaseState) {
        IDLE_LEFT(IDLE), IDLE_RIGHT(IDLE), WALK_LEFT(WALK), WALK_RIGHT(WALK),
        JUMP_LEFT(JUMP), JUMP_RIGHT(JUMP), JUMP_AND_WALK_LEFT(JUMP), JUMP_AND_WALK_RIGHT(JUMP),
        DOWN_LEFT(DOWN), DOWN_RIGHT(DOWN), DOWN_AND_WALK_LEFT(DOWN), DOWN_AND_WALK_RIGHT(DOWN);



        enum class BaseState {
            IDLE, JUMP, WALK, DOWN
        }
    }
}