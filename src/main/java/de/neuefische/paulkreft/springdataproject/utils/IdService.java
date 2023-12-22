package de.neuefische.paulkreft.springdataproject.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdService {
    public String randomId() {
        return UUID.randomUUID().toString();
    }
}
