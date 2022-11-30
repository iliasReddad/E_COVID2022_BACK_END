package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  DataRepository extends JpaRepository<Data,Long> {
    @Query("Select count(Emotion)  from Data  ")
    Integer getAllEmotion();



    @Query("SELECT count(Emotion), Emotion, date from Data group by Emotion,date")
    List<String> getAllCategoryofemotion();

    @Query("SELECT commentaire from Data")
    List<String> getAllcommentaire();

    @Query("select count(commentaire) ,date from Data group by date order by date asc")
    List<String> getAllcommentaireGroupeByDate();

    @Query("select count(commentaire) , Topic  from Data  group by Topic ")
    List<String> getnumberofcommentsbytopic();

    @Query("select count(commentaire) , Emotion  from Data  group by Emotion ")
    List<String> getnumberofcommentsbyEmotion();

    @Query(" select count(commentaire) , Emotion , date from Data  group by date order by date asc")
    List<String> EvolutiobOfcommentsbyEmotion();


    @Query(" select count(commentaire) , Topic , date from Data  group by date order by date asc")
    List<String> EvolutiobOfcommentsbytopic();


    //select count(commentaire) , Topic  from Data  WHERE (date BETWEEN '25/11/2020' AND '29/12/2020')   group by Topic
    //select count(commentaire) , Topic  from Data  where pdate >= CURRENT_TIMESTAMP -30 group by Topic
    @Query("select count(commentaire) , Emotion  from Data where Emotion like 'Approval' or  Emotion like 'Optimism'")
    List<Integer> getnumberofcommentsbyEmotionApprovale();

    Data findById(int id);


}
