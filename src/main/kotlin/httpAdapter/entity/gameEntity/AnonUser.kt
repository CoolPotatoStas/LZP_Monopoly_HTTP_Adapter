package httpAdapter.entity.gameEntity

import httpAdapter.entity.GameEntity

class AnonUser(
    val id: Int,
    var iden32: String
): GameEntity("anon") {}