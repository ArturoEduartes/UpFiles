package me.user.upFiles

import me.user.upFiles.storage.StorageProperties
import me.user.upFiles.storage.StorageService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

//TODO estudiar funcionamiento del proceso de ejecucion de la aplicacion

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties::class)
class UploadingFilesApplication {
    @Bean
    fun init(storageService: StorageService): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            storageService.deleteAll()
            storageService.init()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //SpringApplication.run(UploadingFilesApplication::class.java, *args) // Alternative form of init application
            runApplication<UploadingFilesApplication>(*args)
        }
    }
}