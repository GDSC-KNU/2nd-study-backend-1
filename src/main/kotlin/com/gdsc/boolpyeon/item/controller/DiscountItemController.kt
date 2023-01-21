package com.gdsc.boolpyeon.item.controller

import com.gdsc.boolpyeon.item.service.DiscountItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Controller
class DiscountItemController(
    private val discountItemService: DiscountItemService
) {
    @GetMapping("/items")
    fun allItems(@RequestParam("brand") brand: String, @RequestParam("category") category: String,
    @RequestParam("event_type") event_type: String, model: Model) : String {
        val items = discountItemService.getAllItems()
        model.addAttribute("items", items)
        return "/item/allItems"
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