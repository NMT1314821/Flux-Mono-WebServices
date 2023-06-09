package com.springwebflux.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springwebflux.Model.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String> 
{

}
