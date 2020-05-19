import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailServiceFactory
{
    public CocktailService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CocktailService service = retrofit.create(CocktailService.class);
        return service;
    }
}
