package com.xebia.covidtracker.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.stereotype.Repository;

import com.xebia.covidtracker.entity.CovidDataEntity;

@Repository
public class CovidFilterRepoImpl implements CovidFilterRepo {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CovidDataEntity> findAll(List<String> locations, String type, String selected) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		Metamodel m = em.getMetamodel();
		EntityType<CovidDataEntity> CovidDataEntity_ = m.entity(CovidDataEntity.class);
		CriteriaQuery<CovidDataEntity> query = cb.createQuery(CovidDataEntity.class);
		Root<CovidDataEntity> root = query.from(CovidDataEntity.class);
		In<String> inClause = cb.in(root.get("location"));
		for(String loc:locations) {
			inClause.value(loc);
		}
		query.where(inClause);
		typeOperation(type, selected, cb, CovidDataEntity_, query, root);
		TypedQuery<CovidDataEntity> tquery = em.createQuery(query);
		return tquery.getResultList();
	}

	@Override
	public List<CovidDataEntity> findAll(String type, String selected) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		Metamodel m = em.getMetamodel();
		EntityType<CovidDataEntity> CovidDataEntity_ = m.entity(CovidDataEntity.class);
		CriteriaQuery<CovidDataEntity> query = cb.createQuery(CovidDataEntity.class);
		Root<CovidDataEntity> root = query.from(CovidDataEntity.class);
		typeOperation(type, selected, cb, CovidDataEntity_, query, root);
		TypedQuery<CovidDataEntity> tquery = em.createQuery(query);
		return tquery.getResultList();
	}

	private void typeOperation(String type, String selected, CriteriaBuilder cb,
			EntityType<CovidDataEntity> CovidDataEntity_, CriteriaQuery<CovidDataEntity> query,
			Root<CovidDataEntity> root) {
		if("min".equalsIgnoreCase(type)) {
			query.multiselect(cb.min(root.get(CovidDataEntity_.getSingularAttribute(selected,Long.class))));
		}else if("max".equalsIgnoreCase(type)) {
			query.multiselect(cb.max(root.get(CovidDataEntity_.getSingularAttribute(selected,Long.class))));
		}else {
			query.multiselect(cb.sum(root.get(CovidDataEntity_.getSingularAttribute(selected,Long.class))));
		}
	}

	@Override
	public List<CovidDataEntity> findAll(List<String> locations) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<CovidDataEntity> query = cb.createQuery(CovidDataEntity.class);
		Root<CovidDataEntity> root = query.from(CovidDataEntity.class);
		In<String> inClause = cb.in(root.get("location"));
		for(String loc:locations) {
			inClause.value(loc);
		}
		query.where(inClause);
		TypedQuery<CovidDataEntity> tquery = em.createQuery(query);
		return tquery.getResultList();
	}

}
