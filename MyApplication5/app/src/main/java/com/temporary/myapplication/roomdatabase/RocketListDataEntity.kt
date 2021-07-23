package com.temporary.myapplication.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "rocketList")
data class RocketListDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids") val ids: Int = 0,
    @ColumnInfo(name = "id") val id: List<Id>,
    @ColumnInfo(name = "name") val name: List<Name>,
    @ColumnInfo(name = "country") val country: List<Country>,
    @ColumnInfo(name = "engineCount") val engineCount: List<EngineCount>,
    @ColumnInfo(name = "activeStatus") val activeStatus: List<ActiveStatus>,
    @ColumnInfo(name = "costPerLaunch") val costPerLaunch: List<CostPerLaunch>,
    @ColumnInfo(name = "successRate") val successRate: List<SuccessRate>,
    @ColumnInfo(name = "desc") val desc: List<Desc>,
    @ColumnInfo(name = "wikiLink") val wikiLink: List<WikiLink>,
    @ColumnInfo(name = "height") val height: List<Height>,
    @ColumnInfo(name = "diameter") val diameter: List<Diameter>,
    @ColumnInfo(name = "images") val images: List<Images>
)


data class Id(val id: String)
data class Name(val name: String)
data class Country(val country: String)
data class EngineCount(val engineCount: String)
data class ActiveStatus(val activeStatus: Boolean)
data class CostPerLaunch(val costPerLaunch: String)
data class SuccessRate(val successRate: String)
data class Desc(val desc: String)
data class WikiLink(val wikiLink: String)
data class Height(val height: String)
data class Diameter(val diameter: String)
data class Images(val images: List<String>?)


class IdTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Id>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Id>::class.java).toList()
}

class NameTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Name>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Name>::class.java).toList()
}

class CountryTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Country>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Country>::class.java).toList()
}

class EngineTypeConverter {
    @TypeConverter
    fun listToJson(value: List<EngineCount>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<EngineCount>::class.java).toList()
}

class StatusTypeConverter {
    @TypeConverter
    fun listToJson(value: List<ActiveStatus>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ActiveStatus>::class.java).toList()
}

class CostTypeConverter {
    @TypeConverter
    fun listToJson(value: List<CostPerLaunch>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<CostPerLaunch>::class.java).toList()
}

class SuccessTypeConverter {
    @TypeConverter
    fun listToJson(value: List<SuccessRate>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<SuccessRate>::class.java).toList()
}

class DescTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Desc>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Desc>::class.java).toList()
}

class WikiTypeConverter {
    @TypeConverter
    fun listToJson(value: List<WikiLink>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<WikiLink>::class.java).toList()
}

class HeightTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Height>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Height>::class.java).toList()
}

class DiameterTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Diameter>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Diameter>::class.java).toList()
}

class ImagesTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Images>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Images>::class.java).toList()
}
