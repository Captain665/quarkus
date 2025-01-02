package org.acme.v1.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.v1.models.CompanyModel;

@ApplicationScoped
public class CompanyRepository implements PanacheRepository<CompanyModel> {
}
