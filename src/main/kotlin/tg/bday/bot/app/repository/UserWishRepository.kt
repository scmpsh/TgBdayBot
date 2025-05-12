package tg.bday.bot.app.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import tg.bday.bot.app.entity.UserWish
import java.util.*

@Repository
interface UserWishRepository : JpaRepository<UserWish, UUID> {
    @Query(
        "FROM UserWish " +
                "WHERE user.userId = ?1"
    )
    fun findByUserId(userId: Long): Optional<UserWish>
}