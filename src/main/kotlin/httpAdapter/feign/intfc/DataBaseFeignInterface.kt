package httpAdapter.feign.intfc

import httpAdapter.entity.Rating
import httpAdapter.entity.Room
import httpAdapter.entity.gameEntity.AnonUser
import httpAdapter.entity.gameEntity.RegUser
import httpAdapter.entity.webrequests.GoWaitObj
import httpAdapter.entity.webrequests.LoginIn
import httpAdapter.entity.webrequests.SearchRoomData
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name="database", url="\${feign-interface-url.dataBase}")
interface DataBaseFeignInterface {

    @GetMapping("/is_have_reg_user")
    fun isHaveRegUser(loginIn: LoginIn): RegUser?

    @GetMapping("/registr")
    fun registrNewUser(user: RegUser): RegUser?

    @GetMapping("/save_anon")
    fun saveAnon(anon: AnonUser): AnonUser?

    @GetMapping("/top")
    fun getRating(): ArrayList<Rating>

    @GetMapping("/get_room")
    fun getRoom(): Room

    @GetMapping("/rating")
    fun getRooms(): ArrayList<Room>

    @GetMapping("/get_reguser")
    fun getRegUser(id: Int): RegUser?

    @GetMapping("/add_user")
    fun addGameEntity(goWaitObj: GoWaitObj): Room?

    @GetMapping("/filter_room")
    fun getRoomsWithFilters(data: SearchRoomData): ArrayList<Room>?
}