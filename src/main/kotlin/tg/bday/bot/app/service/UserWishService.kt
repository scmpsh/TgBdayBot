package tg.bday.bot.app.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import tg.bday.bot.app.repository.UserWishRepository

@Service
class UserWishService(
    private val repository: UserWishRepository
) {

    @Transactional
    fun save(userWish: tg.bday.bot.app.entity.UserWish): tg.bday.bot.app.entity.UserWish = repository.save(userWish)

    @Transactional
    fun checkIfUserBookWish(userId: Long) {
        val userWishOpt = repository.findByUserId(userId)
        if (userWishOpt.isPresent) {
            val wish = userWishOpt.get().wish
            throw IllegalArgumentException(
                String.format(
                    "Ты уже выбрал: %s. Больше нельзя /wishlist :)", wish.item
                )
            )
        }
    }
}