package api.englishAPI.service;

import api.englishAPI.dto.WordsDTO;
import api.englishAPI.model.Frequency;
import api.englishAPI.model.Words;
import api.englishAPI.repositories.WordsRepository;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WordsService {

    @Autowired
    private WordsRepository repository;



    public List<Words> listAllWords(){return repository.findAll();}
    public Words getRandomWord() {
        List<Words> allWords = repository.findAll();
        if (allWords.isEmpty()) {
            return null; // Retorna null se a lista estiver vazia
        }

        Random random = new Random();
        int randomIndex = random.nextInt(allWords.size());
        return allWords.get(randomIndex);
    }


    public ResponseEntity<Words> listWordById(Long id){
         Optional<Words> word =  repository.findById(id);
         if (word.isPresent()){
              Words getWord = word.get();
              return new ResponseEntity<>(getWord, HttpStatus.OK);
         }else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }

   public ResponseEntity<Words> addWord(WordsDTO wordsDTO){
        Words words = new Words();
        BeanUtils.copyProperties(wordsDTO, words);
        repository.save(words);
        return new ResponseEntity<>(words, HttpStatus.CREATED );
    }


    public ResponseEntity<String> updateMemo(Long id) {
        Optional<Words> wordsOp = repository.findById(id);

        if (wordsOp.isPresent()) {
            Words word = wordsOp.get();

            // Incrementa o valor de memorizeTimes
            Long actualValue = word.getMemorizeTimes() + 1;
            word.setMemorizeTimes(actualValue);

            // Salva a entidade atualizada no repositório
            repository.save(word);

            return ResponseEntity.ok("Valor de memorizeTimes atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    public List<Words> addWordsBatch(List<WordsDTO> wordsDTOList) {
        List<Words> newWordsList = new ArrayList<>();

        for (WordsDTO wordsDTO : wordsDTOList) {
            Words words = new Words();

            BeanUtils.copyProperties(wordsDTO, words);

            Words savedWords = repository.save(words);

            newWordsList.add(savedWords);
        }

        return newWordsList;
    }

    public void removeAll(){
       repository.deleteAll();
   }



    public void atualizarMemorizeTimes() {
        List<Words> registros = repository.findAll();

        for (Words registro : registros) {
            registro.setMemorizeTimes(0L);
        }

        repository.saveAll(registros);
    }



}
