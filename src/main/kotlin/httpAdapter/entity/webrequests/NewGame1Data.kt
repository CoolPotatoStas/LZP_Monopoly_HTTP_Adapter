package httpAdapter.entity.webrequests

import org.springframework.web.bind.annotation.RequestBody

class NewGame1Data {
    var idPlayer: Int? = null
    var token: String? = null
    var countOfBots: Int? = null
    var modeGame: String? = null
    var typeOfGame: String? = null
}