package example.TgBdayBot.telegram.bot.command

import example.TgBdayBot.telegram.bot.command.type.CommandType
import example.TgBdayBot.telegram.utils.MessageHelper
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class StartCommand : BotCommand(CommandType.START.commandName, "") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        absSender.execute(
            MessageHelper.createMessage(
                chat.id.toString(),
                String.format("Добро пожаловать!\n%s", MessageHelper.helpText())
            )
        )
    }
}