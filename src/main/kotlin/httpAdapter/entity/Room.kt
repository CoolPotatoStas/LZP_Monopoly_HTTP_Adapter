package httpAdapter.entity

import httpAdapter.dictionary.TypeOfRoomActivity
import java.util.ArrayList

class Room (
    val id: Int,
    var countOfPlayer: Int,
    var modeGame: String,
    var typeGame: String,
    var haveToken: Boolean,
    var token: String?,
    var mustRegistr: Boolean,
    var haveBots: Boolean,
    var countOfBots: Int,
    var arrayOfPlayer: ArrayList<GameEntity>,
    var arrayOfDataPlayer: ArrayList<GameDataOnePlayer>,
    var arrayOfLosePlayer: ArrayList<GameEntity>,
    var arrayOfSteps: ArrayList<String>, //нужна сущность Шаг
    var arrayOfWinner: ArrayList<GameEntity>,
    var typeOfActive: TypeOfRoomActivity,
    var dateOfActivate: String
) {
}