package httpAdapter.controller

import httpAdapter.feign.BotFactoryFeign
import httpAdapter.feign.BusinessLogicFeign
import httpAdapter.feign.DataBaseFeign
import httpAdapter.service.SenderLogsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminsController(
    val senderLogsService: SenderLogsService,
    val dataBaseClient: DataBaseFeign,
    val botFactoryClient: BotFactoryFeign,
    val businessLogicClient: BusinessLogicFeign
) {

    @PostMapping("/login_admin")
    fun loginAdmin(){}

    @GetMapping("/room_data")
    fun roomData(){}

    @GetMapping("/room_audit")
    fun roomAudit(){}

    @GetMapping("/room_info")
    fun roomInfo(){}

    @GetMapping("/room_connect")
    fun roomConnect(){}

    @GetMapping("/room_action")
    fun roomAction(){}

    @GetMapping("/room_delete")
    fun roomDelete(){}

    @GetMapping("/session_data")
    fun sessionData(){}

    @GetMapping("/session_audit")
    fun sessionAudit(){}

    @GetMapping("/session_info")
    fun sessionInfo(){}

    @GetMapping("/session_delete")
    fun sessionDelete(){}

    @GetMapping("/user_data")
    fun userData(){}

    @GetMapping("/user_audit")
    fun userAudit(){}

    @GetMapping("/user_info")
    fun userInfo(){}

    @GetMapping("/user_ban")
    fun userBan(){}

    @GetMapping("/user_delete")
    fun userDelete(){}

    @GetMapping("/traffic_data")
    fun trafficData(){}

    @GetMapping("/analysis")
    fun analysis(){}

    @GetMapping("/download_json")
    fun downloadJson(){}

    @GetMapping("/download_excel")
    fun downloadExcel(){}
}