package com.springwebflux;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.springwebflux.Dto.EmployeeDto;

import com.springwebflux.Repository.EmployeeRepository;
import com.springwebflux.Service.EmployeeService;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeInteTest 
{
	@Autowired
	private EmployeeService employeServ;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@BeforeEach
	public void before()
	{
		System.out.println("before each test");
		empRepo.deleteAll().subscribe();
	}
	
	@Test
	public void testSaveEmployee()
	{
		EmployeeDto empDto=new EmployeeDto();
		empDto.setFirstName("naveen");
		empDto.setLastName("Chinni");
		empDto.setEmail("nabi@gmail.com");
		
		webTestClient.post().uri("/api/emp")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(empDto),EmployeeDto.class)
		.exchange()
		.expectStatus().isCreated()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.firstName").isEqualTo(empDto.getFirstName())
		.jsonPath("$.lastName").isEqualTo(empDto.getLastName())
		.jsonPath("$.email").isEqualTo(empDto.getEmail());
				
	}
	@Test
	public void testGetSingleEmp()
	{
		EmployeeDto empDto=new EmployeeDto();
		empDto.setFirstName("harish");
		empDto.setLastName("ragada");
		empDto.setEmail("love@gmail.com");
		
		EmployeeDto savedemp=employeServ.saveEmployee(empDto).block();
		
		webTestClient.get().uri("/api/emp/{id}",Collections.singletonMap("id", savedemp.getId()))
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.id").isEqualTo(savedemp.getId())
		.jsonPath("$.firstName").isEqualTo(savedemp.getFirstName())
		.jsonPath("$.lastName").isEqualTo(savedemp.getLastName())
		.jsonPath("$.email").isEqualTo(savedemp.getEmail());
		
	}
	
	@Test
	public void getAllEmp()
	{
		webTestClient.get().uri("/api/emp")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(EmployeeDto.class)
		.consumeWith(System.out::println);
		
	}
	
	@Test
	public void upTestEmp()
	{
		EmployeeDto empdto=new EmployeeDto();
		empdto.setFirstName("madhvigru");
		empdto.setLastName("pechetti");
		empdto.setEmail("pmadhu13@gmail.com");
		
		EmployeeDto savedemp=employeServ.saveEmployee(empdto).block();
		
		EmployeeDto upEmp=new EmployeeDto();
		upEmp.setFirstName("naveenmgaru");
		upEmp.setLastName("chiinigaru");
		upEmp.setEmail("nua@gmail.com");
		
		webTestClient.put().uri("/api/emp/{id}",Collections.singletonMap("id",savedemp.getId()))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(upEmp),EmployeeDto.class)
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.consumeWith(System.out::println)
		.jsonPath("$.firstName").isEqualTo(upEmp.getFirstName())
		.jsonPath("$.lastName").isEqualTo(upEmp.getLastName())
		.jsonPath("$.email").isEqualTo(upEmp.getEmail());
	
	}
	@Test
	public void deleteTest()
	{
		EmployeeDto empDto=new EmployeeDto();
		empDto.setFirstName("harish");
		empDto.setLastName("ragada");
		empDto.setEmail("love@gmail.com");
		
		EmployeeDto savedemp=employeServ.saveEmployee(empDto).block();
		
		
		webTestClient.delete().uri("/api/emp/{id}",Collections.singletonMap("id",savedemp.getId()))
		.exchange()
		.expectStatus().isNoContent()
		.expectBody()
		.consumeWith(System.out::println);
	}
	
}
//The singletonMap () method of Java Collections class is used to get an immutable map, 
//mapping only the specified key to the specified value. It is the key 
//which will be stored in the returned map. It is the value to which the returned map maps the key.
