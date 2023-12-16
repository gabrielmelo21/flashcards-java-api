package api.englishAPI.controller;


import api.englishAPI.dto.WordsDTO;
import api.englishAPI.model.Words;

import api.englishAPI.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordsController {
    @Autowired
    private WordsService wordsService;


    @PostMapping("/batch")
    public ResponseEntity<List<Words>> addWordsBatch(@RequestBody List<WordsDTO> wordsDTOList) {
        List<Words> newWordsList = wordsService.addWordsBatch(wordsDTOList);

        // Você pode escolher retornar os objetos recém-criados ou uma mensagem de sucesso, dependendo do caso.
        return new ResponseEntity<>(newWordsList, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Words>> listAllWord(){
        List<Words> list = wordsService.listAllWords();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/random")
    public  Words random(){
        return wordsService.getRandomWord();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Words> listById(@PathVariable Long id){
        Words words = wordsService.listWordById(id).getBody();
        return new ResponseEntity<>(words, HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<Words> addWords(@RequestBody WordsDTO wordsDTO){
        Words newWords = wordsService.addWord(wordsDTO).getBody();
        return new ResponseEntity<>(newWords, HttpStatus.CREATED);
    }



    @PutMapping("/updateMemo/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable Long id){
         wordsService.updateMemo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Atualizado com sucesso");
    }



    @DeleteMapping
    public String removeAll(){
          wordsService.removeAll();
         return "itens removidos com sucesos";
    }


}
