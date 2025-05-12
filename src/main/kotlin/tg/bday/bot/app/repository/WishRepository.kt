package tg.bday.bot.app.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tg.bday.bot.app.entity.Wish
import java.util.*

@Repository
interface WishRepository : JpaRepository<Wish, UUID>