package name.serhiibobrov.test_game.gameprocess

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Rectangle

class GameMap {

    private val things: MutableList<Thing> = ArrayList()

    constructor() {
        things.add(Thing(Rectangle(5f, 10f, 1650f, 10f), Color.BLUE))
        things.add(Thing(Rectangle(500f, 100f, 500f, 10f), Color.LIGHT_GRAY))
        things.add(Thing(Rectangle(10f, 500f, 500f, 10f), Color.LIGHT_GRAY))
        things.add(Thing(Rectangle(1000f, 500f, 500f, 10f), Color.LIGHT_GRAY))
    }

    fun getThings(): List<Thing> = things
}