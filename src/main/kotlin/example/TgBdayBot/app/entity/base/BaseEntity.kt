package example.TgBdayBot.app.entity.base

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null
}