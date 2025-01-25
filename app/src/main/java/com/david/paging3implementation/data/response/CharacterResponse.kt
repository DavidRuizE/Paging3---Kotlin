package com.david.paging3implementation.data.response

import com.google.gson.annotations.SerializedName

/*
      "id": 21,
      "name": "Aqua Morty",
      "status": "unknown",

    "image": "https://rickandmortyapi.com/api/character/avatar/21.jpeg",
 */

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String,

)
