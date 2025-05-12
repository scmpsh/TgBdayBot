package example.TgBdayBot.app.service

import example.TgBdayBot.app.entity.UserWish
import example.TgBdayBot.app.entity.Users
import example.TgBdayBot.app.entity.Wish
import example.TgBdayBot.app.repository.WishRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class WishService(
    private val repository: WishRepository,
    private val userWishService: UserWishService,
    private val userService: UsersService
) {
    @Transactional
    fun bookWish(userId: Long, wishId: UUID): String {
        userWishService.checkIfUserBookWish(userId)
        val wishOpt = repository.findById(wishId)
        val wish = wishOpt.orElseThrow { NoSuchElementException(String.format("Wish not found by id%s", wishId)) }
        if (wish.numberOfItems == 0) {
            throw NoSuchElementException(
                String.format(
                    "Данная позиция закончилась %s, выбери что-то новое из списка /wishlist :)", wish.item
                )
            )
        }
        wish.numberOfItems -= 1
        val user = userService.save(Users(userId))
        userWishService.save(UserWish(user, wish))
        return repository.save(wish).item
    }

    fun showWishlist(userId: Long): List<Wish> {
        userWishService.checkIfUserBookWish(userId)
        return repository.findAll().stream().filter { it.numberOfItems > 0 }.toList()
    }
}

