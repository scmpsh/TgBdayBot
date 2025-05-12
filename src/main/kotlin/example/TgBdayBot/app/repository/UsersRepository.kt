package example.TgBdayBot.app.repository

import example.TgBdayBot.app.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository : JpaRepository<Users, UUID> {
    fun findByUserId(userId: Long): Optional<Users>
}