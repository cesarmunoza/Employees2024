package com.employees.web.app.repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.employees.web.app.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
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
        try {
        	
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl + "employee/" + id,
                    HttpMethod.GET,
                    null,
                    String.class);
            
            
            String responseBody = responseEntity.getBody();
            
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode dataNode = root.get("data");
            
            if (dataNode != null) {
            	Employee employee = objectMapper.readValue(dataNode.toString(), new TypeReference<Employee>() {});
            	return employee;
            

            
            } else {
                log.error("La solicitud al servidor no fue exitosa. Código de estado: {}", "error");
                return null;
            }
        } catch (RestClientException e) {
            log.error("Error al realizar la solicitud al servidor", e);
            return null;
        } catch (JsonMappingException e) {
        	log.error("Error al realizar la solicitud al servidor", e);
            return null;
			
		} catch (JsonProcessingException e) {
			log.error("Error al realizar la solicitud al servidor", e);
            return null;
			
		}
    }


    
    
    
    
//    @Override
//    public Employee getEmployeeById(Long id) {
//        try {
//        	ResponseEntity<String> rawResponseEntity = restTemplate.exchange(
//                    baseUrl + "employee/" + id,
//                    HttpMethod.GET,
//                    null,
//                    String.class);
//
//            log.info("Respuesta cruda de la API: {}", rawResponseEntity.getBody());
//
//            ResponseEntity<Employee> responseEntity = restTemplate.exchange(
//                    baseUrl + "employee/" + id,
//                    HttpMethod.GET,
//                    null,
//                    Employee.class);
//            log.info("Respuesta real que está devolviendo: {}", responseEntity.getBody());
//
//            HttpStatus statusCode = (HttpStatus) rawResponseEntity.getStatusCode();
//            if (statusCode.is2xxSuccessful()) {
//                return responseEntity.getBody();
//            } else {
//                log.error("La solicitud al servidor no fue exitosa. Código de estado: {}", statusCode.value());
//                return null;
//            }
//        } catch (RestClientException e) {
//            log.error("Error al realizar la solicitud al servidor", e);
//            return null;
//        }
//    }
}
