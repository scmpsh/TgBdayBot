package example.TgBdayBot.app.entity

import example.TgBdayBot.app.entity.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "user_wish")
data class UserWish(
    @ManyToOne @JoinColumn(name = "user_id") var user: Users,
    @ManyToOne @JoinColumn(name = "wish_id") var wish: Wish
) : BaseEntity()