package httpAdapter.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RabbitLoggerConfig(
    @Value("\${logger.queue-name}")
    var queueName:String,
    @Value("\${logger.host}")
    var host: String,
    @Value("\${logger.port}")
    var port: String
) {

    var queue: Queue = Queue(queueName)

    @Bean
    open fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory(host) //localhost
        connectionFactory.username = "guest"
        connectionFactory.setPassword("guest")
        return connectionFactory
    }

    @Bean
    open fun appQueue(): Queue {
        return queue
    }

    @Bean
    open fun appExchange(): DirectExchange {
        return DirectExchange(queue.name)
    }

    @Bean
    open fun declareBinding(): Binding {
        return BindingBuilder.bind(appQueue()).to(appExchange()).with(queue.name)
    }

    @Bean
    open fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }
}