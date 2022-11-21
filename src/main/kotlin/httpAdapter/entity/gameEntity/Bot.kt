package httpAdapter.entity.gameEntity

import httpAdapter.entity.GameEntity

class Bot(
    val id: Int,
    var token: String,
    var dateBeginWork: String,
    var countOfUse: Int,
    var isFree: Boolean
): GameEntity("bot") {}