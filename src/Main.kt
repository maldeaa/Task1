fun main() {
    println("Привет! Это программа для сжатия строк. Введите сообщение, в котором некоторые буквы подряд повторяются, например: \"aabbccddd\". Обрати внимание, что ПРОБЕЛЫ НЕ СЖИМАЮТСЯ. Приятного пользования ;)\n")
    while (true) {
        try {
            println("Введите строку (или 'exit' для завершения):")
            val input = readLine()?.trim() ?: throw IllegalArgumentException("Пустой ввод")

            if (input.equals("exit", ignoreCase = true)) {
                println("Программа завершена.")
                break
            }

            if (!input.all { it.isLetter() || it.isWhitespace() }) {
                throw IllegalArgumentException("Строка должна содержать только буквы и пробелы")
            }

            if (input.length < 2) {
                throw IllegalArgumentException("Строка должна содержать хотя бы два символа")
            }

            val result = countConsecutiveChars(input)
            println("Результат: $result")
        } catch (e: IllegalArgumentException) {
            println("Ошибка: ${e.message}")
        } catch (e: Exception) {
            println("Неожиданная ошибка: ${e.message}")
        }
        finally {
            println();
        }
    }
}

fun countConsecutiveChars(str: String): String {
    if (str.isEmpty()) return ""

    val result = StringBuilder()
    var currentChar = str[0]
    var count = 1

    for (i in 1 until str.length) {
        if (str[i] == currentChar && currentChar != ' ') {
            count++
        } else {
            appendCharInfo(result, currentChar, count)
            currentChar = str[i]
            count = 1
        }
    }

    appendCharInfo(result, currentChar, count)

    return result.toString()
}

private fun appendCharInfo(builder: StringBuilder, char: Char, count: Int) {
    builder.append(char)
    if (count > 1 && char != ' ') {
        builder.append(count)
    }
}
