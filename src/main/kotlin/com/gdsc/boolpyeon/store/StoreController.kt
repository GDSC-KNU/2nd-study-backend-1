package com.gdsc.boolpyeon.store

import com.gdsc.boolpyeon.store.dto.response.StoreResponse
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/store")
class StoreController(
    private val storeService: StoreService
) {

    @GetMapping("/{storeId}")
    fun store(@PathVariable("storeId") storeId: Long, map: ModelMap): String {
        val store = StoreResponse.from(storeService.getStore(storeId))
        map.addAttribute("location", store.latitude!! + store.longitude!!)
        map.addAttribute("like_count", store.like_count)

        return "store/detail"
    }

    @GetMapping("/nearby")
    fun stores(
        @RequestParam(name = "latitude") latitude: Double,
        @RequestParam(name = "longitude") longitude: Double, map: ModelMap
    ): String {
        val stores = storeService.getNearStores(latitude, longitude)

        map.addAttribute("stores", stores)

        return "store/index"
    }
}