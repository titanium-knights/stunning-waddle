# stunning-waddle
*Tele-op programming, reinvented*

[Installation instructions](installation.md)

## What is stunning-waddle?
Stunning Waddle is a simple Kotlin library that simplifies FTC tele-op programming with a new event-based model:
```kotlin
@TeleOp(name = "My Great Op Mode")
class MyGreatOpMode: EventOpMode({
    // This lambda runs during `init()`

    val servo = hardwareMap[Servo::class.java, "my_servo"]!!    
    
    doWhen(
        gamepad1::x.pressed then { servo.position = 0.0 },
        gamepad1::b.pressed then { servo.position = 1.0 }
    )
})
```

### Flexibility
Stunning Waddle works in a wide variety of situations:
```kotlin
// Move a motor if Y is pressed and stop if it isn't
doWhen(gamepad1::y.pressed, {
    motor.power = 1.0
}, otherwise = {
    motor.power = 0.0
})

// Add some data when the distance sensor detects a wall
doWhen({
    // This lambda runs during loop() now
    distanceSensor.getDistance(DistanceUnit.INCH) <= 50
}) {
    telemetry.addData("Dpad Right", "Pressed")
}
```

If needed, you can specify your own custom code to run during **loop()**:
```kotlin
class MyTeleOpMode: EventOpMode({
    val motor = hardwareMap[DcMotor::class, "my_motor"]

    registerLoopHook {
        motor.power = gamepad1.left_stick_y.toDouble()
    }
})
```

### Push buttons, made easy
Most push button implementations take multiple lines of code. Stunning Waddle condenses it to just one line:
```kotlin
val myButton = register(Button(gamepad1::dpad_up))
doWhen(myButton.pushed) {
    telemetry.speak("Hello")
}

// Or, use makeButton:
doWhen(makeButton(gamepad1::dpad_down).released) {
    telemetry.speak("Goodbye")
}
```

Need a toggle switch? Simply use `makeToggleButton`:
```kotlin
val genericButton = makeToggleButton(gamepad1)

doWhen({ genericButton.selection }) {
    telemetry.addData("Generic Mode", "On")
}
```