package example.TgBdayBot.telegram.utils

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

object MessageHelper {

    fun createMessage(chatId: String, text: String) =
        SendMessage(chatId, text)
            .apply { enableMarkdown(true) }
            .apply { disableWebPagePreview() }

    fun helpText(): String = """
        🤖 Wishlist Bot Commands:
        /wishlist – show wishlist
    """.trimIndent()

    fun createMessageWithInlineButtons(chatId: String, text: String, inlineButtons: List<List<Pair<String, String>>>) =
        createMessage(chatId, text)
            .apply {
                replyMarkup = getInlineKeyboard(inlineButtons)
            }

    fun getInlineKeyboard(allButtons: List<List<Pair<String, String>>>): InlineKeyboardMarkup =
        InlineKeyboardMarkup().apply {
            keyboard = allButtons.map { rowButtons ->
                rowButtons.map { (data, buttonText) ->
                    InlineKeyboardButton().apply {
                        text = buttonText
                        callbackData = data
                    }
                }
            }
        }
}