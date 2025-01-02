package org.acme.v1.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.v1.models.EmployeeModel;

import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<EmployeeModel> {
	public List<EmployeeModel> findByCompanyId(Long companyId) {
		return list("companyId", companyId);
	}
}
