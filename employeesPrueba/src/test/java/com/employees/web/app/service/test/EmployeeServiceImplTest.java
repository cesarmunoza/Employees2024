package com.employees.web.app.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employees.web.app.model.Employee;
import com.employees.web.app.repository.EmployeeRepository;
import com.employees.web.app.service.EmployeeServiceImpl;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Simulamos que el repositorio devuelve una lista de empleados
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John Doe", 50000.0, 30, ""),
                new Employee(2L, "Jane Doe", 60000.0, 25, "")
        );

        // Configuramos el comportamiento simulado
        when(employeeRepository.getAllEmployees()).thenReturn(mockEmployees);

        // Llamamos al método que queremos probar
        List<Employee> result = employeeService.getAllEmployees();

        // Verificamos que el resultado es el esperado
        assertEquals(mockEmployees.size(), result.size());
        assertEquals(mockEmployees.get(0), result.get(0));
        assertEquals(mockEmployees.get(1), result.get(1));

        // Verificamos que el método del repositorio fue llamado
        verify(employeeRepository, times(1)).getAllEmployees();
    }
}
