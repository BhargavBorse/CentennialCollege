package com.example.khanjan_bhargav_mapd711_assignment4
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.khanjan_bhargav_mapd711_assignment4.model.Pizza

class PizzaAdaptor(
    private val pizza: List<Pizza>
) : RecyclerView.Adapter<PizzaAdaptor.PizzaViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list, parent, false)
        return PizzaViewModel(view)
    }

    override fun getItemCount() = pizza.size

    override fun onBindViewHolder(holder: PizzaViewModel, position: Int) {
        holder.bind(pizza[position])
    }

    inner class PizzaViewModel(private val userview: View) : ViewHolder(userview) {

        private val pizzaName: TextView = userview.findViewById(R.id.productNameTextView)
        private val price: TextView = userview.findViewById(R.id.priceTextView)
        private val image: ImageView = userview.findViewById(R.id.productImageView)

        val plusButton: Button = userview.findViewById(R.id.buttonPlus)
        val minusButton: Button = userview.findViewById(R.id.buttonMinus)
        val buyButton: Button = userview.findViewById(R.id.buyPizzaButton)
        val textView: TextView = userview.findViewById(R.id.qtyTextView)


        private var quantity: Int = 0

        fun bind(pizza: Pizza) {
            pizzaName.text = pizza.pizzaName
            price.text = "$ ${pizza.price}"
            val assetImagePath = "file:///android_asset/${pizza.image}"
            Glide.with(userview.context)
                .load(assetImagePath)
                .into(image)// Set


            plusButton.setOnClickListener {
                if (quantity < 10) {
                    quantity++
                    textView.text = quantity.toString()

                }
                // Check and set visibility after incrementing the quantity
                if (quantity == 10) {
                    plusButton.isClickable = false
//                    plusButton.setBackgroundColor(Color.RED)
                }
                plusButton.isClickable = true
            }

            // Minus button click listener
            minusButton.setOnClickListener {
                if (quantity > 0) {
                    quantity--
                    textView.text = quantity.toString()

                }
                // Check and set visibility after decrementing the quantity
                if (quantity == 0) {
                    minusButton.isClickable = false
                }
//                plusButton.visibility = View.VISIBLE
                minusButton.isClickable = true
            }

            buyButton.setOnClickListener{
                if(quantity==0){
                    buyButton.isClickable = false
                    Toast.makeText(
                        userview.context,
                        "Please Select Quantity",
                        Toast.LENGTH_LONG
                    ).show()
                }else{
                    val intent = Intent(userview.context,OrderReview::class.java)
                    var totalPrice : Int = 0
                    totalPrice = quantity*pizza.price

                    intent.putExtra("productName",pizzaName.text.toString())
                    intent.putExtra("productPrice",pizza.price.toString())
                    intent.putExtra("productTotalPrice",totalPrice.toString())
                    intent.putExtra("productQty",quantity.toString())
                    intent.putExtra("productImage",assetImagePath)
                    intent.putExtra("productId",pizza.productId.toString())
                    userview.context.startActivity(intent)
                }

                buyButton.isClickable = true

            }
        }
    }
}