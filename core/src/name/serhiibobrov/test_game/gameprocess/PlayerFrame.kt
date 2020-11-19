package name.serhiibobrov.test_game.gameprocess

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import name.serhiibobrov.test_game.gameprocess.Player.State
import name.serhiibobrov.test_game.gameprocess.Player.State.*
import java.util.*

class PlayerFrame(
        walkRightAnimation: Animation<TextureRegion>,
        walkLeftAnimation: Animation<TextureRegion>,
        idleRightAnimation: Animation<TextureRegion>,
        idleLeftAnimation: Animation<TextureRegion>,
        defaultRightAnimation: Animation<TextureRegion>,
        defaultLeftAnimation: Animation<TextureRegion>) {

    private val animationMap: MutableMap<State, Animation<TextureRegion>> = EnumMap(State::class.java)

    init {
        animationMap[IDLE_RIGHT] = idleRightAnimation
        animationMap[IDLE_LEFT] = idleLeftAnimation
        animationMap[WALK_LEFT] = walkLeftAnimation
        animationMap[WALK_RIGHT] = walkRightAnimation
        animationMap[JUMP_RIGHT] = defaultRightAnimation
        animationMap[JUMP_LEFT] = defaultLeftAnimation
        animationMap[DOWN_RIGHT] = defaultRightAnimation
        animationMap[DOWN_LEFT] = defaultLeftAnimation
        animationMap[JUMP_AND_WALK_LEFT] = defaultLeftAnimation
        animationMap[JUMP_AND_WALK_RIGHT] = defaultRightAnimation
        animationMap[DOWN_AND_WALK_RIGHT] = defaultRightAnimation
        animationMap[DOWN_AND_WALK_LEFT] = defaultRightAnimation
    }

    fun getCurrentFrame(player: Player, stateTime:Float): TextureRegion? {
        return animationMap[player.getState()]
                ?.getKeyFrame(stateTime, true);
    }
}