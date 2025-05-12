package tg.bday.bot.app.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import tg.bday.bot.app.entity.base.BaseEntity

@Entity
@Table(name = "user_wish")
data class UserWish(
    @ManyToOne @JoinColumn(name = "user_id") var user: Users,
    @ManyToOne @JoinColumn(name = "wish_id") var wish: Wish
) : BaseEntity()