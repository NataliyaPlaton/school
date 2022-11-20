package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class InfoServiceProduction implements InfoService {

    @Value("${server.port}")
    private int port;

    public InfoServiceProduction() {
    }

    @Override
    public int getPort() {
        return port;
    }
}

