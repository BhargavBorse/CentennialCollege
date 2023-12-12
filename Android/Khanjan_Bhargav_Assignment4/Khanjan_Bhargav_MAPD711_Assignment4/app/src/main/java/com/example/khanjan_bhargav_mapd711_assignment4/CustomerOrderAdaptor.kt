package com.example.khanjan_bhargav_mapd711_assignment4
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.khanjan_bhargav_mapd711_assignment4.db.OrdersRepository
import com.example.khanjan_bhargav_mapd711_assignment4.db.PizzaDatabase
import com.example.khanjan_bhargav_mapd711_assignment4.model.Orders
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.OrderViewModelFactory
import com.example.khanjan_bhargav_mapd711_assignment4.viewModel.PizzaViewModel
import kotlinx.coroutines.launch

class CustomerOrderAdaptor(
    private val order: List<Orders>
) : RecyclerView.Adapter<CustomerOrderAdaptor.OrderViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_order_list, parent, false)
        return OrderViewModel(view)
    }

    override fun getItemCount() = order.size

    override fun onBindViewHolder(holder: OrderViewModel, position: Int) {
        holder.bind(order[position])
    }


    inner class OrderViewModel(private val userview: View) : ViewHolder(userview) {

        private val productName: TextView = userview.findViewById(R.id.productNameTextViewCustomer)
        private val qty: TextView = userview.findViewById(R.id.productQtyTextViewCustomer)
        private val image: ImageView = userview.findViewById(R.id.productImageViewCustomer)
        private val orderDate: TextView = userview.findViewById(R.id.productOrderDateTextViewCustomer)
        private val status: TextView = userview.findViewById(R.id.productStatusTextViewCustomer)


        val repository =
            OrdersRepository(PizzaDatabase.getDatabaseInstance(userview.context).OrdersDao())
        val viewModelFactory = OrderViewModelFactory(repository)


        fun bind(order: Orders) {

            productName.text =order.productName

            val assetImagePath = "${order.image}"
            Glide.with(userview.context)
                .load(assetImagePath)
                .into(image)// Se

            qty.text = "Qty: ${order.qty}"
            orderDate.text = "Order Date: ${order.orderDate}"
            status.text = order.orderStatus

        }
    }
}