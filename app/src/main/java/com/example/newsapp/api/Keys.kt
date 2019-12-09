package com.example.newsapp.api

interface Keys {
    interface ApiField {
    companion object{
        const val REQ_API_KEY: String="apiKey"
        const val REQ_COUNTRY="country"
    }
    }

    interface Constant {
    companion object{
        const val API_KEY: String="f4b5e1c356bf4c1a8451411f4c162043"
    }
    }

    interface Extras{
        companion object{
            const val URL="url"
        }
    }

    interface Prefs {
        companion object{
            const val LIST_OF_ARTICLES="list_of_start_time"

        }

    }


}