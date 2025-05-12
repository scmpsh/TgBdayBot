package tg.bday.bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TgBdayBotApplication

fun main(args: Array<String>) {
    runApplication<TgBdayBotApplication>(*args)
}
