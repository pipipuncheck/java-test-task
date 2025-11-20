package org.example.javatesttask;

import org.example.javatesttask.model.Number;
import org.example.javatesttask.repository.TestRepository;
import org.example.javatesttask.service.TestService;
import org.example.javatesttask.util.exception.NumberIsNullException;
import org.example.javatesttask.util.mapper.NumberMapper;
import org.example.javatesttask.web.dto.NumberDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private NumberMapper numberMapper;

    @InjectMocks
    private TestService testService;

    @Test
    void saveNumber_WithValidDto_ShouldSaveEntity() {

        NumberDto dto = new NumberDto(42);
        Number entity = new Number(UUID.randomUUID(), 42);

        when(numberMapper.toEntity(dto)).thenReturn(entity);
        when(testRepository.save(entity)).thenReturn(entity);

        testService.saveNumber(dto);

        verify(numberMapper, times(1)).toEntity(dto);
        verify(testRepository, times(1)).save(entity);
    }

    @Test
    void saveNumber_WithNullDto_ShouldThrowException() {

        NumberDto dto = null;

        assertThrows(NumberIsNullException.class, () -> testService.saveNumber(dto));

        verify(numberMapper, never()).toEntity(any());
        verify(testRepository, never()).save(any());
    }

    @Test
    void getAllSorted_WithNumbers_ShouldReturnSortedList() {

        List<Number> numbers = List.of(
                new Number(UUID.randomUUID(), 30),
                new Number(UUID.randomUUID(), 10),
                new Number(UUID.randomUUID(), 20)
        );

        when(testRepository.findAll()).thenReturn(numbers);

        List<Integer> result = testService.getAllSorted();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(List.of(10, 20, 30), result);
        verify(testRepository, times(1)).findAll();
    }

    @Test
    void getAllSorted_WithEmptyList_ShouldReturnEmptyList() {

        when(testRepository.findAll()).thenReturn(List.of());

        List<Integer> result = testService.getAllSorted();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(testRepository, times(1)).findAll();
    }

    @Test
    void getAllSorted_WithSingleNumber_ShouldReturnSingleElementList() {

        Number number = new Number(UUID.randomUUID(), 5);
        when(testRepository.findAll()).thenReturn(List.of(number));

        List<Integer> result = testService.getAllSorted();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(List.of(5), result);
        verify(testRepository, times(1)).findAll();
    }

    @Test
    void getAllSorted_WithDuplicateNumbers_ShouldReturnSortedWithDuplicates() {

        List<Number> numbers = List.of(
                new Number(UUID.randomUUID(), 10),
                new Number(UUID.randomUUID(), 10),
                new Number(UUID.randomUUID(), 5)
        );

        when(testRepository.findAll()).thenReturn(numbers);

        List<Integer> result = testService.getAllSorted();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(List.of(5, 10, 10), result);
        verify(testRepository, times(1)).findAll();
    }
}