package com.koala.rest.service;

import com.koala.rest.model.Stations;

import java.util.Collection;

/**
 * An interface to define all the public business behaviours or operations 
 * for the {@link Stations} entity.
 */
public interface StationService {

	/**
	 * Final all Stations entities.
	 * @return a collection of Stations objects.
	 */
	Collection<Stations> findAll();

}
