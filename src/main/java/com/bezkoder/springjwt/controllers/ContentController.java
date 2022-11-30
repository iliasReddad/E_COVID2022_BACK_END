package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.Contact;
import com.bezkoder.springjwt.models.Content;
import com.bezkoder.springjwt.payload.request.UpdateContentRequest;
import com.bezkoder.springjwt.repository.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContactRepository contactRepository;


    @GetMapping("/all")
    public List<Content>  getAll(){
        return contentRepository.findAll();
    }
    @GetMapping("/Getcontact")
    public List<Contact> getContact(){
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content  getContent( @ApiParam("Id") @PathVariable String  id ){
        return  contentRepository.findbyctn(id);
    }

    @PostMapping ("/Update/{id}")
    public void UpdateContentTitre(@ApiParam("Id") @PathVariable String  id  , @Valid @RequestBody UpdateContentRequest contentRequest){
        System.out.println(contentRequest.getTitre());
        System.out.println(contentRequest.getSous_titre());
        System.out.println(contentRequest.getParagraphe());


        System.out.println("methode called");

        Content content  =  contentRepository.findbyctn(id);

        if (contentRequest.getTitre() !=""){
            content.setTitre(contentRequest.getTitre());
        }
        if (contentRequest.getSous_titre() !=""){
            content.setSous_titre(contentRequest.getSous_titre());
        }
        if (contentRequest.getParagraphe() !=""){
            content.setParagraphe(contentRequest.getParagraphe());
        }

         contentRepository.save(content);

    }

/*
    @PutMapping("/getContent/")
    public Content  updateContent(@Valid @RequestBody Content content ){
       // Content contentRetreived = contentRepository.findByCtn_block(content.getCtn_block());
      /*  System.out.println(content.getTitre());
        content = contentRetreived;
        contentRepository.save(content);
        System.out.println(content.getTitre());
        return  content;
    }*/

}
