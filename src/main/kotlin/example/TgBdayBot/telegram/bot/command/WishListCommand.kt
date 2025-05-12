package example.TgBdayBot.telegram.bot.command

import example.TgBdayBot.app.service.WishService
import example.TgBdayBot.telegram.bot.command.type.CommandType
import example.TgBdayBot.telegram.bot.handler.type.HandlerType
import example.TgBdayBot.telegram.utils.MessageHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class WishListCommand(
    private val wishService: WishService
) : BotCommand(CommandType.WISHLIST.commandName, "") {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun execute(absSender: AbsSender, user: User, chat: Chat, array: Array<out String>) {
        val callback = HandlerType.WISHLIST.handlerName
        try {
            val wishList = wishService.showWishlist(user.id)
            val listOfPairs =
                wishList.stream().map {
                    String.format("$callback|%s", it.id) to String.format(
                        "%s. Осталось %s шт.",
                        it.item,
                        it.numberOfItems
                    )
                }.toList()
            absSender.execute(
                MessageHelper.createMessageWithInlineButtons(
                    chat.id.toString(),
                    "Выбери подарок:",
                    listOfPairs.chunked(1)
                )
            )
        } catch (e: RuntimeException) {
            val chatId = chat.id.toString()
            val errorMsg = "❌ Oops, something went wrong:\n${e.localizedMessage}"
            absSender.execute(SendMessage(chatId, errorMsg))
            logger.warn(e.message)
        }
    }
}