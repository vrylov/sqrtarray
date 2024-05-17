package com.example.sqrtarray;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SqrtarrayApplication {

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            byte largeArray[] = {1,2,3,4,3,5,2,5,4,3,5,2,4,6,4,5,6,4,7,4,5,7,9,8,0,1};

            System.out.println("int sqrt from : "+Arrays.toString(largeArray));
            System.out.println("is : "+Arrays.toString(LargeArrayMath.intSqrt(largeArray)));
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(SqrtarrayApplication.class, args);
    }

}
