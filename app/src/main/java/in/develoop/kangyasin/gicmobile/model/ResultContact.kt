package `in`.develoop.kangyasin.gicmobile.model

import com.google.gson.annotations.SerializedName

data class ResultContact(
  @field:SerializedName("message")
  val message: String? = null,

  @field:SerializedName("result")
  val result: List<ContactDataItem>? = null,

  @field:SerializedName("status")
  val status: Int? = null
)
