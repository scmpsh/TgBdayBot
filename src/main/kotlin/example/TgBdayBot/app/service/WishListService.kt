package example.TgBdayBot.app.service

import example.TgBdayBot.app.entity.Wish
import example.TgBdayBot.app.repository.WishRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class WishListService(
    private val repository: WishRepository
) {

    @Transactional
    fun bookWish(userId: Long, wishId: UUID): String {
        val wishOpt = repository.findById(wishId)
        val wish = wishOpt.orElseThrow { NoSuchElementException(String.format("Wish not found by id%s", wishId)) }
        wish.isBooked = true
        wish.userId = userId
        return repository.save(wish).item
    }

    fun showWishlist(): List<Wish> = repository.findAll()
}