package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Services.DataService;
import com.bezkoder.springjwt.models.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private DataService dataService;
    @GetMapping("/Data")
    public List<Data> getAlldata(){
        return dataService.getAllData();
    }
    @GetMapping("/Data/NumberofEmotions")
    public Integer getAllEmotion(){return dataService.getAllemotion();}
    @GetMapping("/Data/Emotions")
    public List<String> getallEmotions(){return dataService.getAllcategoryofemotion();}
    @GetMapping("/Data/Commentaires")
    public List<String> getallcomments(){return dataService.getAllComments();}
    @GetMapping("/Data/numberofcommentaires")
    public int getnumberofallcomments(){return dataService.getnumberofallcomments();}
    @GetMapping("Data/numberofcommentairesbyEmotionPositive")
    public List<Integer> getnumberofcommentsbyEmotionPositive(){return dataService.getnumberofcommentsbyEmotionApprovale();}
    @GetMapping("Data/numberofcommentairesbyDate")
    public List<String> getnumberofcommentsbyDate(){return dataService.getnumberofcommentsbyDate();}

    @GetMapping("Data/numberofcommentairesbyTopic")
    public List<String> getnumberofcommentsbyTopic(){return dataService.getnumberofcommentsbyTopic();}
    @GetMapping("Data/EvolutionofcommentairesbyTopic")
    public List<String> evolutionofcommentsbyTopic(){return dataService.EvolutionofcommentsbyTopic();}

    @GetMapping("Data/numberofcommentairesbyEmotion")
    public List<String> getnumberofcommentsbyEmotion(){return dataService.getnumberofcommentsbyEmotion();}
    @GetMapping("Data/EvolutionofcommentairesbyEmotion")
    public List<String> evolutionofcommentsbyEmotion(){return dataService.EvolutionofcommentsbyEmotion();}


    @PostMapping(path = "/import")
    public void importTransactionsFromExcelToDb(@RequestParam("file") MultipartFile files) {
        System.out.println("called service");
        dataService.importToDb((List<MultipartFile>) files);

    }

}
