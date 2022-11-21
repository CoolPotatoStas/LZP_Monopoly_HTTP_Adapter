package httpAdapter.entity

open class CustomSession(
    val user: GameEntity,
    val token: String,
    var tick: Int
) {


}