package vbn.clean.nation_flag.data.model.response.chart

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class BobbleChart(
    val name: String? = null,
    val value: Float? = 0f,
    val isMain: Boolean? = null,
)

data class CandlestickData(
    val timestamp: String,
    val open: Float,
    val close: Float,
    val high: Float,
    val low: Float,
    val volume: Int
)

fun parseCandlestickData(jsonString: String): List<CandlestickData> {
    val gson = Gson()
    val listType = object : TypeToken<List<CandlestickData>>() {}.type
    return gson.fromJson(jsonString, listType)
}

val json = """{
  "candlestick_data": [
    {
      "timestamp": "2023-06-01T00:00:00Z",
      "open": 100.5,
      "close": 105.0,
      "high": 107.0,
      "low": 99.5,
      "volume": 1500
    },
    {
      "timestamp": "2023-06-01T01:00:00Z",
      "open": 105.0,
      "close": 102.5,
      "high": 106.0,
      "low": 101.5,
      "volume": 1200
    },
    {
      "timestamp": "2023-06-01T02:00:00Z",
      "open": 102.5,
      "close": 103.0,
      "high": 104.5,
      "low": 101.0,
      "volume": 1300
    },
    {
      "timestamp": "2023-06-01T03:00:00Z",
      "open": 103.0,
      "close": 107.0,
      "high": 108.0,
      "low": 102.0,
      "volume": 1400
    },
    {
      "timestamp": "2023-06-01T04:00:00Z",
      "open": 107.0,
      "close": 106.5,
      "high": 109.0,
      "low": 105.5,
      "volume": 1100
    },
    {
      "timestamp": "2023-06-01T05:00:00Z",
      "open": 106.5,
      "close": 104.0,
      "high": 107.5,
      "low": 103.0,
      "volume": 1600
    },
    {
      "timestamp": "2023-06-01T06:00:00Z",
      "open": 104.0,
      "close": 108.0,
      "high": 109.5,
      "low": 103.5,
      "volume": 1700
    },
    {
      "timestamp": "2023-06-01T07:00:00Z",
      "open": 108.0,
      "close": 110.0,
      "high": 111.0,
      "low": 107.0,
      "volume": 1800
    }
  ]
}"""

val candlestickData = parseCandlestickData(json)