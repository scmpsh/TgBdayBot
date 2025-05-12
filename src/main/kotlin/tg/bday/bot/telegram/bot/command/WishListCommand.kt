package tg.bday.bot.telegram.bot.command

import tg.bday.bot.app.service.WishService
import tg.bday.bot.telegram.bot.command.type.CommandType
import tg.bday.bot.telegram.bot.handler.type.HandlerType
import tg.bday.bot.telegram.utils.MessageHelper
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

            val wishListString = buildString {
                wishList.forEachIndexed { index, wish ->
                    appendLine("${index + 1}) ${wish.item} Осталось: ${wish.numberOfItems}")
                }
            }

            val listOfPairs = wishList.mapIndexed { index, wish ->
                String.format("$callback|%s", wish.id) to "${index + 1}"
            }.toList()

            absSender.execute(
                MessageHelper.createMessageWithInlineButtons(
                    chat.id.toString(),
                    "Выбери подарок:\n" +
                            wishListString,
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