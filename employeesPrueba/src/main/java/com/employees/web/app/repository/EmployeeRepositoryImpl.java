package com.employees.web.app.repository;

import com.employees.web.app.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUrl = "http://dummy.restapiexample.com/api/v1/";

    @Override
    public List<Employee> getAllEmployees() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "employees",
                HttpMethod.GET,
                null,
                String.class);

        String responseBody = responseEntity.getBody();

        try {
            // Lee el campo "data" como un array de empleados
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode dataNode = root.get("data");
            
            if (dataNode != null) {
                List<Employee> employees = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Employee>>() {});
                return employees;
            } else {
                // Manejar la situación donde no hay datos
                return Collections.emptyList();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(
                baseUrl + "employee/" + id,
                HttpMethod.GET,
                null,
                Employee.class);

        return responseEntity.getBody();
    }
}
