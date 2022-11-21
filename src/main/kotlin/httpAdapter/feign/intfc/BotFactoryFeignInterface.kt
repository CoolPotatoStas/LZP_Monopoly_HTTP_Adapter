package httpAdapter.feign.intfc

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name="botFactory", url="\${feign-interface-url.botFactory}")
interface BotFactoryFeignInterface {

    @GetMapping("/activate")
    fun activate(){}

    @GetMapping("/die")
    fun die(){}
}