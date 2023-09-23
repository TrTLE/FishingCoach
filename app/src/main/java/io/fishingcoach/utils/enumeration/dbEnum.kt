package io.fishingcoach.utils.enumeration

enum class DBEnum (val value: String, val url: String){

    DB_TYPE_POSTRESQL("POSTGRESQL", "http:192.168.1.21:8085"),
    DB_TYPE_SQLLITE("SQLLITE", "http://127.0.0.1:5000")


}