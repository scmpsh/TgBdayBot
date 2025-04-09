package example.TgBdayBot.telegram.bot.handler

import example.TgBdayBot.telegram.bot.handler.type.HandlerType
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.bots.AbsSender

interface CallbackHandler {

    val name: HandlerType

    fun processCallbackData(absSender: AbsSender, callbackQuery: CallbackQuery, arguments: List<String>)
}