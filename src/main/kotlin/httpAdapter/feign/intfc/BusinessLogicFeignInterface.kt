package httpAdapter.feign.intfc

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name="businessLogic", url="\${feign-interface-url.businessLogic}")
interface BusinessLogicFeignInterface {

    @GetMapping("/generate_queue")
    fun queueOfPlayers(arrOfId: Array<Int>){}

    @GetMapping("/is_end")
    fun isEnd(){}

    @GetMapping("/cubs")
    fun cubs(){}

    @GetMapping("/buy")
    fun buy(){}

    @GetMapping("/renta")
    fun renta(){}

    @GetMapping("/auction")
    fun auction(){}
}