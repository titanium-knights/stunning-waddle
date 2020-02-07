package io.github.titanium_knights.stunning_waddle

fun EventOpMode.doWhen(condition: Condition, hook: Hook, otherwise: Hook = {}) {
    registerLoopHook { if (condition()) hook() else otherwise() }
}

fun EventOpMode.doWhen(condition: Condition, hook: Hook) {
    doWhen(condition, hook, otherwise = {})
}

val otherwise = null

infix fun Condition?.then(hook: Hook): Pair<Condition?, Hook> {
    return this to hook
}

fun EventOpMode.doWhen(vararg conditions: Pair<Condition?, Hook>) {
    registerLoopHook {
        conditions.firstOrNull { (condition, _) -> condition == null || condition() }?.let { it.second() }
    }
}