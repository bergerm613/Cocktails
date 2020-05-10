import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailService
{
    @GET("/api/json/v1/1/search.php?")
    Call<CocktailFeed> getCocktails(@Query("s") String cocktailName);
}
