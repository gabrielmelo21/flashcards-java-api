package api.englishAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class HelloController {
    @GetMapping
    public String hello() {
        return "Flashcards-Java-API is RUNNING!  ";
    }
}
