package airports;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;

public class BaseTest {

    @DataProvider(name = "airport_ids")
    public Object[][] airportId(){
        return new Object[][]{{"HGU"}, {"LAE"}, {"POM"}, {"WWK"}, {"UAK"}, {"GOH"},
                {"SFJ"}, {"THU"}, {"AEY"}, {"EGS"}, {"HFN"}, {"HZK"}, {"IFJ"}, {"KEF"},
                {"PFJ"}, {"RKV"}, {"SIJ"}, {"VEY"}, {"YAM"}, {"YAY"}, {"YAZ"}, {"YBB"},
                {"YBC"}, {"YBG"}, {"YBK"}, {"YBL"}, {"YBR"}, {"YCB"}};
    }

    static String token = "4BcTRAKUVZcS7MtFz3CCHioU";

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().setBaseUri("https://airportgap.com/")
                .setBasePath("/api")
                .addHeader("Authorization","Bearer " + token)
                .setContentType(ContentType.JSON).build();
    }


}