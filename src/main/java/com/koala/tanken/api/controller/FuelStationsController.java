package com.koala.tanken.api.controller;

import com.amazonaws.geo.GeoDataManager;
import com.amazonaws.geo.model.GeoPoint;
import com.amazonaws.geo.model.QueryRadiusRequest;
import com.amazonaws.geo.model.QueryRadiusResult;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Map;

import static com.koala.tanken.api.database.DatabaseClient.setConfiguration;

public class FuelStationsController {

    private static GeoDataManager geoDataManager;
    private static String stationsTableName = "Stations";

    static {
        geoDataManager = setConfiguration(stationsTableName);
    }

    /**
     * Returns the stations within a set distance of the set coordinates
     */
    public List<Map<String, AttributeValue>> findStations(Point point, Distance radiusInMeters) {
        GeoPoint centerPoint = new GeoPoint(point.getY(), point.getX());

        QueryRadiusRequest queryRadiusRequest = new QueryRadiusRequest(centerPoint, radiusInMeters.in(Metrics.KILOMETERS).getValue() * 1000);
        QueryRadiusResult queryRadiusResult = geoDataManager.queryRadius(queryRadiusRequest);

        return queryRadiusResult.getItem();
    }

}
