package httpAdapter.entity.gameEntity

import httpAdapter.entity.GameEntity

class RegUser(
    val id: Int,
    var username: String,
    var login: String,
    var password: String,
    var countWin: Int,
    var countLose: Int,
    var dateOfRegistr: String,
    var birthdate: String
    ) : GameEntity("registry") {}