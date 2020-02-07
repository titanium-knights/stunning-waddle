package io.github.titanium_knights.stunning_waddle

import com.qualcomm.robotcore.eventloop.opmode.OpMode

open class EventOpMode(private val initFunc: EventOpMode.() -> Unit): OpMode() {
    private val preLoopHooks = mutableListOf<Hook>()
    private val startHooks = mutableListOf<Hook>()
    private val loopHooks = mutableListOf<Hook>()
    private val stopHooks = mutableListOf<Hook>()

    override fun init() {
        initFunc()
    }

    override fun init_loop() {
        preLoopHooks.forEach { it() }
    }

    override fun start() {
        startHooks.forEach { it() }
    }

    override fun loop() {
        loopHooks.forEach { it() }
    }

    override fun stop() {
        stopHooks.forEach { it() }
    }

    fun registerPreLoopHook(hook: Hook) {
        preLoopHooks.add(hook)
    }

    fun registerStartHook(hook: Hook) {
        startHooks.add(hook)
    }

    fun registerLoopHook(hook: Hook) {
        loopHooks.add(hook)
    }

    fun registerStopHook(hook: Hook) {
        stopHooks.add(hook)
    }
}