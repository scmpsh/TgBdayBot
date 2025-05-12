package tg.bday.bot.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import tg.bday.bot.app.entity.base.BaseEntity


@Entity
data class Wish(
    @Column(name = "item") var item: String,
    @Column(name = "number_of_items") var numberOfItems: Int,
) : BaseEntity()