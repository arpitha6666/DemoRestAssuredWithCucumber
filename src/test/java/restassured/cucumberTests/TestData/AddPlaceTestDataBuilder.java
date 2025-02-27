package restassured.cucumberTests.TestData;

import model.mapsapi.AddPlace;
import model.mapsapi.Location;

import java.util.Arrays;
import java.util.List;

public class AddPlaceTestDataBuilder {

    public AddPlace getAddPlaceData(String name, String language, String address){
        AddPlace place = new AddPlace();
        Location loc = new Location();
        List<String> arrTypes = Arrays.asList(new String[]{"Shoe park", "shoe"});
        loc.setLat(-38.383494);
        loc.setLng(37.383494);
        place.setAccuracy(50);
        place.setAddress(address);
        place.setName(name);
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://rahulshettyacademy.com/");
        place.setLanguage(language);
        place.setLocation(loc);
        place.setTypes(arrTypes);
        return place;
    }

    public String getDeleteData(String placeId){
        return "{\n" +
                "\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }
}
