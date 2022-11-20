package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.ParallelStreamService;

@RestController
public class ParallelStreamController {

    private final ParallelStreamService parallelStreamService;

    public ParallelStreamController(ParallelStreamService parallelStreamService) {
        this.parallelStreamService = parallelStreamService;
         }

    @GetMapping
    public void testParallelStream() {
        parallelStreamService.testParallelStream();
    }
}
