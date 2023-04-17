package work2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException {
        /*Properties props = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        props.load(new InputStreamReader(in, "UTF-8"));

        String dbId = props.getProperty("field.name.id");
        String dbName = props.getProperty("field.name.name");
        String dbHp = props.getProperty("field.name.hp");
        String dbDamage = props.getProperty("field.name.damage");*/

        Config config = ConfigUtil.getValues();
        String dbId =config.getFieldNameId() ;
        String dbName = config.getFieldNameName();
        String dbHp = config.getFieldNameHp();
        String dbDamage = config.getFieldNameDamage();


        //得到表格中所有的数据
        List<Heronote> listExcel = Read.getAllByExcel("D:\\study\\group 4\\work\\hero.xls");
        for (Heronote heronote : listExcel) {
            System.out.println(heronote);
        }
        DBLink db = new DBLink();
        for (Heronote heronote : listExcel) {
            int id = heronote.getId();
            if (!Read.isExist(id)) {
                //不存在就添加
                String sql = "Insert into hero (" + dbName + "," + dbHp + "," + dbDamage + ") values (?,?,?)";
                String[] str = new String[]{heronote.getName(), heronote.getHp(), heronote.getDamage() + ""};
                db.AddU(sql, str);
            } else {
                //存在就更新
                String sql = "update hero set "+dbName+"=?,"+dbHp+"=?,"+dbDamage+"=? where "+dbId+"=?";
                String[] str = new String[]{heronote.getName(), heronote.getHp(), heronote.getDamage() + "", id + ""};
                db.AddU(sql, str);
            }
        }
    }
}
