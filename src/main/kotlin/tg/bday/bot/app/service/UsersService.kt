package tg.bday.bot.app.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import tg.bday.bot.app.entity.Users
import tg.bday.bot.app.repository.UsersRepository

@Service
class UsersService(
    private val repository: UsersRepository
) {

    @Transactional
    fun save(users: Users): Users {
        val dbUsersOpt = repository.findByUserId(users.userId)
        if (dbUsersOpt.isPresent) {
            return dbUsersOpt.get()
        }
        return repository.save(users)
    }
}