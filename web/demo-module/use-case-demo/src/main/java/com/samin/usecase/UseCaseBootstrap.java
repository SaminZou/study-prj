package com.samin.usecase;

import com.samin.usecase.beanconfig.config.SystemProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class UseCaseBootstrap implements CommandLineRunner {

  private final SystemProperties systemProperties;

  public static void main(String[] args) {
    SpringApplication.run(UseCaseBootstrap.class, args);
  }

  @Override
  public void run(String... args) {

    log.info("pgHost: {}", systemProperties.getPgHost());
    log.info("pgPort: {}", systemProperties.getPgPort());
    log.info("pgDatabase: {}", systemProperties.getPgDatabase());
    log.info("pgUsername: {}", systemProperties.getPgUsername());
    log.info("pgPassword: {}", systemProperties.getPgPassword());
    log.info("pgSchema: {}", systemProperties.getPgSchema());
    log.info("redisHost: {}", systemProperties.getRedisHost());
    log.info("redisPort: {}", systemProperties.getRedisPort());
    log.info("redisPassword: {}", systemProperties.getRedisPassword());
    log.info("redisDatabase: {}", systemProperties.getRedisDatabase());
  }
}
