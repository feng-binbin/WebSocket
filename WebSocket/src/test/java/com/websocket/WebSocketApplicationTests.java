package com.websocket;

import com.websocket.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketApplicationTests {

    @Test
    void contextLoads() {
        Message message = new Message();
        message.setUsername("李四");
        message.setType(0);

        System.out.println(message);
    }

}
