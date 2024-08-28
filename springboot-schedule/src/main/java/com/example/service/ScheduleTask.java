package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
La diferencia entre rate y delay es que rate ejecuta sin importar si termina la tarea
y el delay espera a que termine la tarea para ejecutarlo.

 - fixedRate: Ejecuta a intervalos fijos desde el inicio de cada ejecución.
 - fixedDelay: Ejecuta a intervalos fijos desde el final de cada ejecución.
 - initialDelay: Retrasa la primera ejecución.
 - cron: Ejecuta según una expresión CRON.
 - zone: Especifica la zona horaria para una expresión CRON.
 */
@Component
public class ScheduleTask {

    //    @Scheduled(fixedDelay = 5000, initialDelay = 3000)
    @Scheduled(cron = "*/10 * * * * *") // ejecuta cada 10s
    public void scheduleMessage() {
        System.out.println("Hola mundo!!");
    }

//    Para saber zona horaria
//    public static void main(String[] args) {
//        ZoneId.getAvailableZoneIds().forEach(System.out::println);
//    }
}
