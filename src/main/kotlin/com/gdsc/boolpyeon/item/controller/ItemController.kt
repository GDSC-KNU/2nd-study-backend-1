package com.gdsc.boolpyeon.item.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController {
    @GetMapping("/items")
    fun allItems() {

    }

    @GetMapping("/items/gs25")
    fun gs25Items() {

    }

    @GetMapping("/items/cu")
    fun cuItems() {

    }

    @GetMapping("/items/sevenEleven")
    fun sevenElevenItems() {

    }

    @GetMapping("/items/emart24")
    fun emart24Items() {

    }
}