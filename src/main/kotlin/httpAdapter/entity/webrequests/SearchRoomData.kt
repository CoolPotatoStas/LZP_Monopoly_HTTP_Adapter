package httpAdapter.entity.webrequests

import org.springframework.web.bind.annotation.RequestBody

class SearchRoomData {
    var token: String? = null
    var countOfPlayer: Int? = null
    var modeGame: String? = null
    var typeOfGame: String? = null
    var mustRegistr: String? = null
    var haveBots: Boolean? = null
}