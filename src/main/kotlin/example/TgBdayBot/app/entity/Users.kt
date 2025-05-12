package example.TgBdayBot.app.entity

import example.TgBdayBot.app.entity.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
data class Users(
    @Column(name = "user_id") var userId: Long,
) : BaseEntity()