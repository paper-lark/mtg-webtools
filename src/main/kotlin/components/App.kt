package components

import kotlinx.css.Align
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.JustifyContent
import kotlinx.css.alignItems
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.flexDirection
import kotlinx.css.flexGrow
import kotlinx.css.justifyContent
import react.RBuilder
import react.RProps
import react.ReactElement
import react.child
import react.functionalComponent
import react.useCallback
import react.useState
import styled.css
import styled.styledDiv
import utils.ThemeColor
import kotlin.math.max
import kotlin.math.min

val app = functionalComponent<RProps> {
    val (playersHP, setPlayersHP) = useState(listOf(29, 32))
    val updatePlayerHealth = useCallback({ playerIndex: Int, deltaHp: Int ->
        setPlayersHP(
            playersHP.mapIndexed { i, hp ->
                if (i == playerIndex) max(min(hp + deltaHp, 99), -99) else hp
            }
        )
    }, arrayOf(playersHP))

        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                alignItems = Align.stretch
                justifyContent = JustifyContent.spaceEvenly
                flexGrow = 1.0
                backgroundColor = ThemeColor.background
                color = ThemeColor.foreground
            }

            // TODO: support 3+ players
            // TODO: make player cards
            playersHP.mapIndexed { i, health ->
                healthCounter {
                    hp = health
                    updateHp = { updatePlayerHealth(i, it) }
                }
            }
        }
    }

    fun RBuilder.app(handler: RProps.() -> Unit): ReactElement {
        return child(app) {
            this.attrs(handler)
        }
    }
    