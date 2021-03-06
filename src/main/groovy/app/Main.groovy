package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableAutoConfiguration
class Main {
    static void main(String[] args){
        SpringApplication.run( Main, args )
    }
}