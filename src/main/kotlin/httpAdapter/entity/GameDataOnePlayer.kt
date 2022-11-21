package httpAdapter.entity

class GameDataOnePlayer {
    val id: Int = -1
    var idPlayer: Int = -1
    var typeOfPlayer: String = ""
    var positionOnField: String = ""
    var countOfQueue: Byte = -1
    //var typeOfFigure: String = ""
    var countOfMoney: Long = 2500
    var cards: ArrayList<String> = ArrayList()
    var realEstate: HashMap<String, String> = HashMap() //данные типа "улица - что куплено"
}