package org.acme.v1.resourceHandlers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.v1.models.CompanyModel;
import org.acme.v1.models.EmployeeModel;
import org.acme.v1.models.EmployeeSalary;
import org.acme.v1.repositories.CompanyRepository;
import org.acme.v1.repositories.EmployeeRepository;
import org.acme.v1.resources.EmployeeBuilder;
import org.acme.v1.resources.EmployeeResource;

import java.math.BigDecimal;

@ApplicationScoped
public class EmployeeResourceHandler {
	@Inject
	private EmployeeRepository employeeRepository;
	@Inject
	private CompanyRepository companyRepository;

	public EmployeeResource createOrUpdate(EmployeeResource resource) {
		CompanyModel companyModel = companyRepository.findById(resource.getCompanyId());
		if (companyModel == null) {
			return null;
		}
		EmployeeModel employeeModel = new EmployeeBuilder()
				.setFullName(resource.getFullName())
				.setMobile(resource.getMobile())
				.setEmailId(resource.getEmailId())
				.setGender(resource.getGender())
				.setJoiningDate(resource.getJoiningDate())
				.setResignDate(resource.getResignDate())
				.setRole(resource.getRole())
				.setLocation(resource.getLocation())
				.setCompany(companyModel)
				.setAssets(resource.getAssets())
				.setSalary(calculateEmployeeSalary(resource.getSalaryStructure()))
				.build();
		EmployeeResource responseResource = new EmployeeResource(employeeRepository.saveEmployeeDetails(employeeModel));
		responseResource.setCompanyDetails(companyModel);
		return responseResource;
	}


	private EmployeeSalary calculateEmployeeSalary(EmployeeSalary employeeSalary) {
		BigDecimal baseAmount = employeeSalary.getBaseAmount() != null ? employeeSalary.getBaseAmount() : BigDecimal.ZERO;
		BigDecimal hra = employeeSalary.getHra() != null ? employeeSalary.getHra() : BigDecimal.ZERO;
		BigDecimal pf = employeeSalary.getPf() != null ? employeeSalary.getPf() : BigDecimal.ZERO;
		BigDecimal medical = employeeSalary.getMedical() != null ? employeeSalary.getMedical() : BigDecimal.ZERO;
		BigDecimal tax = employeeSalary.getTax() != null ? employeeSalary.getTax() : BigDecimal.ZERO;
		BigDecimal totalAmount = baseAmount.add(hra).add(pf).add(medical).subtract(tax);
		employeeSalary.setTotalAmount(totalAmount);
		return employeeSalary;
	}
}
