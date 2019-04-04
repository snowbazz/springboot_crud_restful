package io.github.orrrz;

import io.github.orrrz.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by icejam.
 * 引导类
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    // 初始化IdWorker
    @Bean
    public IdWorker IdWorker() {
        return new IdWorker(1, 1);
    }
}
