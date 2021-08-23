data class Post (
    var id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 20082021,
    val text: String,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val comments: Any? = null,
    val copyright: Any? = null,
    val likes: Any = 0,
    val reposts: Any = 0,
    val views: Any = 1,
    val postType: String = "default",
    val postSource: Any? = null,
    val geo: Any? = null,
    val signerId: Int = 0,
    val copyHistory: Any? = null,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Any? = null,
    val postponedId: Int? = null
    )