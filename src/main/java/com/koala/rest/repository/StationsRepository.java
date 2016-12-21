package com.koala.rest.repository;

import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.koala.rest.model.QStations;
import com.koala.rest.model.Stations;

/**
 * Repository interface for out-of-the-box paginating access to {@link Stations}s and a query method to find Stations by
 * location and distance.
 */
public interface StationsRepository extends PagingAndSortingRepository<Stations, String>, QueryDslPredicateExecutor<Stations> {

	@RestResource(rel = "by-location")
	Page<Stations> findByAddressLocationNear(Point location, Distance distance, Pageable pageable);

	/**
	 * Tweak the Querydsl binding if collection resources are filtered.
	 * 
	 * @see org.springframework.data.web.querydsl.QuerydslBinderCustomizer#customize(org.springframework.data.web.querydsl.QuerydslBindings,
	 *      com.mysema.query.types.EntityPath)
	 */
	default void customize(QuerydslBindings bindings, QStations stations) {

		//bindings.bind(stations.address.city).first((path, value) -> path.endsWith(value));
		bindings.bind(String.class).first((StringPath path, String value) -> path.contains(value));
	}
}
