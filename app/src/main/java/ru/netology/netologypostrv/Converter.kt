package ru.netology.netologypostrv


object Converter {
    fun convert(count: Int): String{
        var s = ""

        if (count > 999 && count <= 9999){
            val convertCount = count/1000.0
            s = String.format("%.1f", convertCount) + "K"
        } else if(count > 9999 && count <= 999_999){
            val convertCount = (count/1000)
            s = convertCount.toString() + "K"
        }else if (count >= 1_000_000){
            val convertCount = count/1000000.0
            s = String.format("%.1f", convertCount) + "M"
        }else{
            s = count.toString()
        }
        return s
    }
}