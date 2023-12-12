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
import androidx.core.view.isVisible
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

class OrderAdaptor(
    private val order: List<Orders>
) : RecyclerView.Adapter<OrderAdaptor.OrderViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_list, parent, false)
        return OrderViewModel(view)
    }

    override fun getItemCount() = order.size

    override fun onBindViewHolder(holder: OrderViewModel, position: Int) {
        holder.bind(order[position])
    }


    inner class OrderViewModel(private val userview: View) : ViewHolder(userview) {

        private val productName: TextView = userview.findViewById(R.id.productNameTextViewAdmin)
        private val qty: TextView = userview.findViewById(R.id.productQtyTextViewAdmin)
        private val image: ImageView = userview.findViewById(R.id.productImageViewAdmin)
        private val orderDate: TextView = userview.findViewById(R.id.productOrderDateTextViewAdmin)
        private val status: TextView = userview.findViewById(R.id.productStatusTextViewAdmin)



        val changeButton: Button = userview.findViewById(R.id.changeStatusAdmin)

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

            if(status.text == "In-Process"){
                changeButton.isVisible = true
            }else{
                changeButton.isVisible = false
            }

            changeButton.setOnClickListener{
                    val intent = Intent(userview.context,ConfirmChangeStatus::class.java)
                    intent.putExtra("productName",productName.text.toString())
                    intent.putExtra("productQty",order.qty.toString())
                    intent.putExtra("productImage",assetImagePath)
                    intent.putExtra("orderId",order.orderId.toString())
                    intent.putExtra("orderDate",order.orderDate)
                    intent.putExtra("orderStatus",status.text.toString())
                    intent.putExtra("customerId",order.customerId.toString())
                    intent.putExtra("productId",order.productId.toString())
                    userview.context.startActivity(intent)


            }
        }
    }
}