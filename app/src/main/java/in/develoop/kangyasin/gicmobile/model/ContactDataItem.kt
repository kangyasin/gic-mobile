package `in`.develoop.kangyasin.gicmobile.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContactDataItem : Serializable{

  @field:SerializedName("id")
  val contactId: String? = null

  @field:SerializedName("name")
  val contactName: String? = null

  @field:SerializedName("phone")
  val contactPhone: String? = null

  @field:SerializedName("email")
  val contactEmail: String? = null

}
