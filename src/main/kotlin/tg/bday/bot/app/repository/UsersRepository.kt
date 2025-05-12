package tg.bday.bot.app.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tg.bday.bot.app.entity.Users
import java.util.*

@Repository
interface UsersRepository : JpaRepository<Users, UUID> {
    fun findByUserId(userId: Long): Optional<Users>
}