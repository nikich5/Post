package attachments

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    override val type: String = "Photo"
) : Attachment

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    override val type: String = "Video"
) : Attachment

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    override val type: String = "Audio"
) : Attachment

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    override val type: String = "Document"
) : Attachment

data class Link(
    val url: String,
    val title: String,
    val caption: String,
    val description: String,
    override val type: String = "Link"
) : Attachment

data class PhotoAttachment(
    override val type: String = "Photo",
    val photo: Photo
) : Attachment

data class VideoAttachment(
    override val type: String = "Video",
    val video: Video
) : Attachment

data class AudioAttachment(
    override val type: String = "Audio",
    val audio: Audio
) : Attachment

data class DocumentAttachment(
    override val type: String = "Document",
    val document: Document
) : Attachment

data class LinkAttachment(
    override val type: String = "Link",
    val link: Link
) : Attachment