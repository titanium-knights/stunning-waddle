package io.github.titanium_knights.stunning_waddle

interface Registrable {
    fun register(eventOpMode: EventOpMode)
}

fun <TRegistrable> EventOpMode.register(registrable: TRegistrable): TRegistrable where TRegistrable: Registrable {
    registrable.register(this)
    return registrable
}