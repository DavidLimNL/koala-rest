package com.koala.rest.service;

import com.koala.rest.model.Stations;
import com.koala.rest.repository.StationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Encapsulates all business behaviour and operations for the 
 * Stations entity.
 * 
 */
@Service
@Transactional
public class StationServiceBean implements StationService {

	@Autowired
	private StationsRepository dishRepo;

	@Override
	public Collection<Stations> findAll() {
		Collection<Stations> stations = (Collection<Stations>) dishRepo.findAll();

		return stations;
	}
	
}
