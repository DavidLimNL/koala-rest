package com.koala.rest.model;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import lombok.Value;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

/**
 * Entity to represent a {@link Stations}.
 */
@Value
@Document
public class Stations {

	String name;
	String postalCode;
	//Location loc;
    List<Fuel> fuelDetails;
    @GeoSpatialIndexed(type = GEO_2DSPHERE) Point loc;

	/**
	 * Value object to represent an {@link Address}.
	 * 
	 * @author Oliver Gierke
	 */
	@Value
	static class Location {

		String street, city, zip;

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return String.format("%s, %s %s", street, zip, city);
		}
	}

	@Value
    static class Fuel {
        String fuelType;
        Float price;
        Float moreOrLess;
    }
}
