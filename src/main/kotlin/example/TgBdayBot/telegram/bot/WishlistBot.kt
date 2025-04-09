package example.TgBdayBot.telegram.bot

import example.TgBdayBot.telegram.bot.handler.CallbackHandler
import example.TgBdayBot.telegram.utils.MessageHelper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class WishlistBot(
    @Value("\${telegram.bot.name}") private val username: String,
    @Value("\${telegram.bot.token}") private val token: String,
    commands: Set<BotCommand>,
    callbackHandlers: Set<CallbackHandler>
) : TelegramLongPollingCommandBot(token) {

    private lateinit var handlerMapping: Map<String, CallbackHandler>

    init {
        registerAll(*commands.toTypedArray())
        handlerMapping = callbackHandlers.associateBy { it.name.handlerName }
    }

    override fun getBotUsername(): String = username

    override fun processNonCommandUpdate(update: Update?) {
        if (update != null) {
            if (update.hasCallbackQuery()) {
                val callbackQuery = update.callbackQuery
                val callbackData = callbackQuery.data

                val callbackQueryId = callbackQuery.id
                execute(AnswerCallbackQuery(callbackQueryId))

                val callbackArguments = callbackData.split("|")
                val callbackHandlerName = callbackArguments.first()

                handlerMapping.getValue(callbackHandlerName)
                    .processCallbackData(
                        this,
                        callbackQuery,
                        callbackArguments.subList(1, callbackArguments.size)
                    )
            } else if (update.hasMessage()) {
                val chatId = update.message.chatId.toString()
                if (update.message.hasText()) {
                    execute(
                        MessageHelper.createMessage(
                            chatId,
                            String.format(
                                "Вы написали: *${update.message.text}*\n но нужно выбрать что-то из этого!!!\n%s",
                                MessageHelper.helpText()
                            )
                        )
                    )
                }
            }
        }
    }
}
