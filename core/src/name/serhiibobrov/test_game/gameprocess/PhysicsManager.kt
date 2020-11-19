package name.serhiibobrov.test_game.gameprocess

import com.badlogic.gdx.math.Rectangle
import name.serhiibobrov.test_game.gameprocess.PhysicsManager.CollisionType.*

class PhysicsManager(private val player: Player, private val gameMap: GameMap) {
    fun update() {
        var collisionType = NOT_COLLISION
        for (thing in gameMap.getThings()) {
            if (thing.rectangle.overlaps(player.rectangle)) {

                val thingRect = thing.rectangle
                val playerRect = player.rectangle

                if (isThingOnTheRight(playerRect, thingRect) && hasYCollision(playerRect, thingRect)) {
                    collisionType = RIGHT
                    println("collisionType: $collisionType")

                } else if (isThingOnTheLeft(playerRect, thingRect) && hasYCollision(playerRect, thingRect)) {
                    collisionType = LEFT
                    println("collisionType: $collisionType")

                } else if (playerRect.y > thingRect.y) {
//                    println("down collision")
                    collisionType = DOWN
                } else {
                    println("top collision")
                    collisionType = TOP
                }
            }
        }

        when (collisionType) {
            DOWN -> player.stopFalling()
            TOP -> player.stopJumping()
            RIGHT -> player.stopWalkToRight()
            LEFT -> player.stopWalkToLeft()
            NOT_COLLISION -> player.startFalling()
        }
    }

    private fun isThingOnTheRight(playerRect: Rectangle, thingRect: Rectangle) =
            (playerRect.x + playerRect.width) in thingRect.x..(thingRect.x + thingRect.width/2)

    private fun hasYCollision(playerRect: Rectangle, thingRect: Rectangle) = false
//            thingRect.y in playerRect.y..(playerRect.y + playerRect.height)

    private fun isThingOnTheLeft(playerRect: Rectangle, thingRect: Rectangle) =
            playerRect.x in (thingRect.x + thingRect.width/2)..(thingRect.x + thingRect.width)

    enum class CollisionType {
        NOT_COLLISION, LEFT, RIGHT, TOP, DOWN
    }
}