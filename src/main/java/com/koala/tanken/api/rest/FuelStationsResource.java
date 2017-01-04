package com.koala.tanken.api.rest;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.koala.tanken.api.controller.FuelStationsController;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.data.geo.Metrics.MILES;

@RestController
@Slf4j
@RequiredArgsConstructor
class FuelStationsResource {

	private static final List<Distance> DISTANCES = Arrays.asList(new Distance(0.5, MILES), new Distance(1, MILES),
			new Distance(2, MILES));
	private static final Distance DEFAULT_DISTANCE = new Distance(100, Metrics.KILOMETERS);
	private static final Map<String, Point> KNOWN_LOCATIONS;

	static {

		Map<String, Point> locations = new HashMap<>();
		locations.put("Bezuidenhoutseweg", new Point(4.335370, 52.087868));
		KNOWN_LOCATIONS = Collections.unmodifiableMap(locations);
	}

	@RequestMapping(
			value = "/api/koala/fuel/by-location",
			method = RequestMethod.GET)
	public ResponseEntity byLocation(@RequestParam Point location, @RequestParam Distance radius) {

        FuelStationsController controller = new FuelStationsController();
        List<Map<String, AttributeValue>> response = controller.findStations(location, radius);

		return new ResponseEntity<> (response, HttpStatus.OK);
	}
}
