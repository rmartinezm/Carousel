package studio.juno.app.robert.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import studio.juno.app.robert.carousel.item.Item

/**
 *
 * @param items
 */
class CarouselAdapter(
    private val items: List<Item>
) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    /**
     *
     * @param parent
     * @param viewType
     * @return [ViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_adapter, parent, false)
        )

    /**
     *
     * @return [Int]
     */
    override fun getItemCount(): Int {
        var itemCount = Int.MAX_VALUE
        while (itemCount % items.size != 0)
            itemCount--
        return itemCount
    }

    /**
     *
     * @return [Int]
     */
    fun getInitialPosition(): Int {
        var initialPosition = itemCount / 2
        while (initialPosition % items.size != 0)
            initialPosition--
        return initialPosition
    }

    /**
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[(position % items.size)]
        holder.apply {
            ivImage.setImageResource(item.imageResource)
            tvLabel.text = item.label
        }
    }

    /**
     *
     *
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /* */
        val ivImage: ImageView by lazy { itemView.findViewById<ImageView>(R.id.ivImage) }
        /* */
        val tvLabel: TextView by lazy { itemView.findViewById<TextView>(R.id.tvLabel) }

    }

}