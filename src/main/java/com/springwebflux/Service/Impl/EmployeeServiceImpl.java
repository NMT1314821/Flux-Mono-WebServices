package com.springwebflux.Service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springwebflux.Dto.EmployeeDto;
import com.springwebflux.Mapper.EmployeeMapper;
import com.springwebflux.Model.Employee;
import com.springwebflux.Repository.EmployeeRepository;
import com.springwebflux.Service.EmployeeService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService 
{
	private EmployeeRepository empRepo;

	@Override
	public Mono<EmployeeDto> saveEmployee(EmployeeDto empDto)
	{
		Employee emp=EmployeeMapper.mapToEmployee(empDto);
		Mono<Employee> savede=empRepo.save(emp);
		return savede.map((s)->EmployeeMapper.mapToEmployeeDto(s));
	}

	@Override
	public Mono<EmployeeDto> getEmpById(String empid)
	{
		Mono<Employee> es=empRepo.findById(empid);
		return es.map((emp)->EmployeeMapper.mapToEmployeeDto(emp));
	}

	@Override
	public Flux<EmployeeDto> getAllEmp()
	{	
		Flux<Employee> all=empRepo.findAll();
		return all
				.map((s)->EmployeeMapper.mapToEmployeeDto(s))
				.switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<EmployeeDto> updateEmp(EmployeeDto empdto, String empid) {
		Mono<Employee> emono=empRepo.findById(empid);
		Mono<Employee> upemp=emono.flatMap((eemp)->{
			eemp.setFirstName(empdto.getFirstName());
			eemp.setLastName(empdto.getLastName());
			eemp.setEmail(empdto.getEmail());
			return empRepo.save(eemp);
		});		
		return upemp.map((s)->EmployeeMapper.mapToEmployeeDto(s)); 
	}

	@Override
	public Mono<Void> deleteEmp(String empid)
	{
		return empRepo.deleteById(empid);
		
	}

	
	

}
