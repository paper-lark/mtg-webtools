package components

import kotlinx.css.Cursor
import kotlinx.css.cursor
import kotlinx.css.fontSize
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.ReactElement
import react.child
import react.functionalComponent
import styled.css
import styled.styledSpan

external interface HealthButtonProps : RProps {
    var label: String
    var onClick: () -> Unit
}

val healthButton = functionalComponent<HealthButtonProps> { props ->
    styledSpan {
        css {
            fontSize = 96.px
            cursor = Cursor.pointer
        }
        +props.label
        attrs {
            onClickFunction = { props.onClick() }
        }
    }
}

fun RBuilder.healthButton(handler: HealthButtonProps.() -> Unit): ReactElement {
    return child(healthButton) {
        this.attrs(handler)
    }
}
