package br.com.cin.sitcon.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cin.sitcon.model.Pac;
import br.com.cin.sitcon.repository.PacRepository;




@Service
public class LeitorPac {
	
	private Pac pac;
	private List<Pac> itensPac;
	@Autowired
	private PacRepository pacRepository;
	
	 
    /**
     * @param args the command line arguments
     */
	 
    public  void carregar( File file) {

        FileInputStream fisPlanilha = null;
        this.itensPac = new ArrayList<Pac>();
       

        try {
            fisPlanilha = new FileInputStream(file);
            
            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);
          //recuperamos apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);
            
          //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha 0
            while (rowIterator.hasNext()) {
            	
            	this.pac = new Pac();
            	//recebe cada linha da planilha
                Row row = rowIterator.next();
                //pegamos todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();                
              
                System.out.println("\n");
                if(row.getRowNum()>1) {    
                	while (cellIterator.hasNext()) {
                    	//Cria uma celula para interacao
                    	Cell cell = cellIterator.next();
                    	if(cell.getColumnIndex()==0){
                    		//this.pac.setOrgao(cell.getNumericCellValue()+"");
                    	}
                    	if(cell.getColumnIndex()==1){
                    		//this.pac.setUasg(String.valueOf(cell.getNumericCellValue()+""));
                    	}
                    	
                    	if(cell.getColumnIndex()==2){
                    		//this.pac.setAnoPlano(Integer.valueOf(cell.getNumericCellValue()+"".replace(".", "")));
                    	}
                    	
                    	if(cell.getColumnIndex()==3){
                    		//this.pac.setNumero(Integer.valueOf(cell.getStringCellValue()));
                    	}
                    	
                    	if(cell.getColumnIndex()==4){
                    		this.pac.setTipoItem(cell.getStringCellValue());
                    	}
                    	
                    	if(cell.getColumnIndex()==5){
                    		this.pac.setSubTipo(cell.getStringCellValue());
                    	}
                    	
                    	if(cell.getColumnIndex()==6){
                    		//this.pac.setCodigoItem(Integer.valueOf(cell.getNumericCellValue()+""));
                    	}
                    	
                    	if(cell.getColumnIndex()==7){
                    		this.pac.setDescricao(cell.getStringCellValue());
                    	}
                    	
                    	if(cell.getColumnIndex()==8){
                    		this.pac.setDescricaoDetalhada(cell.getStringCellValue());
                    	}
                    	
                    	
                        
                        
                    }
                	
                	this.itensPac.add(this.pac);
                }
                
                
                
                
                
                
                    
            }
            
            System.out.println("Adicionando na Base de Dados....");
            int aux =1;
            for(Pac newPac : this.itensPac) {
            	this.pacRepository.save(newPac);
            	System.out.println("Adicionou Registro:"+aux);
            	aux++;
            }
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorPac.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeitorPac.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
			System.out.println("OLA"+e.getMessage());
		}
        finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(LeitorPac.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NullPointerException e) {
            	
            }
        }
        
       
    }

}
