package org.acme.v1.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.v1.models.EmployeeModel;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<EmployeeModel> {

	@Inject
	private AssetRepository assetRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public List<EmployeeModel> findByCompanyId(Long companyId) {
		return list("company.id", companyId);
	}

	@Transactional
	public EmployeeModel saveEmployeeDetails(EmployeeModel model) {
		EmployeeModel modelInDb = findByMobile(model.getMobile());
		if (modelInDb != null) {
			if (modelInDb.getAsset() != null) {
				modelInDb.getAsset().forEach(assetModel -> assetRepository.delete(assetModel));
			}
			model.setId(modelInDb.getId());
			model.getSalary().setId(modelInDb.getSalary().getId());
			model.getSalary().setEmployee(model);
			model.getAsset().forEach(assetModel -> assetModel.setEmployee(model));
			model.setActive(modelInDb.getActive());
			model.setNewUser(false);
			return getEntityManager().merge(model);
		}
		model.getSalary().setEmployee(model);
		model.getAsset().forEach(assetModel -> assetModel.setEmployee(model));
		model.setActive(true);
		model.setNewUser(true);
		persist(model);
		return model;
	}

	public EmployeeModel findByMobile(String mobile) {
		return find("mobile", mobile).firstResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
