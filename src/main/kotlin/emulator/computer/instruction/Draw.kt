package com.natestott.emulator.computer.instruction

import com.natestott.emulator.computer.ScreenManager.screen
import com.natestott.emulator.computer.memory.register.RManager.r
import com.natestott.emulator.logger.LoggerManager.logger
import com.natestott.emulator.logger.Logger.Level

class Draw(
    nibbles: ByteArray
) : Instruction(nibbles) {
    override fun performOperation() {
        val rxIndex = nibbles[0].toInt()
        val row = nibbles[1]
        val col = nibbles[2]

        val rx = r[rxIndex]
        val asciiValue = rx.read()[0].toInt()

        if (asciiValue > 0x7F) {
            throw IllegalArgumentException("ASCII value in rX is greater than 0x7F.")
        }

        logger.log(Level.INFO, "Drawing character '${asciiValue.toChar()}' at row $row, column $col")

        screen.draw(asciiValue.toByte(), row, col)
    }
}
