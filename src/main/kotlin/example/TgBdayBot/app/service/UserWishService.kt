package example.TgBdayBot.app.service

import example.TgBdayBot.app.entity.UserWish
import example.TgBdayBot.app.repository.UserWishRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserWishService(
    private val repository: UserWishRepository
) {

    @Transactional
    fun save(userWish: UserWish): UserWish = repository.save(userWish)

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