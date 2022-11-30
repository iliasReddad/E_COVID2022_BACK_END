package com.bezkoder.springjwt.payload.request;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor

public class UpdateContentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ctn_block;

    private String titre;

    private String sous_titre;

    @Size(max = 300)
    private String paragraphe;



}
