package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.models.Data;
import com.bezkoder.springjwt.repository.DataRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transaction;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
 public class DataService {
    @Autowired
    private DataRepository dataRepository;
    public List<Data> getAllData(){
        return dataRepository.findAll();
    }
    //Get All Comments
    public List<String> getAllComments(){return dataRepository.getAllcommentaire();}
    //Get number of All comments
    public int getnumberofallcomments(){return dataRepository.getAllcommentaire().size();}
   //Get number of comments by Emotio
   public List<Integer> getnumberofcommentsbyEmotionApprovale(){return dataRepository.getnumberofcommentsbyEmotionApprovale();}

    //Get number of comments by Topic
    public List<String> getnumberofcommentsbyTopic(){return dataRepository.getnumberofcommentsbytopic();}

    //Evolution of comments by Topic
    public List<String> EvolutionofcommentsbyTopic(){return dataRepository.EvolutiobOfcommentsbytopic();}

    //Get number of comments by Emotion
    public List<String> getnumberofcommentsbyEmotion(){return dataRepository.getnumberofcommentsbyEmotion();}

    //Evolution of comments by Emotion
    public List<String> EvolutionofcommentsbyEmotion(){return dataRepository.EvolutiobOfcommentsbyEmotion();}



    //Get number of comment By Date
    public List<String> getnumberofcommentsbyDate(){return  dataRepository.getAllcommentaireGroupeByDate();}
   //Get All Emotion
    public Integer getAllemotion(){return dataRepository.getAllEmotion();}
    //Get All Category of Emotion
    public List<String> getAllcategoryofemotion(){return dataRepository.getAllCategoryofemotion();}
    //Save new Data
    public void save(Data data){
        dataRepository.save(data);
    }
    //get data by an Id
    public Data findbyId(int id){
        return dataRepository.findById(id);
    }
    // delete a data by Id
    public void delete(int id){
        dataRepository.deleteById((long) id);
    }

    public void importToDb(List<MultipartFile> multipartfiles) {
        System.out.println("called");
        if (!multipartfiles.isEmpty()) {
            List<Data> DATAS = new ArrayList<>();
            multipartfiles.forEach(multipartfile -> {
                try {
                    XSSFWorkbook workBook = new XSSFWorkbook(multipartfile.getInputStream());

                    XSSFSheet sheet = workBook.getSheetAt(0);
                    // looping through each row
                    for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0) - 1; rowIndex++) {
                        // current row
                        XSSFRow row = sheet.getRow(rowIndex);
                        // skip the first row because it is a header row
                        if (rowIndex == 0) {
                            continue;
                        }
                        Long Id = Long.parseLong(getValue(row.getCell(0)).toString());
                        Date date  = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(row.getCell(0)));
                        String commentaire = String.valueOf(row.getCell(2));
                        String Topic = String.valueOf(row.getCell(5));
                        String Emotion = String.valueOf(row.getCell(4));
                        String Source = String.valueOf(row.getCell(3));


                        Data data = new Data();
                        data.setDate(date);
                        data.setId(Id);
                        data.setEmotion(Emotion);
                        data.setSource(Source);
                        data.setTopic(Topic);

                        DATAS.add(data);
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            });

            if (!DATAS.isEmpty()) {
                // save to database
                dataRepository.saveAll(DATAS);
            }
        }
    }

    private Object getValue(XSSFCell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            case _NONE:
                return null;
            default:
                break;
        }
        return null;
    }

    public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
        int numOfNonEmptyCells = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                XSSFCell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    numOfNonEmptyCells++;
                }
            }
        }
        return numOfNonEmptyCells;
    }
}

