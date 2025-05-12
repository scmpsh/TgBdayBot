package tg.bday.bot.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import tg.bday.bot.app.entity.base.BaseEntity

@Entity
data class Users(
    @Column(name = "user_id") var userId: Long,
) : BaseEntity()