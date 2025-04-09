package example.TgBdayBot.app.entity

import jakarta.persistence.*
import java.util.*


@Entity
data class Wish(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    val id: UUID,

    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "item")
    var item: String,

    @Column(name = "booked")
    var isBooked: Boolean = false
)