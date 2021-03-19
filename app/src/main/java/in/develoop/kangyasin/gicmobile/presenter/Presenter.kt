package `in`.develoop.kangyasin.gicmobile.presenter

import `in`.develoop.kangyasin.gicmobile.model.ResultContact
import `in`.develoop.kangyasin.gicmobile.model.ResultStatus
import `in`.develoop.kangyasin.gicmobile.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Response
import android.util.Log

class Presenter (val crudView: CrudView) {

  //Fungsi GetData
  fun getData(){
    ConfigNetwork.getService().getData()
      .enqueue(object : retrofit2.Callback<ResultContact>{
        override fun onFailure(call: Call<ResultContact>, t: Throwable) {
          crudView.onFailedGet(t.localizedMessage)
          Log.d("Error", "Error Data")
        }

        override fun onResponse(call: Call<ResultContact>, response: Response<ResultContact>) {
          if(response.isSuccessful){
            val status = response.body()?.status
            if (status == 200){
              val data = response.body()?.result
              crudView.onSuccessGet(data)
            } else{
              crudView.onFailedGet("Error $status")
            }
          }
        }

      })
  }


  //Add data
  fun addData(name : String, phone : String, email : String){
    ConfigNetwork.getService()
      .addContact(name, phone, email)
      .enqueue(object : retrofit2.Callback<ResultStatus>{
        override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
          crudView.errorAdd(t.localizedMessage)
        }

        override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
          if (response.isSuccessful && response.body()?.status == 200) {
            crudView.successAdd(response.body()?.message ?: "")
          }else {
            crudView.errorAdd(response.body()?.message ?: "")
          }
        }

      })
  }


  //Hapus Data
  fun hapusData(id: String?){
    ConfigNetwork.getService()
      .deleteContact(id)
      .enqueue(object : retrofit2.Callback<ResultStatus>{
        override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
          crudView.onErrorDelete(t.localizedMessage)
        }

        override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
          if (response.isSuccessful && response.body()?.status == 200){
            crudView.onSuccessDelete(response.body()?.message ?: "")
          } else {
            crudView.onErrorDelete(response.body()?.message ?: "")
          }
        }

      })
  }

  //Update Data
  fun updateData(id: String, name: String, phone: String, email: String){
    ConfigNetwork.getService()
      .updateContact(id, name, phone, email)
      .enqueue(object : retrofit2.Callback<ResultStatus>{
        override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
          crudView.onErrorUpdate(t.localizedMessage)
        }

        override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
          if (response.isSuccessful && response.body()?.status == 200){
            crudView.onSuccessUpdate(response.body()?.message ?: "")
          }else{
            crudView.onErrorUpdate(response.body()?.message ?: "")
          }

        }

      })
  }

}
