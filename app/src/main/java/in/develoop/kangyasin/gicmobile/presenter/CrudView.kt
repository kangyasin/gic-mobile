package `in`.develoop.kangyasin.gicmobile.presenter

import `in`.develoop.kangyasin.gicmobile.model.ContactDataItem

interface CrudView {
  //Get data contact
  fun onSuccessGet(data: List<ContactDataItem>?)
  fun onFailedGet(msg : String)

  //Delete data contact
  fun onSuccessDelete(msg: String)
  fun onErrorDelete(msg: String)

  //Add data contact
  fun successAdd(msg : String)
  fun errorAdd(msg: String)

  //Update data contact
  fun onSuccessUpdate(msg : String)
  fun onErrorUpdate(msg : String)
}
