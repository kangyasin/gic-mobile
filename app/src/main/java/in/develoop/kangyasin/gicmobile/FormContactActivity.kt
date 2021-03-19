package `in`.develoop.kangyasin.gicmobile

import `in`.develoop.kangyasin.gicmobile.model.ContactDataItem
import `in`.develoop.kangyasin.gicmobile.presenter.CrudView
import `in`.develoop.kangyasin.gicmobile.presenter.Presenter
import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_form.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class FormContactActivity : AppCompatActivity(), CrudView {

  private lateinit var presenter: Presenter
  @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

      presenter = Presenter(this)
      val itemDataItem = intent.getSerializableExtra("ContactDataItem")

      if (itemDataItem == null){
        btnAction.text = "Tambah"
        btnAction.onClick {
          presenter.addData(
            etName.text.toString(),
            etPhone.text.toString(),
            etEmail.text.toString())
        }

      }else if (itemDataItem != null){
        btnAction.text = "Update"
        val item = itemDataItem as ContactDataItem?
        etName.setText(item?.contactName.toString())
        etPhone.setText(item?.contactPhone.toString())
        etEmail.setText(item?.contactEmail.toString())
        btnAction.onClick {
          presenter.updateData(
            item?.contactId ?: "",
            etName.text.toString(),
            etPhone.text.toString(),
            etEmail.text.toString())
          finish()
        }

      }
    }
  override fun successAdd(msg: String) {
    startActivity<MainActivity>()
    finish()
  }

  override fun errorAdd(msg: String) {}

  override fun onSuccessUpdate(msg: String) {
    startActivity<MainActivity>()
    finish()
  }

  override fun onErrorUpdate(msg: String) {}

  override fun onSuccessGet(data: List<ContactDataItem>?) {}

  override fun onFailedGet(msg: String) {}

  override fun onSuccessDelete(msg: String) {}

  override fun onErrorDelete(msg: String) {}
}
