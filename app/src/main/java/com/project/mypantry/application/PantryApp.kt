package com.project.mypantry.application

import android.app.Application
import com.project.mypantry.objects.ResultsModel
import com.project.mypantry.objects.pantryResultsMod
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class PantryApp: Application() {
    lateinit var apiManager: ApiManager
    lateinit var apiManagerTwo: ApiManager

    lateinit var pantryManager: PantryListManager
    lateinit var recipeListManager: RecipeListManager
    lateinit var shoppingListManager: ShoppingListManager
    lateinit var glossaryManager: GlossaryManager
    lateinit var workManager: ExpireWorkManager
    lateinit var notificationManager: MessageNotificationManager
    lateinit var httpManager: HTTPManager
    private lateinit var recipeService: RecipeService
    private lateinit var recipeServiceTwo: RecipeService

    override fun onCreate() {
        super.onCreate()
        initManagers()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
//            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create()) // this will automatically apply Gson conversion :)
            .client(client)
            .build()

        /////////////Build Second HTTP call here //////////
        val retrofitCalltwo = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create()) // this will automatically apply Gson conversion :)
            .build()
        //////////////////////////////////////////
        recipeService = retrofit.create(RecipeService::class.java)
        recipeServiceTwo = retrofitCalltwo.create(RecipeService::class.java)
        // Load managers
        apiManager = ApiManager(recipeService,recipeServiceTwo)
        apiManagerTwo = ApiManager(recipeService,recipeServiceTwo)

    }

    interface RecipeService {
        @Headers("x-rapidapi-host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com", "x-rapidapi-key: 6d0dc23c60msh9a34ddaa986f734p12fe5ajsn57b0f1b0f01b")
        @GET("/recipes/findByIngredients")
        fun allRecipes(
            @Query("number") numRet: Int,
            @Query("ranking") showRank: Int,
            @Query("ignorePantry") pantryYN: Boolean,
            @Query("ingredients") allIngred: String
        ): Call<List<ResultsModel>>

        @GET("LiamAlbright/codesnippetspantry/master/Recipes.json")
        fun allPantryRep (): Call<pantryResultsMod>
    }


    /**
     * As we implement each manager, we should replace the placeholders in PantryApp.
     *
     * If you're designing the front end and need to use some of the managers, then replace Placeholder
     * with a dummy manager (e.g. PantryListManagerDummy) that returns hardcoded outputs for each function
     */
    private fun initManagers() {
        pantryManager = PantryListManagerStatic(this) as PantryListManager
        recipeListManager = RecipeListManagerStatic(this) as RecipeListManager
        shoppingListManager = ShoppingListManagerStatic(this) as ShoppingListManager
        glossaryManager = GlossaryManagerStatic(this) as GlossaryManager
        httpManager = HTTPManagerStatic(this) as HTTPManager // work in progress

        // for phase 2. Let's not focus on notifications for now
        workManager = ExpireWorkManager(this)
        notificationManager = MessageNotificationManager(this)
    }


}