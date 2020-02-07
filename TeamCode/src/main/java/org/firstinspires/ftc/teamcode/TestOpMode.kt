package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import io.github.titanium_knights.stunning_waddle.*

@TeleOp(name = "Test Op Mode", group = "A")
class TestOpMode: EventOpMode({
    doWhen(
            gamepad1::x.released then { telemetry.addLine("It work") },
            otherwise then { telemetry.addLine("it no work") }
    )

    doWhen(gamepad1::y.pressed) {
        telemetry.addLine("y press")
    }

    doWhen({ gamepad1.a }, {
        telemetry.addLine("a press")
    }, otherwise = {
        telemetry.addLine("a not press")
    })

    var thing = false

    doWhen(register(Button(gamepad1::dpad_left)).pushed, {
        thing = !thing
    })

    doWhen(makeButton(gamepad1::dpad_right).released, {
        thing = !thing
    })

    doWhen({ thing }) {
        telemetry.addLine("thing")
    }

    val counter = register(ButtonCounter(gamepad1::dpad_down, gamepad1::dpad_up, 1))
    val selector = ButtonSelector(counter, listOf("A", "B", "C", "D"))

    registerLoopHook {
        telemetry.addLine("Count: ${counter.count}")
        telemetry.addLine("Selected: ${selector.selection}")
    }
})

@TeleOp(name = "Op Mode 2", group = "B")
class TestOpMode2: OpMode() {
    override fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}