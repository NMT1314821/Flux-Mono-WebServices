package com.springwebflux.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springwebflux.Dto.EmployeeDto;
import com.springwebflux.Service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/emp")
public class EmployeeController 
{
	private EmployeeService employeServ;
	
	@PostMapping
	@ResponseStatus(value=HttpStatus.CREATED)
	public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto empdto)
	{
		return employeServ.saveEmployee(empdto);
	}
	
	@GetMapping("{id}")
	public Mono<EmployeeDto> getEmpById(@PathVariable("id") String empid)
	{
		
		return employeServ.getEmpById(empid);
	}
	@GetMapping
	public Flux<EmployeeDto> getAllEmp()
	{
		return employeServ.getAllEmp();
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(value=HttpStatus.OK)
	public Mono<EmployeeDto> updateEmp(@RequestBody EmployeeDto empdto,@PathVariable("id") String empid)
	{
		return employeServ.updateEmp(empdto, empid);
	}
	@DeleteMapping("{id}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public Mono<Void> deleteEmp(@PathVariable("id") String empid)
	{
		return employeServ.deleteEmp(empid);
	}
	
}
