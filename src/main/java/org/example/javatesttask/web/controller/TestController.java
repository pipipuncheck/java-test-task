package org.example.javatesttask.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.javatesttask.service.TestService;
import org.example.javatesttask.web.dto.NumberDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @PostMapping()
    public void takeNumber(@RequestBody NumberDto number){
        testService.saveNumber(number);
    }

    @GetMapping()
    public List<Integer> getAllSorted(){
        return testService.getAllSorted();
    }
}
