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
    val volume: Int,
)

fun parseCandlestickData(jsonString: String): List<CandlestickData> {
    val gson = Gson()
    val listType = object : TypeToken<List<CandlestickData>>() {}.type
    return gson.fromJson(jsonString, listType)
}

val json = """[
    {"timestamp": "2023-06-01T13:00:00Z", "open": 115.0, "close": 113.0, "high": 116.5, "low": 112.5, "volume": 1600},
    {"timestamp": "2023-06-01T14:00:00Z", "open": 113.0, "close": 114.0, "high": 115.0, "low": 112.0, "volume": 1700},
    {"timestamp": "2023-06-01T15:00:00Z", "open": 114.0, "close": 116.0, "high": 117.0, "low": 113.5, "volume": 1800},
    {"timestamp": "2023-06-01T16:00:00Z", "open": 116.0, "close": 118.0, "high": 119.0, "low": 115.5, "volume": 1900},
    {"timestamp": "2023-06-01T17:00:00Z", "open": 118.0, "close": 117.0, "high": 118.5, "low": 116.5, "volume": 2000},
    {"timestamp": "2023-06-01T18:00:00Z", "open": 117.0, "close": 120.0, "high": 121.0, "low": 116.0, "volume": 2100},
    {"timestamp": "2023-06-01T19:00:00Z", "open": 120.0, "close": 122.0, "high": 123.0, "low": 119.0, "volume": 2200},
    {"timestamp": "2023-06-01T20:00:00Z", "open": 122.0, "close": 121.0, "high": 122.5, "low": 120.5, "volume": 2300},
    {"timestamp": "2023-06-01T21:00:00Z", "open": 121.0, "close": 123.0, "high": 124.0, "low": 120.0, "volume": 2400},
    {"timestamp": "2023-06-01T22:00:00Z", "open": 123.0, "close": 125.0, "high": 126.0, "low": 122.5, "volume": 2500},
    {"timestamp": "2023-06-01T23:00:00Z", "open": 125.0, "close": 127.0, "high": 128.0, "low": 124.0, "volume": 2600},
    {"timestamp": "2023-06-02T00:00:00Z", "open": 127.0, "close": 128.5, "high": 129.0, "low": 126.5, "volume": 2700},
    {"timestamp": "2023-06-02T01:00:00Z", "open": 128.5, "close": 127.5, "high": 129.0, "low": 126.5, "volume": 2800},
    {"timestamp": "2023-06-02T02:00:00Z", "open": 127.5, "close": 126.0, "high": 128.0, "low": 125.5, "volume": 2900},
    {"timestamp": "2023-06-02T03:00:00Z", "open": 126.0, "close": 127.0, "high": 127.5, "low": 125.0, "volume": 3000},
    {"timestamp": "2023-06-02T04:00:00Z", "open": 127.0, "close": 128.0, "high": 129.0, "low": 126.5, "volume": 3100},
    {"timestamp": "2023-06-02T05:00:00Z", "open": 128.0, "close": 129.0, "high": 130.0, "low": 127.5, "volume": 3200},
    {"timestamp": "2023-06-02T06:00:00Z", "open": 129.0, "close": 128.0, "high": 129.5, "low": 127.0, "volume": 3300},
    {"timestamp": "2023-06-02T07:00:00Z", "open": 128.0, "close": 127.0, "high": 128.5, "low": 126.5, "volume": 3400},
    {"timestamp": "2023-06-02T08:00:00Z", "open": 127.0, "close": 125.5, "high": 127.5, "low": 125.0, "volume": 3500},
    {"timestamp": "2023-06-02T09:00:00Z", "open": 125.5, "close": 124.5, "high": 126.0, "low": 124.0, "volume": 3600},
    {"timestamp": "2023-06-02T10:00:00Z", "open": 124.5, "close": 125.5, "high": 126.5, "low": 124.0, "volume": 3700},
    {"timestamp": "2023-06-02T11:00:00Z", "open": 125.5, "close": 126.5, "high": 127.5, "low": 125.0, "volume": 3800},
    {"timestamp": "2023-06-02T12:00:00Z", "open": 126.5, "close": 128.0, "high": 128.5, "low": 126.0, "volume": 3900},
    {"timestamp": "2023-06-02T13:00:00Z", "open": 128.0, "close": 129.5, "high": 130.0, "low": 127.5, "volume": 4000},
    {"timestamp": "2023-06-02T14:00:00Z", "open": 129.5, "close": 128.5, "high": 130.0, "low": 128.0, "volume": 4100},
    {"timestamp": "2023-06-02T15:00:00Z", "open": 128.5, "close": 130.0, "high": 130.5, "low": 128.0, "volume": 4200},
    {"timestamp": "2023-06-02T16:00:00Z", "open": 130.0, "close": 131.0, "high": 132.0, "low": 129.5, "volume": 4300},
    {"timestamp": "2023-06-02T17:00:00Z", "open": 131.0, "close": 130.0, "high": 131.5, "low": 129.0, "volume": 4400},
    {"timestamp": "2023-06-02T18:00:00Z", "open": 130.0, "close": 132.0, "high": 132.5, "low": 129.5, "volume": 4500},
    {"timestamp": "2023-06-02T19:00:00Z", "open": 132.0, "close": 131.0, "high": 132.5, "low": 130.5, "volume": 4600},
    {"timestamp": "2023-06-02T20:00:00Z", "open": 131.0, "close": 130.0, "high": 131.5, "low": 129.0, "volume": 4700},
    {"timestamp": "2023-06-02T21:00:00Z", "open": 130.0, "close": 128.5, "high": 130.5, "low": 128.0, "volume": 4800},
    {"timestamp": "2023-06-02T22:00:00Z", "open": 128.5, "close": 127.0, "high": 129.0, "low": 126.5, "volume": 4900},
    {"timestamp": "2023-06-02T23:00:00Z", "open": 127.0, "close": 126.0, "high": 127.5, "low": 125.5, "volume": 5000},
    {"timestamp": "2023-06-03T00:00:00Z", "open": 126.0, "close": 128.0, "high": 128.5, "low": 125.5, "volume": 5100},
    {"timestamp": "2023-06-03T01:00:00Z", "open": 128.0, "close": 129.0, "high": 129.5, "low": 127.0, "volume": 5200},
    {"timestamp": "2023-06-03T02:00:00Z", "open": 129.0, "close": 130.0, "high": 131.0, "low": 128.5, "volume": 5300},
    {"timestamp": "2023-06-03T03:00:00Z", "open": 130.0, "close": 128.0, "high": 130.5, "low": 127.5, "volume": 5400},
    {"timestamp": "2023-06-03T04:00:00Z", "open": 128.0, "close": 127.0, "high": 128.5, "low": 126.5, "volume": 5500},
    {"timestamp": "2023-06-03T05:00:00Z", "open": 127.0, "close": 126.5, "high": 127.5, "low": 126.0, "volume": 5600},
    {"timestamp": "2023-06-03T06:00:00Z", "open": 126.5, "close": 128.0, "high": 129.0, "low": 126.0, "volume": 5700},
    {"timestamp": "2023-06-03T07:00:00Z", "open": 128.0, "close": 129.5, "high": 130.0, "low": 127.5, "volume": 5800},
    {"timestamp": "2023-06-03T08:00:00Z", "open": 129.5, "close": 131.0, "high": 132.0, "low": 129.0, "volume": 5900},
    {"timestamp": "2023-06-03T09:00:00Z", "open": 131.0, "close": 130.0, "high": 131.5, "low": 129.0, "volume": 6000},
    {"timestamp": "2023-06-03T10:00:00Z", "open": 130.0, "close": 128.5, "high": 130.5, "low": 128.0, "volume": 6100},
    {"timestamp": "2023-06-03T11:00:00Z", "open": 128.5, "close": 127.0, "high": 129.0, "low": 126.5, "volume": 6200},
    {"timestamp": "2023-06-03T12:00:00Z", "open": 127.0, "close": 129.0, "high": 129.5, "low": 126.5, "volume": 6300},
    {"timestamp": "2023-06-03T13:00:00Z", "open": 129.0, "close": 130.0, "high": 131.0, "low": 128.5, "volume": 6400},
    {"timestamp": "2023-06-03T14:00:00Z", "open": 130.0, "close": 131.5, "high": 132.0, "low": 129.5, "volume": 6500},
    {"timestamp": "2023-06-03T15:00:00Z", "open": 131.5, "close": 133.0, "high": 134.0, "low": 131.0, "volume": 6600},
    {"timestamp": "2023-06-03T16:00:00Z", "open": 133.0, "close": 134.5, "high": 135.0, "low": 132.5, "volume": 6700},
    {"timestamp": "2023-06-03T17:00:00Z", "open": 134.5, "close": 133.5, "high": 135.0, "low": 132.5, "volume": 6800},
    {"timestamp": "2023-06-03T18:00:00Z", "open": 133.5, "close": 134.0, "high": 135.0, "low": 132.5, "volume": 6900},
    {"timestamp": "2023-06-03T19:00:00Z", "open": 134.0, "close": 135.5, "high": 136.0, "low": 133.5, "volume": 7000},
    {"timestamp": "2023-06-03T20:00:00Z", "open": 135.5, "close": 137.0, "high": 138.0, "low": 135.0, "volume": 7100},
    {"timestamp": "2023-06-03T21:00:00Z", "open": 137.0, "close": 138.5, "high": 139.0, "low": 136.5, "volume": 7200},
    {"timestamp": "2023-06-03T22:00:00Z", "open": 138.5, "close": 139.5, "high": 140.0, "low": 138.0, "volume": 7300},
    {"timestamp": "2023-06-03T23:00:00Z", "open": 139.5, "close": 138.5, "high": 140.0, "low": 138.0, "volume": 7400},
    {"timestamp": "2023-06-04T00:00:00Z", "open": 138.5, "close": 137.0, "high": 139.0, "low": 136.5, "volume": 7500},
    {"timestamp": "2023-06-04T01:00:00Z", "open": 137.0, "close": 135.5, "high": 137.5, "low": 135.0, "volume": 7600},
    {"timestamp": "2023-06-04T02:00:00Z", "open": 135.5, "close": 134.0, "high": 136.0, "low": 133.5, "volume": 7700},
    {"timestamp": "2023-06-04T03:00:00Z", "open": 134.0, "close": 133.0, "high": 134.5, "low": 132.5, "volume": 7800},
    {"timestamp": "2023-06-04T04:00:00Z", "open": 133.0, "close": 134.5, "high": 135.0, "low": 132.5, "volume": 7900},
    {"timestamp": "2023-06-04T05:00:00Z", "open": 134.5, "close": 133.5, "high": 135.0, "low": 133.0, "volume": 8000},
    {"timestamp": "2023-06-04T06:00:00Z", "open": 133.5, "close": 132.0, "high": 134.0, "low": 131.5, "volume": 8100}
  ]
"""

val candlestickData = parseCandlestickData(json)