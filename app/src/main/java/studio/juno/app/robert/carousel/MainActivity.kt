package studio.juno.app.robert.carousel

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import studio.juno.app.robert.carousel.item.Item
import studio.juno.app.robert.carousel.item.MyItem
import kotlin.math.abs

/**
 *
 *
 */
class MainActivity : AppCompatActivity() {

    /* */
    private val linearSmoothScroller: LinearSmoothScroller by lazy {
        object : LinearSmoothScroller(this) {
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float =
                65f / displayMetrics.densityDpi
        }
    }

    /**
     *
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        setUpViews()
    }

    /**
     *
     *
     */
    private fun initViewPager(){
        vpCarousel.apply {
            val items = getItems()
            adapter = CarouselAdapter(items)
            offscreenPageLimit = 3
            setPageTransformer(getCompositePageTransformer())
            registerOnPageChangeCallback(getOnPageChangeCallback(items))
            setCurrentItem((adapter as CarouselAdapter).getInitialPosition(), false)
        }
    }

    /**
     *
     * @return [ViewPager2.OnPageChangeCallback]
     */
    private fun getOnPageChangeCallback(items: List<Item>) = object : ViewPager2.OnPageChangeCallback() {

        /**
         *
         * @param position
         */
        override fun onPageSelected(position: Int) {
            val item = items[position % items.size]
            Toast.makeText(this@MainActivity, item.label, Toast.LENGTH_SHORT).show()
            super.onPageSelected(position)
        }

    }

    /**
     *
     *
     */
    private fun setUpViews(){
        ivBack.setOnClickListener {
            vpCarousel.smoothScrollTo(vpCarousel.currentItem - 1)
        }
        ivNext.setOnClickListener {
            vpCarousel.smoothScrollTo(vpCarousel.currentItem + 1)
        }
    }

    /**
     *
     * @param position
     */
    private fun ViewPager2.smoothScrollTo(position: Int){
        linearSmoothScroller.targetPosition = position
        ((getChildAt(0) as RecyclerView).layoutManager as LinearLayoutManager)
            .startSmoothScroll(linearSmoothScroller)
    }

    /**
     *
     * @return [CompositePageTransformer]
     */
    private fun getCompositePageTransformer() = CompositePageTransformer().apply {
        addTransformer { page, position ->
            page.scaleX = 0.53f + (1 - abs(position)) * 0.47f
            page.scaleY = 0.53f + (1 - abs(position)) * 0.47f
        }
    }

    /**
     *
     * @return [List]
     */
    private fun getItems() : List<Item> = listOf(
        MyItem(R.drawable.ic_launcher_foreground, "Hi", "Any"),
        MyItem(R.drawable.ic_launcher_foreground, "world!", "Any"),
        MyItem(R.drawable.ic_launcher_foreground, "Gabo", "Any"),
        MyItem(R.drawable.ic_launcher_foreground, "Payment", "Any")
    )

}
