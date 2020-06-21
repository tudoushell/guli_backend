package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class PersonListener extends AnalysisEventListener {

  @Override
  public void invoke(Object o, AnalysisContext analysisContext) {
    System.out.println(o);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
