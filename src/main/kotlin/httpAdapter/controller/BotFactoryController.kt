package httpAdapter.controller

import httpAdapter.feign.BotFactoryFeign
import httpAdapter.feign.BusinessLogicFeign
import httpAdapter.feign.DataBaseFeign
import httpAdapter.service.SenderLogsService
import org.springframework.stereotype.Controller

@Controller
class BotFactoryController(
    val senderLogsService: SenderLogsService,
    val dataBaseClient: DataBaseFeign,
    val botFactoryClient: BotFactoryFeign,
    val businessLogicClient: BusinessLogicFeign
) {

}