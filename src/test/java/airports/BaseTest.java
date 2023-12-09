package airports;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;

public class BaseTest {

    static String token = "4BcTRAKUVZcS7MtFz3CCHioU";

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().setBaseUri("https://airportgap.com/")
                .setBasePath("/api")
                .addHeader("Authorization","Bearer " + token)
                .setContentType(ContentType.JSON).build();
    }


}