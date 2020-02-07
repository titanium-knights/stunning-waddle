package io.github.titanium_knights.stunning_waddle

import java.lang.IllegalArgumentException

val GamepadButton.pressed: Condition get() = { get() }
val GamepadButton.released: Condition get() = { !get() }

class Button(private val gamepadButton: GamepadButton): Registrable {
    private var previousState = false
    private var currentState = false

    fun update() {
        previousState = currentState
        currentState = gamepadButton.get()
    }

    val isPushed get() = !previousState && currentState
    val isReleased get() = previousState && !currentState

    val pushed = { this.isPushed }
    val released = { this.isReleased }

    override fun register(eventOpMode: EventOpMode) {
        eventOpMode.registerLoopHook { update() }
    }
}

fun EventOpMode.makeButton(gamepadButton: GamepadButton): Button {
    return register(Button(gamepadButton))
}

class ButtonCounter(decrement: GamepadButton?, increment: GamepadButton?, var count: Int = 0): Registrable {
    private val decrement = decrement?.let { Button(it) }
    private val increment = increment?.let { Button(it) }

    constructor(increment: GamepadButton): this(null, increment)

    fun update() {
        decrement?.update()
        increment?.update()

        if (decrement?.isPushed == true) {
            count--
        }

        if (increment?.isPushed == true) {
            count++
        }
    }

    override fun register(eventOpMode: EventOpMode) {
        eventOpMode.registerLoopHook { update() }
    }
}

class ButtonSelector<T>(val counter: ButtonCounter, private val selections: List<T>) {
    var selection: T
        get() = selections[((counter.count % selections.size) + selections.size) % selections.size]
        set(value) {
            val index = selections.indexOf(value)
            if (index < 1) {
                throw IllegalArgumentException("ButtonSelector.selection must be set to a value in ButtonSelector.selections")
            }
            counter.count = index
        }
}

fun EventOpMode.makeToggleButton(gamepadButton: GamepadButton, current: Boolean = false): ButtonSelector<Boolean> {
    return ButtonSelector(register(ButtonCounter(gamepadButton)), listOf(current, !current))
}