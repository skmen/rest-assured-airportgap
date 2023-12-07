package dataModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder
public class Airport {

    private Attributes attribute;
    private String id;
    private String type;

    public Attributes getAttributes(){ return attribute; }
    public void setAttributes(Attributes attributes){ this.attribute = attributes;}

    public String getId(){ return id; }
    public void setId(String ID){this.id = ID;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public static class Attributes{
        private int altitude;
        private String city;
        private String country;
        private String iata;
        private String icao;
        private String latitude;
        private String longitude;
        private String name;
        private String timezone;

        public int getAltitude(){return altitude;}
        public void setAltitude(int altitude){this.altitude = altitude;}

        public String getCity(){return city;}
        public void setCity(String city){this.city=city;}

        public String getCountry(){return country;}
        public void setCountry(String country){this.country=country;}

        public String getIata(){return iata;}
        public void setIata(String iata){this.iata=iata;}

        public String getIcao(){return icao;}
        public void setIcao(String icao){this.icao=icao;}

        public String getLatitude(){return latitude;}
        public void setLatitude(String latitude){this.latitude=latitude;}

        public String getLongitude(){return longitude;}
        public void setLongitude(String longitude){this.longitude=longitude;}

        public String getName(){return name;}
        public void setName(){this.name = name;}

        public String timezone(){return timezone;}
        public void setTimezone(){this.timezone=timezone;}
    }
}
