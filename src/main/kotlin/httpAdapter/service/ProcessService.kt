package httpAdapter.service

import httpAdapter.entity.CustomSession
import httpAdapter.entity.gameEntity.AnonUser
import httpAdapter.entity.gameEntity.RegUser
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProcessService {

    var arrOfSession: ArrayList<CustomSession> = ArrayList<CustomSession>()

    fun generateSessionTokenForRegUser(user: RegUser): ByteArray{
        val tmp: String = "ru:" + user.id + ":" + user.username + ":" + user.login;
        arrOfSession.add(CustomSession(user, tmp, 0))
        return Base64.getEncoder().encode(tmp.toByteArray(Charsets.UTF_8))
    }

    fun generateSessionIdent32ForAnon(): String{
        val array = ByteArray(32)
        Random().nextBytes(array)
        return String(array, Charsets.UTF_8)
    }

    fun generateRoomToken(): String {
        val array = ByteArray(6)
        Random().nextBytes(array)
        return String(array, Charsets.UTF_8)
    }

    fun hasInSessionArr(token: String): Boolean{

        for (session in arrOfSession){
            if (session.token.equals(token)) return true
        }

        return false
    }

    fun hasChangeInRoomUsers(mainArrOfId: ArrayList<Int>, usArrOfId: Array<Int>): Boolean{
        if (mainArrOfId.size != usArrOfId.size){
            return false
        }

        mainArrOfId.sort()
        usArrOfId.sort()

        for (i in mainArrOfId.indices){
            if (mainArrOfId[i] != usArrOfId[i])
                return false
        }

        return true
    }

    @Scheduled(cron = "* */15 * * * *")
    fun checkSession(){

        val tmpArrOfSession: ArrayList<CustomSession> = ArrayList<CustomSession>()

        for (session in arrOfSession){
            session.tick++;
            if (session.tick <= 3){
                tmpArrOfSession.add(session)
            }
        }

        arrOfSession = tmpArrOfSession
    }
}