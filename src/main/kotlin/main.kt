import attachments.*

fun main() {
    val wallService = WallService()

    val post1 = Post(text = "Пост 1")
    val post2 = Post(text = "Пост 2")

    wallService.add(post1)
    wallService.add(post2)
    wallService.showPosts()

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

    val comment1 = Comment(0, 2, text = "Комментарий1")
    val comment2 = Comment(0, 2, text = "Комментарий2")

    wallService.createComment(comment1)
    wallService.createComment(comment2)

    wallService.reportComment(comment2, 2)
}