package com.koala.rest.rest;

import com.koala.rest.model.Stations;
import com.koala.rest.repository.StationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.data.geo.Metrics.MILES;

@RestController
@Slf4j
@RequiredArgsConstructor
class StoresController {

	private static final List<Distance> DISTANCES = Arrays.asList(new Distance(0.5, MILES), new Distance(1, MILES),
			new Distance(2, MILES));
	private static final Distance DEFAULT_DISTANCE = new Distance(100, Metrics.KILOMETERS);
	private static final Map<String, Point> KNOWN_LOCATIONS;

	static {

		Map<String, Point> locations = new HashMap<>();

		locations.put("Bezuidenhoutseweg", new Point(4.335370, 52.087868));

		KNOWN_LOCATIONS = Collections.unmodifiableMap(locations);
	}

	private final StationsRepository repository;

	/**
	 * Looks up the stores in the given distance around the given location.
	 * 
	 * @param model the {@link Model} to populate.
	 * @param location the optional location, if none is given, no search results will be returned.
	 * @param distance the distance to use, if none is given the {@link #DEFAULT_DISTANCE} is used.
	 * @param pageable the pagination information
	 * @return page of results
	 */
    @RequestMapping(
            value = "/api/koala/fuel/by-location",
            method = RequestMethod.GET)
	public ResponseEntity byLocation(Model model, @RequestParam Optional<Point> location, @RequestParam Optional<Distance> distance,
			Pageable pageable) {
		Point point = location.orElse(KNOWN_LOCATIONS.get("Bezuidenhoutseweg"));

		Page<Stations> stationsPage = repository.findByLocNear(point, distance.orElse(DEFAULT_DISTANCE), pageable);

		model.addAttribute("stations", stationsPage);
		model.addAttribute("distances", DISTANCES);
		//model.addAttribute("selectedDistance", distance.orElse(DEFAULT_DISTANCE));
		model.addAttribute("location", point);
		//model.addAttribute("locations", KNOWN_LOCATIONS);

		return new ResponseEntity<> (stationsPage, HttpStatus.OK);
	}
}
