package `in`.develoop.kangyasin.gicmobile

import `in`.develoop.kangyasin.gicmobile.adapter.ContactAdapter
import `in`.develoop.kangyasin.gicmobile.model.ContactDataItem
import `in`.develoop.kangyasin.gicmobile.presenter.CrudView
import `in`.develoop.kangyasin.gicmobile.presenter.Presenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), CrudView {
  private lateinit var presenter: Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter = Presenter(this)
    presenter.getData()

    btnTambah.setOnClickListener {
      startActivity<FormContactActivity>()
      finish()
    }
  }

  override fun onSuccessGet(data: List<ContactDataItem>?) {
    rvCategory.adapter = ContactAdapter(data, object : ContactAdapter.onClickItem {
      override fun clicked(item: ContactDataItem?) {
        startActivity<FormContactActivity>("ContactDataItem" to item)
      }

      override fun delete(item: ContactDataItem?) {
        presenter.hapusData(item?.contactId)
        startActivity<MainActivity>()
        finish()
      }

    })
  }

  override fun onFailedGet(msg: String) {
  }

  override fun onSuccessDelete(msg: String) {
    presenter.getData()

  }

  override fun onErrorDelete(msg: String) {
    alert {
      title = "Error Delete Data"
    }.show()
  }

  override fun successAdd(msg: String) {
  }

  override fun errorAdd(msg: String) {
  }

  override fun onSuccessUpdate(msg: String) {
  }

  override fun onErrorUpdate(msg: String) {
  }
}
