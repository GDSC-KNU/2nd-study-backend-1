package com.gdsc.boolpyeon.item.controller

import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.gdsc.boolpyeon.item.service.DiscountItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Controller
@RequestMapping("/api")
class DiscountItemController(
    private val discountItemService: DiscountItemService
) {

    @GetMapping("/items")
    fun allItems(model: Model) : String {
        val items = discountItemService.getAllItems()
        model.addAttribute("items", items)
        return "/item/allItems"
    }
    @GetMapping("/items/search")
    fun searchItems(@RequestParam("brand", required = false) brand: String, @RequestParam("category", required = false) category: String,
    @RequestParam("event_type", required = false) event_type: String, model: Model) : String {
        val items = discountItemService.searchItems(brand, category, event_type)
        model.addAttribute("items", items)
        return "/item/searchItems"
    }

    @GetMapping("/item/{itemName}")
    fun item(@PathVariable itemName: String, model: Model) : String {
        val items = discountItemService.getItem(itemName)
        model.addAttribute("items", items)
        return "item/item"
    }

    @GetMapping("/brand/{brand}")
    fun brandItems(@PathVariable brand: String, model: Model) : String {
        val items = discountItemService.getBrandItems(brand)
        model.addAttribute("items", items)
        return "item/brandItems"
    }

    @GetMapping("/item/{brand}/{itemId}")
    fun brandItemDetail(@PathVariable brand: String, @PathVariable itemId: Long, model: Model) : String {
        val items = discountItemService.getBrandItemDetail(brand, itemId)
        model.addAttribute("items", items)
        return "item/itemDetail"
    }
}