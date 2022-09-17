package com.medical.backend.controller

import com.medical.backend.model.HospitalList
import com.medical.backend.service.HospitalListService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest(HospitalListController::class)
@AutoConfigureWebTestClient
class HospitalListControllerTest {
    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var HospitalListService: HospitalListService

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun HospitalListService() = mockk<HospitalListService>()
    }

    @Test
    fun `should return list of all the hospitals and to verify that hospitalList service is internally called once`() {
        val HospitalList1 = HospitalList(
            "1", "Lilavati",
            "981", "Sion"
        )
//        val HospitalList2 = HospitalList("2",
//            "Criticare", "982", "Santacruz")

        val expectedResult =
            mapOf(
                "hospitalId" to "1",
                "hospitalName" to "Lilavati",
                "hospitalPhNum" to "981",
                "hospitalAddress" to "Sion"
            )
        every {
            HospitalListService.findAllHospitalLists()
        } returns Flux.just(HospitalList1)

        val response = client.get()
            .uri("/hospital")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult


        verify(exactly = 1) {
            HospitalListService.findAllHospitalLists()
        }
    }


    @Test
    fun `should create hospital entry when create api is being called`() {

        val expectedResponse =
            mapOf(
                "hospitalId" to "1",
                "hospitalName" to "Lilavati",
                "hospitalPhNum" to "981",
                "hospitalAddress" to "Sion",
            )

        val HospitalList1 = HospitalList("1", "Lilavati", "981", "Sion")

        every {
            HospitalListService.addToHospitalList(HospitalList1)
        } returns Mono.just(HospitalList1)

        val response = client.post()
            .uri("/HospitalLists")
            .bodyValue(HospitalList1)
            .exchange()
            .expectStatus().is2xxSuccessful
            .returnResult<Any>().responseBody

        response.blockFirst() shouldBe expectedResponse

        verify(exactly = 1) {
            HospitalListService.addToHospitalList(HospitalList1)
        }
    }

    @Test
    fun `should be able to update hospital details`() {

        val expectedResult = listOf(
            mapOf("hospitalId" to "1",
                "hospitalName" to "Criticare",
                "hospitalPhNum" to "982",
                "hospitalAddress" to "Andheri",
            )
        )

        val HospitalList1 = HospitalList("1","Criticare","982","Andheri")

        every {
            HospitalListService.updateHospitalListById("1",HospitalList1)
        } returns Mono.just(HospitalList1)

        val response = client.put()
            .uri("/updateHospitalListById/1")
            .bodyValue(HospitalList1)
            .exchange()
            .expectStatus().is2xxSuccessful

        verify(exactly = 1) {
            HospitalListService.updateHospitalListById("1",HospitalList1)
        }
    }

}
