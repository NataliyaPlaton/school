package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class InfoServiceTest implements InfoService {

    @Value("${server.port}")
    private int port;

    public InfoServiceTest() {
    }

    @Override
    public int getPort() {
        return port;
    }
}

