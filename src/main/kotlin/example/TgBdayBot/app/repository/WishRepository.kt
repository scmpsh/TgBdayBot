package example.TgBdayBot.app.repository

import example.TgBdayBot.app.entity.Wish
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WishRepository : JpaRepository<Wish, UUID>