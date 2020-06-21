package excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

public class ExcelDemo {

  /**
   * easy excel 读
   */
  @Test
  public void testRead() {
    String fileName = "/Users/happy/Desktop/person.xlsx";
    // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
    EasyExcel.read(fileName, Person.class, new PersonListener()).sheet().doRead();
  }
}
