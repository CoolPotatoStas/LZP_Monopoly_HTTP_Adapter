package httpAdapter.feign

import httpAdapter.feign.intfc.BotFactoryFeignInterface
import org.springframework.stereotype.Service

@Service
class BotFactoryFeign(
    val client: BotFactoryFeignInterface
) {
}