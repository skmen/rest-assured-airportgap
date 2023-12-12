package airports;

import helper.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;

public class BaseTest {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().setBaseUri(ConfigReader.getInstance().getBaseUrl())
                .setBasePath("/api")
                .addHeader("Authorization", "Bearer " + ConfigReader.getInstance().getToken())
                .setContentType(ContentType.JSON).build();
    }


}