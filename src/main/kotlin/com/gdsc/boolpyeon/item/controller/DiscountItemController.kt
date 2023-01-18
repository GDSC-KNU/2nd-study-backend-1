package com.gdsc.boolpyeon.item.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DiscountItemController {
    @GetMapping("/items")
    fun allItems(@RequestParam("brand") brand: String, @RequestParam("category") category: String,
    @RequestParam("event_type") event_type: String) {

    }

    @GetMapping("/item/{itemName}")
    fun item(@PathVariable itemName: String) {

    }

    @GetMapping("/item/{brand}")
    fun brandItem(@PathVariable brand: String) {

    }

    @GetMapping("/item/{brand}/{itemId}")
    fun brandItemDetail(@PathVariable brand: String, @PathVariable itemId: Long) {

    }
}