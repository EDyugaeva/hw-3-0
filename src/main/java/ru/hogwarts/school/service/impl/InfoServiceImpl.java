package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.InfoService;

import java.util.stream.Stream;

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

    @Override
    public int getMathOperation() {
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        logger.debug("Calculations were made, the result is " + sum);
        return sum;
    }


}
