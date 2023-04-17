package work2;


import java.io.*;
import java.sql.ResultSet;
import java.util.*;

import org.apache.poi.ss.usermodel.*;




public class Read {
    public static List<Heronote> getAllByExcel(String file) throws IOException {
       /* Properties props = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        props.load(new InputStreamReader(in, "UTF-8"));
        Map<String,String>columnFieldMappings = new HashMap<>();
        Enumeration columnNames = props.propertyNames();
        while(columnNames.hasMoreElements()){
            String columnName = (String) columnNames.nextElement();
            if (columnName.startsWith("column.name.")){
                String fieldName = props.getProperty("field.name."+columnName.substring(12));
                columnFieldMappings.put(columnName.substring(12),fieldName);
            }
        }*/


        Workbook workbook = WorkbookFactory.create(new File(file));
        Sheet sheet = workbook.getSheetAt(0);

        /*String primaryKey = props.getProperty("primary.Key");

        String excelId = props.getProperty("column.name.id");
        String excelName = props.getProperty("column.name.name");
        String excelHp = props.getProperty("column.name.hp");
        String excelDamage = props.getProperty("column.name.damage");*/

        Config config = ConfigUtil.getValues();
        String primaryKey = config.getPrimaryKey();

        String excelId = config.getColumnNameId();
        String excelName = config.getColumnNameName();
        String excelHp = config.getColumnNameHp();
        String excelDamage = config.getColumnNameDamage();

        int rowId = -1;
        int rowName = -1;
        int rowHp = -1;
        int rowDamage = -1;

        Row row2 = sheet.getRow(0);
        for (int i = 0; i < row2.getLastCellNum(); i++) {
            if(excelId.equals(row2.getCell(i).getStringCellValue())){
                rowId = i;
            }
            if(excelName.equals(row2.getCell(i).getStringCellValue())){
                rowName = i;
            }
            if(excelHp.equals(row2.getCell(i).getStringCellValue())){
                rowHp = i;
            }
            if(excelDamage.equals(row2.getCell(i).getStringCellValue())){
                rowDamage = i;
            }
        }


        List<Heronote> datalist = new ArrayList<>();
        for (int i = 1;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            Heronote heronote = new Heronote();
            Object primaryKeyValue = null;
            Cell cell = row.getCell(rowId);
            heronote.setId(cell.getCellType()==CellType.NUMERIC ?((int)cell.getNumericCellValue()):Integer.parseInt(cell.getStringCellValue()+""));
            cell = row.getCell(rowName);
            heronote.setName(cell.getStringCellValue());
            cell = row.getCell(rowHp);
            heronote.setHp(cell.getCellType()==CellType.NUMERIC ?cell.getNumericCellValue()+"":cell.getStringCellValue());
            cell = row.getCell(rowDamage);
            heronote.setDamage(cell.getCellType()==CellType.NUMERIC ?((int)cell.getNumericCellValue()):Integer.parseInt(cell.getStringCellValue()+""));
            datalist.add(heronote);
        }
        return datalist;
    }


    //通过id判断是否存在
    public static boolean isExist(int id) {
        try {
            DBLink db = new DBLink();
            ResultSet rs = db.Search("select * from hero where id =? ", new String[]{id + ""});
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}


