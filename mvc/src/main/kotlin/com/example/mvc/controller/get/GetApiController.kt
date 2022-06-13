package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController         // REST API Controller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    @GetMapping(path = ["hello", "/abcd"]) // GET http://localhost:8080/api/hello
    fun hello(): String{
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("get-mapping/path-veriable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, ${age}")
        return name +" "+ age
    }

    @GetMapping("get-mapping/path-veriable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {

        val name = "kotlin"

        println("${_name}, ${age}")
        return _name +" "+ age
    }

    // http://localhost:8080/api/page?key=value&key=value...... ?뒤에있는걸 쿼리파라미터라고한다.

    @GetMapping("/get-mapping/query-param") // ?name=steve&age=20
    fun queryParam(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "age") age: Int
    ): String {
        println("${name}, ${age}")
        return name+" "+age
    }

    // name, age, address, email
    // -
    // phoneNumber -> phone-number, phonenumber
    // 객체로 받아오는 방법
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }
    // map 으로 받아오는 방법 하이픈 있는 경우 이방법<< 리퀘스트 파람이나 두개중 하나 선택
    @GetMapping("/get-mapping/query-param/map")
    fun  queryParamMap(@RequestParam map: Map<String,Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        return map
    }
}