fun main() {
    val wallService = WallService()
    val post = Post(text = "Абоба")
    println(wallService.add(post).id)
    val post1 = Post(text = "Абоба1")
    println(wallService.add(post1).id)
    println(wallService.update(post))
    val post2 = Post(text = "авава")
    println(wallService.update(post2))
}