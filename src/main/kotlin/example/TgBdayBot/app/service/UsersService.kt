package example.TgBdayBot.app.service

import example.TgBdayBot.app.entity.Users
import example.TgBdayBot.app.repository.UsersRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

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