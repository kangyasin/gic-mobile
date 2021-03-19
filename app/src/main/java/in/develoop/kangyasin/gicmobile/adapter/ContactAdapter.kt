package `in`.develoop.kangyasin.gicmobile.adapter

import `in`.develoop.kangyasin.gicmobile.R
import `in`.develoop.kangyasin.gicmobile.model.ContactDataItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_data_contact.view.*

import org.jetbrains.anko.sdk27.coroutines.onClick

class ContactAdapter (val data: List<ContactDataItem>? , private val click: onClickItem) : RecyclerView.Adapter<ContactAdapter.MyHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_contact,parent,false)
    return MyHolder(view)
  }

  override fun getItemCount() = data?.size ?: 0

  override fun onBindViewHolder(holder: MyHolder, position: Int) {
    holder.onBind(data?.get(position))
    holder.itemView.onClick {
      click.clicked(data?.get(position))
    }
    holder.itemView.btnHapus.setOnClickListener {
      click.delete(data?.get(position))
    }
  }

  class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(get: ContactDataItem?) {
      itemView.tvName.text = get?.contactName
      itemView.tvPhone.text = get?.contactPhone
      itemView.tvEmail.text = get?.contactEmail
    }
  }

  interface onClickItem{
    fun clicked (item: ContactDataItem?)
    fun delete(item: ContactDataItem?)

  }
}
