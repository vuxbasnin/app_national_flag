package vbn.clean.nation_flag.data.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import vbn.clean.nation_flag.data.model.response.demo.DemoReponse
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponse
import vbn.clean.nation_flag.data.network.DefaultRequest
import vbn.clean.nation_flag.data.network.Endpoint

interface DemoService {
    @GET(Endpoint.DEMO)
    suspend fun getDemo(@Body body: DefaultRequest): Response<DemoReponse>

    @GET(Endpoint.NATION_FLAG)
    suspend fun getNationFlag(): Response<NationalFlagResponse>
}