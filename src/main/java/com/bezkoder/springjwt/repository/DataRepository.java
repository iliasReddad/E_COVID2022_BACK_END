package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Data;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface  DataRepository extends JpaRepository<Data,Long> {
    @Query("Select count(Emotion)  from Data  ")
    Integer getAllEmotion();



    @Query("SELECT count(Emotion), Emotion, date from Data group by Emotion,date")
    List<String> getAllCategoryofemotion();

    @Query("SELECT commentaire from Data")
    List<String> getAllcommentaire();

    @Query("SELECT commentaire ,id  from Data where Emotion = '' or Topic = '' ")
    List<String> getAllCommentsNonAnnoted();

    @Transactional
    @Modifying
    @Query(" update Data d SET d.Emotion=:emotion , d.Topic=:topic WHERE d.id=:ids  ")
    boolean UpdateComment(@Param("ids") Long ids , @Param("emotion") String emotion , @Param("topic") String topic);





    @Query("select count(commentaire) ,date from Data group by date order by date asc")
    List<String> getAllcommentaireGroupeByDate();

    @Query("select count(commentaire) , Topic  from Data where Topic not like '' group by Topic ")
    List<String> getnumberofcommentsbytopic();

    @Query("select count(commentaire) , Emotion  from Data where Emotion not like ''   group by Emotion")
    List<String> getnumberofcommentsbyEmotion();

    @Query(" select count(commentaire) , Emotion , date from Data where Emotion not like ''   group by date order by date asc")
    List<String> EvolutiobOfcommentsbyEmotion();


    @Query(" select count(commentaire) , Topic , date from Data where Topic not like ''  group by date order by date asc")
    List<String> EvolutiobOfcommentsbytopic();


    //select count(commentaire) , Topic  from Data  WHERE (date BETWEEN '25/11/2020' AND '29/12/2020')   group by Topic
    //select count(commentaire) , Topic  from Data  where pdate >= CURRENT_TIMESTAMP -30 group by Topic
    @Query("select count(commentaire) , Emotion  from Data where Emotion like 'Approval' or  Emotion like 'Optimism'")
    List<Integer> getnumberofcommentsbyEmotionApprovale();

    Data findById(int id);


}
