package httpAdapter.feign

import httpAdapter.feign.intfc.BusinessLogicFeignInterface
import org.springframework.stereotype.Service

@Service
class BusinessLogicFeign(
    val client: BusinessLogicFeignInterface
) {

    fun queueOfPlayers(){}

    fun isEnd(){}

    //fun howMuch(){}
}