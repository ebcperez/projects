import java.util.*;
import java.util.Map;

import processing.core.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class LifeExpectancy extends PApplet{
	UnfoldingMap map;
	
	Map<String,Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup(){
		size(800,600,OPENGL);
		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
		map = new UnfoldingMap(this,0,0,800,600,new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);//event handler
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	public void draw(){
		map.draw();
	}
	private Map<String,Float> loadLifeExpectancyFromCSV(String fileName){
		Map<String,Float> lifeExpMap = new HashMap<String,Float>();
		String[] rows = loadStrings(fileName);
		
		for(String row: rows){
			String[] columns = row.split(",");

			if(columns.length == 6 && !columns[5].equals("..")){
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[4], value);
			}
		}
		return lifeExpMap;
	}
	private void shadeCountries(){
		for(Marker marker: countryMarkers){
			String countryId = marker.getId();
			
			if(lifeExpByCountry.containsKey(countryId)){//checks value for life expectancy of countries
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int)map(lifeExp,40,90,10,255);//takes number in range(40,90(ages)),to new range(10,255(RGB code))
				marker.setColor(color(255-colorLevel,100,colorLevel));
			} else {
				marker.setColor(color(150,150,150));//for countries that doesn't have data it is grey
			}
		}
	}
}




































