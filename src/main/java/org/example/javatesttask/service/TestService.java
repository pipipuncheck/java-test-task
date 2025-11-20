package org.example.javatesttask.service;

import lombok.RequiredArgsConstructor;
import org.example.javatesttask.model.Number;
import org.example.javatesttask.repository.TestRepository;
import org.example.javatesttask.util.exception.NumberIsNullException;
import org.example.javatesttask.util.mapper.NumberMapper;
import org.example.javatesttask.web.dto.NumberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final NumberMapper mapper;

    public void saveNumber(NumberDto dto){

        if (dto == null) {
            throw new NumberIsNullException("Number is null!");
        }

        Number number = mapper.toEntity(dto);

        testRepository.save(number);
    }

    public List<Integer> getAllSorted(){

        List<Number> allNumbers = testRepository.findAll();

        return allNumbers
                .stream()
                .map(Number::getNumber)
                .sorted()
                .toList();
    }
}
