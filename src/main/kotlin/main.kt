import attachments.*

fun main() {
    val wallService = WallService()

    val photo = Photo(1,1, 1, 1)
    val video = Video(1, 1, "Автор", "Описание")
    val audio = Audio(1, 1, "Автор", "Название")
    val doc = Document(1, 1, "Название", 1)
    val link = Link("Ссылка", "Название", "-", "Описание")

    wallService.addAttachment(photo)
    wallService.addAttachment(video)
    wallService.addAttachment(audio)
    wallService.addAttachment(doc)
    wallService.addAttachment(link)
}