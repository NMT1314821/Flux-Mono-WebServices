package com.springwebflux.Service;



import com.springwebflux.Dto.EmployeeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService 
{
	Mono<EmployeeDto> saveEmployee(EmployeeDto empDto);
	
	Mono<EmployeeDto> getEmpById(String empid);
	
	//get all employees
	
	Flux<EmployeeDto> getAllEmp();
	
	Mono<EmployeeDto> updateEmp(EmployeeDto empdto,String empid);
	
	Mono<Void>deleteEmp(String empid);

}
