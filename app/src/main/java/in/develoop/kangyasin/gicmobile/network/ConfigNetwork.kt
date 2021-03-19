package `in`.develoop.kangyasin.gicmobile.network

import `in`.develoop.kangyasin.gicmobile.model.ResultContact
import `in`.develoop.kangyasin.gicmobile.model.ResultStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ConfigNetwork {

  fun getInterceptor() : OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(logging)
      .build()

    return okHttpClient
  }

  fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://e6c034d5561e.ngrok.io/")
      .client(getInterceptor())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  fun getService() = getRetrofit().create(ContactService::class.java)
}

interface ContactService{

  //Fungsi Create Data
  @FormUrlEncoded
  @POST("api/contacts/buat")
  fun addContact(
               @Field("name") name : String,
               @Field("phone") phone : String,
               @Field("email") email: String) : Call<ResultStatus>

  //Fungsi Get Data
  @GET("/api/contacts")
  fun getData() : Call<ResultContact>

  //Fungsi Delete Data
  @DELETE("api/contacts/hapus/{id}")
  fun deleteContact(@Path(value = "id", encoded = true) id: String?) : Call<ResultStatus>

  //Fungsi Update Data
  @FormUrlEncoded
  @PUT("api/contacts/ubah/{id}")
  fun updateContact(
                  @Path(value = "id", encoded = true) id: String?,
                  @Field("name") name: String,
                  @Field("phone") phone : String,
                  @Field("email") email : String) : Call<ResultStatus>
}
