package httpAdapter.service

import httpAdapter.dictionary.TypeOfService
import httpAdapter.entity.Logs
import org.apache.tomcat.util.json.JSONParser
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
@EnableScheduling
class SenderLogsService(
    val rabbit: RabbitTemplate
) {

    fun formLogs(service: TypeOfService, time: String, logs: String){
        rabbit.convertAndSend(Logs(service.value, time, logs))
    }

//    @Scheduled(cron = "* */10 * * * *")
    fun logsHttpAdapter(){
        val format = DateTimeFormatter.ofPattern("HH:mm:ss dd_MM_yyyy")
        rabbit.convertAndSend("logging", Logs(TypeOfService.ADAPTER_HTTP.value, LocalDateTime.now().format(format), "OK").toString())
    }
}