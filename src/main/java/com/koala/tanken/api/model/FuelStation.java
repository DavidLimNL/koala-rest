package com.koala.tanken.api.model;

import java.util.List;

import lombok.Value;


@Value
public class FuelStation {

	String name;
	String postalCode;
	//Location loc;
    List<Fuel> fuelDetails;

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
