package components

import kotlinx.css.Align
import kotlinx.css.Display
import kotlinx.css.JustifyContent
import kotlinx.css.TextAlign
import kotlinx.css.alignItems
import kotlinx.css.display
import kotlinx.css.fontSize
import kotlinx.css.justifyContent
import kotlinx.css.margin
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.width
import react.RBuilder
import react.RProps
import react.ReactElement
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledSpan

external interface HealthCounterProps : RProps {
    var hp: Int
    var updateHp: (delta: Int) -> Unit
}

val healthCounter = functionalComponent<HealthCounterProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }
        // TODO: color player zone based on desk color

        healthButton {
            label = "-"
            onClick = { props.updateHp(-1) }
        }

        // TODO: color player zone when hp is ≤0
        styledSpan {
            css {
                fontSize = 96.px
                margin = "0 16px"
                width = 144.px
                textAlign = TextAlign.center
            }
            +"${props.hp}"
        }

        healthButton {
            label = "+"
            onClick = { props.updateHp(1) }
        }
    }
}

fun RBuilder.healthCounter(handler: HealthCounterProps.() -> Unit): ReactElement {
    return child(healthCounter) {
        this.attrs(handler)
    }
}
