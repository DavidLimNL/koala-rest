package com.koala.tanken.api.controller;

import com.amazonaws.geo.GeoDataManager;
import com.amazonaws.geo.GeoDataManagerConfiguration;
import com.amazonaws.geo.model.GeoPoint;
import com.amazonaws.geo.model.QueryRadiusRequest;
import com.amazonaws.geo.model.QueryRadiusResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Map;

public class FuelStationsController {

    static AmazonDynamoDBClient amazonDynamoDBClient;
    static DynamoDB dynamoDB;
    static GeoDataManager geoDataManager;

    static String stationsTableName = "Stations";

    static {
        amazonDynamoDBClient = new AmazonDynamoDBClient();
        amazonDynamoDBClient.setEndpoint("http://localhost:8000");
        dynamoDB = new DynamoDB(amazonDynamoDBClient);
        setConfiguration(stationsTableName);
    }

    /**
     * Returns the stations within a set distance of the set coordinates
     */
    public List<Map<String, AttributeValue>> findStations(Point point, Distance radiusInMeters) {
        GeoPoint centerPoint = new GeoPoint(point.getY(), point.getX());

        QueryRadiusRequest queryRadiusRequest = new QueryRadiusRequest(centerPoint, radiusInMeters.getValue() * 1000);
        QueryRadiusResult queryRadiusResult = geoDataManager.queryRadius(queryRadiusRequest);

        return queryRadiusResult.getItem();
    }

    private static void setConfiguration(String tableName) {
        GeoDataManagerConfiguration config = new GeoDataManagerConfiguration(amazonDynamoDBClient, tableName)
                .withHashKeyAttributeName("stationHashKey")
                .withRangeKeyAttributeName("stationId")
                .withGeohashAttributeName("stationGeohash")
                .withGeoJsonAttributeName("stationGeoJson")
                .withGeohashIndexName("station-geohash-index");
        geoDataManager = new GeoDataManager(config);
    }
}
