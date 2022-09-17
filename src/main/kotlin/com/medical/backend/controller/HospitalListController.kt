package com.medical.backend.controller

import com.medical.backend.model.HospitalList
import com.medical.backend.repository.HospitalListRepository
import com.medical.backend.service.HospitalListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin("*")
@RestController
//@RequestMapping("v1")
class HospitalListController (

    @Autowired
//    val HospitalListRepository: HospitalListRepository,
    val HospitalListService: HospitalListService
){

    @GetMapping("/hospital")
    fun getAllHospitalLists(): Flux<HospitalList> {
        return HospitalListService.findAll()
    }

    @GetMapping("{hospitalId}")
    fun getHospitalListById(@PathVariable hospitalId: String): Mono<HospitalList> {
        return HospitalListService.findById(hospitalId)
    }

    @PostMapping("/HospitalLists")
    fun HospitalListSave(@RequestBody HospitalList: HospitalList): Mono<HospitalList> {
        return HospitalListService.addToHospitalList(HospitalList)
    }

    @PutMapping("/updateHospitalListById/{hospitalId}")
    fun updateByHospitalId( @PathVariable hospitalId: String, @RequestBody hospitalList: HospitalList): Mono<HospitalList> {
        return HospitalListService.updateById(hospitalId,hospitalList)
    }

//    @DeleteMapping
//    fun deleteHospitalList(): Mono<Void> {
//        return HospitalListRepository.deleteAll()
//    }


    @DeleteMapping("/HospitalLists/{hospitalId}")
    fun deleteHospitalLists(@PathVariable hospitalId: String): Mono<Void> {
        return HospitalListService.deleteById(hospitalId)
    }
}


