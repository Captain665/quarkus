package org.acme.v1.resourceHandlers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.v1.models.CompanyModel;
import org.acme.v1.models.EmployeeModel;
import org.acme.v1.repositories.CompanyRepository;
import org.acme.v1.repositories.EmployeeRepository;
import org.acme.v1.resources.CompanyResponseResource;
import org.acme.v1.resources.EmployeeResponseResource;

import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public class CompanyResourceHandler {
	public final CompanyRepository repository;
	public final EmployeeRepository employeeRepository;

	@Inject
	public CompanyResourceHandler(CompanyRepository repository, EmployeeRepository employeeRepository) {
		this.repository = repository;
		this.employeeRepository = employeeRepository;
	}

	public CompanyResponseResource getCompanyDetails(Long id) {
		CompanyModel model = repository.findById(id);
		if (model != null) {
			CompanyResponseResource companyResponseResource = new CompanyResponseResource(model);
			List<EmployeeModel> model1 = employeeRepository.findByCompanyId(model.getId()).stream().filter(EmployeeModel::getActive).toList();
			companyResponseResource.setEmployeeDetails(model1.stream().map(EmployeeResponseResource::new).toList());
			companyResponseResource.setNumberOfEmployee(BigInteger.valueOf(model1.size()));
			return companyResponseResource;
		}
		return null;
	}
}
