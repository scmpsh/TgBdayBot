package example.TgBdayBot.telegram.config

import example.TgBdayBot.telegram.bot.WishlistBot
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Configuration
class TgBotConfig {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun telegramBotsApi(bot: WishlistBot): TelegramBotsApi {
        logger.info(String.format("Telegram Bot has been initialized✅"))
        return TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(bot)
        }
    }

}
