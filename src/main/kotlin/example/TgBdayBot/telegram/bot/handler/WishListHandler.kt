package example.TgBdayBot.telegram.bot.handler

import example.TgBdayBot.app.service.WishListService
import example.TgBdayBot.telegram.bot.handler.type.HandlerType
import example.TgBdayBot.telegram.utils.MessageHelper
import example.TgBdayBot.telegram.utils.MessageHelper.getInlineKeyboard
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*

@Component
class WishListHandler(
    private val wishListService: WishListService
) : CallbackHandler {
    override val name: HandlerType = HandlerType.WISHLIST

    override fun processCallbackData(
        absSender: AbsSender,
        callbackQuery: CallbackQuery,
        arguments: List<String>
    ) {

        val chatId = callbackQuery.message.chatId.toString()
        val userId = callbackQuery.from.id

        absSender.execute(
            EditMessageReplyMarkup(
                chatId,
                callbackQuery.message.messageId,
                callbackQuery.inlineMessageId,
                getInlineKeyboard(emptyList())
            )
        )

        val wish = wishListService.bookWish(userId, UUID.fromString(arguments[0]))
        absSender.execute(MessageHelper.createMessage(chatId, String.format("Вы забронировали: %s", wish)))
    }
}