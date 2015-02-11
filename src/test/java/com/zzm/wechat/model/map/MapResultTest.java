package com.zzm.wechat.model.map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapResultTest {

    @Test
    public void should_parse_json_to_map_correct() throws Exception {
        String json = "{\n" +
                "    \"status\": 0,\n" +
                "    \"result\": {\n" +
                "        \"location\": {\n" +
                "            \"lng\": 104.06011997317,\n" +
                "            \"lat\": 30.577634867784\n" +
                "        },\n" +
                "        \"formatted_address\": \"四川省成都市武侯区锦悦西二路\",\n" +
                "        \"business\": \"石羊\",\n" +
                "        \"addressComponent\": {\n" +
                "            \"city\": \"成都市\",\n" +
                "            \"country\": \"中国\",\n" +
                "            \"direction\": \"\",\n" +
                "            \"distance\": \"\",\n" +
                "            \"district\": \"武侯区\",\n" +
                "            \"province\": \"四川省\",\n" +
                "            \"street\": \"锦悦西二路\",\n" +
                "            \"street_number\": \"\",\n" +
                "            \"country_code\": 0\n" +
                "        },\n" +
                "        \"poiRegions\": [\n" +
                "            {\n" +
                "                \"direction_desc\": \"内\",\n" +
                "                \"name\": \"锦城公园\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"sematic_description\": \"锦城公园内,呈祥东馆(南苑店)西北320米\",\n" +
                "        \"cityCode\": 75\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        MapResult mapResult = mapper.readValue(json, MapResult.class);

        assertThat(mapResult.getStatus(), is(0));

        MapResultData resultData = mapResult.getResult();
        assertThat(resultData.getFormattedAddress(), is("四川省成都市武侯区锦悦西二路"));
        assertThat(resultData.getBusiness(), is("石羊"));
        assertThat(resultData.getSematicDescription(), is("锦城公园内,呈祥东馆(南苑店)西北320米"));
        assertThat(resultData.getCityCode(), is(75));

        Location location = resultData.getLocation();
        assertThat(location.getLatitude(), is(30.577634867784));
        assertThat(location.getLongitude(), is(104.06011997317));

        Address address = resultData.getAddressComponent();
        assertThat(address.getCity(), is("成都市"));
        assertThat(address.getCountry(), is("中国"));
        assertThat(address.getDistrict(), is("武侯区"));
        assertThat(address.getProvince(), is("四川省"));
        assertThat(address.getStreet(), is("锦悦西二路"));
        assertThat(address.getCountryCode(), is(0));

        List<PoiRegion> poiRegions = resultData.getPoiRegions();
        assertThat(poiRegions.size(), is(1));
        assertThat(poiRegions.get(0).getDirectionDesc(), is("内"));
        assertThat(poiRegions.get(0).getName(), is("锦城公园"));
    }

}