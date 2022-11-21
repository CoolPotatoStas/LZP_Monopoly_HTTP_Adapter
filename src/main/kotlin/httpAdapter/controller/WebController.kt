package httpAdapter.controller

import httpAdapter.dictionary.TypeOfAction
import httpAdapter.dictionary.TypeOfError
import httpAdapter.dictionary.TypeOfRoomActivity
import httpAdapter.entity.*
import httpAdapter.entity.gameEntity.*
import httpAdapter.entity.webrequests.*
import httpAdapter.entity.webresponse.*
import httpAdapter.feign.BotFactoryFeign
import httpAdapter.feign.BusinessLogicFeign
import httpAdapter.feign.DataBaseFeign
import httpAdapter.service.ProcessService
import httpAdapter.service.SenderLogsService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.ArrayList

@RestController
class WebController(
    val senderLogsService: SenderLogsService,
    val dataBaseClient: DataBaseFeign,
    val botFactoryClient: BotFactoryFeign,
    val businessLogicClient: BusinessLogicFeign,
    val processService: ProcessService
) {
    private val logger = KotlinLogging.logger { }

    private fun logSend(nameReq: String, login: String) = logger.info("Send: " + nameReq + " ::: Token: " + login)

    @PostMapping("/login_1")
    fun authorize(@RequestBody loginIn: LoginIn): LoginResponse {

        logSend("/login_1", loginIn.login)

        val loginResponse = LoginResponse()
        if (loginIn.botToken.equals("42botToken42")) {
            val answer = dataBaseClient.isHaveThisUser(loginIn)
            if (answer == null) {
                logger.info("login_1 : " + TypeOfError.E01)
                loginResponse.errorCode = TypeOfError.E01.code
                loginResponse.errorMessage = TypeOfError.E01.message
                return loginResponse
            }
            loginResponse.token = processService.generateSessionTokenForRegUser(answer).toString(Charsets.UTF_8)
            logger.info("login_1 : generate token for login " + loginIn.login + " : " + loginResponse.token)
            return loginResponse
        }

        logger.info("login_1 : " + TypeOfError.E03)

        loginResponse.errorCode = TypeOfError.E03.code
        loginResponse.errorMessage = TypeOfError.E03.message
        return loginResponse
    }

    @PostMapping("/login_2")
    fun regist(@RequestBody loginUp: LoginUp): LoginResponse {

        logSend("/login_2", loginUp.login)

        val loginResponse = LoginResponse()

        if (loginUp.botToken.equals("42botToken42")) {

            var loginIn = LoginIn()
            loginIn.login = loginUp.login
            loginIn.password = loginUp.password

            val answer = dataBaseClient.isHaveThisUser(loginIn)
            if (answer != null) {
                logger.info("login_2 : " + TypeOfError.E04)
                loginResponse.errorCode = TypeOfError.E04.code
                loginResponse.errorMessage = TypeOfError.E04.message
                return loginResponse
            }
            val newUser = dataBaseClient.saveRegUser(
                RegUser(
                    -1,
                    loginUp.username,
                    loginUp.login,
                    loginUp.password,
                    0,
                    0,
                    Date().toString(),
                    loginUp.birthdate
                )
            )!!
            loginResponse.token = processService.generateSessionTokenForRegUser(newUser).toString(Charsets.UTF_8)
            logger.info("login_2 : generate token for login " + loginIn.login + " : " + loginResponse.token)
            return loginResponse
        }
        logger.info("login_2 : " + TypeOfError.E03)

        loginResponse.errorCode = TypeOfError.E03.code
        loginResponse.errorMessage = TypeOfError.E03.message
        return loginResponse
    }

    @PostMapping("/login_3")
    fun enterAnon(@RequestBody anon: AnonEnter): LoginResponse {

        logSend("/login_3", "new_anon")

        val loginResponse = LoginResponse()

        if (anon.botToken.equals("42botToken42")) {
            val iden32 = processService.generateSessionIdent32ForAnon()
            val anon = dataBaseClient.saveAnon(AnonUser(-1, iden32))
            processService.arrOfSession.add(CustomSession(anon!!, iden32, 0))

            logger.info("login_3:ident32: " + iden32)

            loginResponse.token = iden32
            return loginResponse
        }

        logger.info("login_3 : " + TypeOfError.E03)

        loginResponse.errorCode = TypeOfError.E03.code
        loginResponse.errorMessage = TypeOfError.E03.message
        return loginResponse
    }

    @GetMapping("/auth")
    fun isAuth(@RequestBody token: String): Boolean = processService.hasInSessionArr(token)

    @GetMapping("/get_top")
    fun top(@RequestBody token: String): ArrayList<RaitingDatasets> {

        logger.info(token + ": /get_top")

        return dataBaseClient.getRating()
    }

    @GetMapping("/go_wait")
    fun goWait(@RequestBody goWaitObj: GoWaitObj): Any {
        val room = dataBaseClient.addGameEntityToRoom(goWaitObj)

        if (room == null) {
            logger.info("/go_wait" + TypeOfError.E05)
            return TypeOfError.E05
        }

        var roomForWait = RoomConnect()
        roomForWait.canStart = false
        roomForWait.countOfPlayer = room.arrayOfPlayer.size
        roomForWait.arr = room.arrayOfPlayer
        logger.info("/go_wait: room connect: " + room!!.id)
        return roomForWait
    }

    @GetMapping("/is_connect")
    fun isConnectUser(@RequestBody checker: RoomIdPlayerChecker): Any? {

        val room = dataBaseClient.getRoomById(checker.idRoom!!)

        logger.info("/is_connect: " + room!!.id)

        //return room

        var arrOfId = ArrayList<Int>()
        for (el in room!!.arrayOfPlayer) {
            if (el is AnonUser) {
                arrOfId.add(el.id)
            }
            if (el is Bot) {
                arrOfId.add(el.id)
            }
            if (el is RegUser) {
                arrOfId.add(el.id)
            }
        }

        if (processService.hasChangeInRoomUsers(arrOfId, checker.idOfPlayers!!)) {
            return TypeOfError.E06
        }

        val starterInfo = StarterInfo()
        starterInfo.arrOfGameEntity = room.arrayOfPlayer
        starterInfo.arrOfGameData = room.arrayOfDataPlayer

        return starterInfo
    }

    @GetMapping("/is_go_game")
    fun isGoGame(@RequestBody idRoom: Int): Any {

        logger.info("/is_go_game : " + idRoom)

        return dataBaseClient.getRoomById(idRoom)!!.typeOfActive
    }

    //доделать
    @PostMapping("/newGame_1") //игра с ботами
    fun botGame(@RequestBody data: NewGame1Data): Room? {
        //return dataBaseClient.getRoomById()

        logger.info("/new_game")

        return null
    }

    //доделать
    @PostMapping("/newGame_2") //создание своей комнаты
    fun createRoom(@RequestBody data: NewGame2Data): Room? {
        val tmp = processService.generateRoomToken()

        logger.info("/newGame_2: created new room ")

        var arrayOfPlayer: ArrayList<GameEntity> = ArrayList()
        var arrayOfDataPlayer: ArrayList<GameDataOnePlayer> = ArrayList()
        var arrayOfLosePlayer: ArrayList<GameEntity> = ArrayList()
        var arrayOfSteps: ArrayList<String> = ArrayList()
        var arrayOfWinner: ArrayList<GameEntity> = ArrayList()

        var newRoom = Room(
            -1,
            data.countOfPlayer!!,
            data.modeGame!!,
            data.typeOfGame!!,
            data.haveToken!!,
            tmp,
            data.mustRegistr!!,
            data.haveBots!!,
            data.countOfBots!!,
            arrayOfPlayer,
            arrayOfDataPlayer,
            arrayOfLosePlayer,
            arrayOfSteps,
            arrayOfWinner,
            TypeOfRoomActivity.WAIT,
            ""
        )

        return null
    }

    @PostMapping("/search_room") //поиск комнат
    fun searchRooms(
        @RequestBody data: SearchRoomData
    ): ArrayList<Room> {
        return dataBaseClient.getRoomsWithFilters(data)!!
    }

    //доделать
    @PostMapping("/newGame_3") //подключение к готовой комнате
    fun getConnect(@RequestBody data: NewGame3Data): Room? {
        //return dataBaseClient.getRoomById()
        return null
    }

    @PostMapping("/starter_info")
    fun starterInfo(@RequestBody data: StarterInfoRequest): Any {

//        val room = dataBaseClient.getRoomById(data.idRoom!!)
//
//        var starterInfo = StarterInfo()
//        starterInfo.arrOfGameEntity = room!!.arrayOfPlayer
//        starterInfo.arrOfGameData = room.arrayOfDataPlayer
//
//        return starterInfo
        return "created"
    }

    @PostMapping("/step")
    fun toStep(@RequestBody action: Action): Any? { //ответ на реакцию
        if (action.equals(TypeOfAction.CUBS)) {
            //находит игрока в бд, отправляет в бизнес-логику, та считает и возвращает обновленные данные
        }
        if (action.equals(TypeOfAction.BUY)) {
            //находит игрока в бд, отправляет данные логике, та возвращает обновления, обновления распространяются на всех
        }
        if (action.equals(TypeOfAction.RENTA)) {
            //отправка игрока логике, реакция в зависимости от того, есть у него деньги или нет
        }
        if (action.equals(TypeOfAction.AUCTION)) {
            //???????????????????????????????
        }

        return "good"
    }

    @GetMapping("/checkInfo")
    fun checkInfo(@RequestBody info: CheckInfoRequest): Any? {
        //return dataBaseClient.getRoomById(info.idRoom!!)!!.arrayOfSteps[0]
        return "checked"
    }

    //доделать
    @GetMapping("/exit")
    fun exit(): Any {

        return "exit"
    } //реагирует на выход из игры

    fun getGameEntity() {} //возвращает список игровых сущностей. Нужен для того, чтобы в случае выхода ожного из человек была подмена на бота
}