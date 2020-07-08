package studio.juno.app.robert.carousel.item

/**
 *
 *
 */
class MyItem(
    override val imageResource: Int,
    override val label: String,
    val anotherAttribute: Any
) : Item
