package com.empcrud.emp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.empcrud.emp.Employee.Employee;
import com.empcrud.emp.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
	private final EmployeeService employeeService;

	public EmployeeResource(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employee = employeeService.findAllEmployees();
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = employeeService.findEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@PostMapping("/fileUpload")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream out = new FileOutputStream(myFile);
		out.write(file.getBytes());
		System.out.println(out.getFD());
		out.close();
		return new ResponseEntity<Object>("file uploaded successful", HttpStatus.OK);
	}

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY1;

	@PostMapping("/fileDownload")
	public ResponseEntity<Object> fileDownload() throws IOException {
		InputStream inputStream = new URL("https://picsum.photos/id/237/200/300").openStream();
		Files.copy(inputStream, Paths.get(FILE_DIRECTORY1+"dog.png"),StandardCopyOption.REPLACE_EXISTING);

		return new ResponseEntity<Object>("file saved successful", HttpStatus.OK);
	}
}
