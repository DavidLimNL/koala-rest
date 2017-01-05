package com.koala.tanken.api.rest;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.koala.tanken.api.controller.FuelStationsController;
import com.koala.tanken.api.util.JacksonConverterException;
import com.koala.tanken.api.util.JacksonConverterImpl;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
class FuelStationsResource {

	@RequestMapping(
			value = "/api/koala/fuel/by-location",
			method = RequestMethod.GET)
	public ResponseEntity byLocation(@RequestParam Point location, @RequestParam Distance radius) {

	    JsonNode response;

        FuelStationsController controller = new FuelStationsController();
        List<Map<String, AttributeValue>> itemList = controller.findStations(location, radius);

        try {
            response = new JacksonConverterImpl().itemListToJsonArray(itemList);
        } catch (JacksonConverterException e) {
            log.error(e.getMessage());
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }

		return new ResponseEntity<> (response, HttpStatus.OK);
	}
}
