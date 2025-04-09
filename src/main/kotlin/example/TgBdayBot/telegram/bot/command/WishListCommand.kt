package example.TgBdayBot.telegram.bot.command

import example.TgBdayBot.app.service.WishListService
import example.TgBdayBot.telegram.bot.command.type.CommandType
import example.TgBdayBot.telegram.bot.handler.type.HandlerType
import example.TgBdayBot.telegram.utils.MessageHelper
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class WishListCommand(
    private val wishListService: WishListService
) : BotCommand(CommandType.WISHLIST.commandName, "") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, array: Array<out String>) {
        val callback = HandlerType.WISHLIST.handlerName
        val wishList = wishListService.showWishlist()
        val listOfPairs = wishList.stream().map { "$callback|" + it.id to it.item }.toList()
        absSender.execute(
            MessageHelper.createMessageWithInlineButtons(
                chat.id.toString(),
                "Выбери подарок:",
                listOfPairs.chunked(2)
            )
        )
    }
}