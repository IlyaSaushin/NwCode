package com.earl.nwcode.data

import com.earl.nwcode.data.models.remote.ImageRemote
import org.json.JSONObject
import javax.inject.Inject

interface JsonParseHelper {

    fun parseImageEntity(json: String) : List<ImageRemote>

    class Base @Inject constructor() : JsonParseHelper {

        override fun parseImageEntity(json: String) = try {
            val jsonObject = JSONObject(json)
            val hits = jsonObject.getJSONArray(HITS)
            val readyList = mutableListOf<ImageRemote>()
            for (i in 0 until hits.length()) {
                readyList.add(ImageRemote(
                    hits.getJSONObject(i).getInt(ID),
                    hits.getJSONObject(i).getString(WEB_FORMAT),
                    hits.getJSONObject(i).getString(PREVIEW),
                    hits.getJSONObject(i).getString(LARGE),
                ))
            }
            readyList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    companion object {
        private const val HITS = "hits"
        private const val ID = "id"
        private const val WEB_FORMAT = "webformatURL"
        private const val PREVIEW = "previewURL"
        private const val LARGE = "largeImageURL"
    }
}