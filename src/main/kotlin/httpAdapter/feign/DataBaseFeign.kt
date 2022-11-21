package httpAdapter.feign

import httpAdapter.entity.Room
import httpAdapter.entity.gameEntity.AnonUser
import httpAdapter.entity.gameEntity.RegUser
import httpAdapter.entity.webrequests.GoWaitObj
import httpAdapter.entity.webrequests.LoginIn
import httpAdapter.entity.webrequests.SearchRoomData
import httpAdapter.entity.webresponse.RaitingDatasets
import httpAdapter.feign.intfc.DataBaseFeignInterface
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class DataBaseFeign(
//    val client: DataBaseFeignInterface
) {

    fun isHaveThisUser(login: LoginIn): RegUser? {
        //return client.isHaveRegUser(login)
        return RegUser(1, "user", login.login, login.password, 0, 0, Date().toString(), "")
    }

    fun saveRegUser(newUser: RegUser): RegUser? {
//        return client.registrNewUser(newUser)
        return newUser
    }

    fun saveAnon(newAnon: AnonUser): AnonUser? {
        //return client.saveAnon(newAnon)
        return newAnon
    }

    fun getBotByToken() {}

    fun getUserById(id: Int): RegUser? {
//        return client.getRegUser(id)
        return RegUser(1, "user", "login", "login", 0, 0, Date().toString(), "")
    }

    fun saveNewAnot() {}

    fun getRating(): ArrayList<RaitingDatasets> {
//        val tmpArr = client.getRating()

        var mainArr: ArrayList<RaitingDatasets> = ArrayList()

//        for (el in tmpArr) {
//            val tmp = getUserById(el.id)
//            val newEl = RaitingDatasets()
//            newEl.usernameOfPlayer = tmp!!.username
//            newEl.userWin = tmp.countWin
//            newEl.userLose = tmp.countLose
//            newEl.userDateAddInRaiting = el.dateAdd
//            mainArr.add(newEl)
//        }

        return mainArr
    }

    fun getRoomById(id: Int): Room? {
        //return client.getRoom()
        return null
    }

    fun getRooms(): ArrayList<Room> {
//        return client.getRooms()
        return ArrayList()
    }

    fun getRoomsWithFilters(data: SearchRoomData): ArrayList<Room>? {
//        return client.getRoomsWithFilters(data)
        return ArrayList()
    }

    fun addGameEntityToRoom(goWaitObj: GoWaitObj): Room? {
//        return client.addGameEntity(goWaitObj)
        return null
    }

}