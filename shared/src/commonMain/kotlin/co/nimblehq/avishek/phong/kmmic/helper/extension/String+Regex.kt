package co.nimblehq.avishek.phong.kmmic.helper.extension

fun String.isValidEmail(): Boolean {

    val expression = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b"
    val regex = Regex(expression)
    return isNotEmpty() && matches(regex)
}
