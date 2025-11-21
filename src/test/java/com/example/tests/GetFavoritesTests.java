package com.example.tests;

import com.example.utils.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.api_services.FavoritesService;

public class GetFavoritesTests extends BaseTest {

    private FavoritesService favoritesService;

    @BeforeClass
    public void setup() {
        super.setup();
        favoritesService = new FavoritesService(reqSpec);
    }

    @Test
    public void VerifyGetFavoritesReturns200Status(){
        favoritesService.getFavorites()
                .then()
                .assertThat().statusCode(200);
    }
}
