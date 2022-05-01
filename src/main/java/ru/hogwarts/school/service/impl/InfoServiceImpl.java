package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public int getPort() {
        logger.info("Server port " + serverPort);
        return serverPort;
    }
}
