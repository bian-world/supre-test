package com.supretest.ui.parse;

import com.supretest.base.domain.UiElement;
import com.supretest.base.mapper.ApiScenarioMapper;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.LogUtil;
import com.supretest.service.TestResourcePoolService;
import com.supretest.ui.dto.UiScriptMappingDTO;
import com.supretest.ui.service.UiElementService;
import com.supretest.ui.service.UiScriptMappingService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UiStepToScript {

    private UiScriptMappingService uiScriptMappingService;

    private UiElementService uiElementService;

    public UiStepToScript() {
        uiScriptMappingService = CommonBeanFactory.getBean(UiScriptMappingService.class);
        uiElementService = CommonBeanFactory.getBean(UiElementService.class);
    }
    /**
     * 将步骤转化为js脚本
     * @param step
     * @return script
     */
    public String stepToScript(HashMap step) {
        String script = "var pkg = JavaImporter(org.openqa.selenium, org.openqa.selenium.support.ui);";
        UiScriptMappingDTO uiScriptMappingDTO = new UiScriptMappingDTO();
        try {
            if(step.get("operationType").toString().equals("WebElement")) {
                UiElement uiElement = uiElementService.getElement((String) step.get("elementId"));
//                    uiElementService.getLocationType((String)uiElement.getLocationType());
                step.put("locationType", uiElementService.getLocationTypeById(uiElement.getLocationType()));
                step.put("location", uiElement.getLocation());
            } else if (step.get("operationType").toString().equals("browser")) {
                step.put("locationType", "browser");
            } else if (step.get("operationType").toString().equals("JavascriptExecutor")) {
                step.put("locationType", "JavascriptExecutor");
            }
            uiScriptMappingDTO.setLocationType((String) step.get("locationType"));
            uiScriptMappingDTO.setOperation((String) step.get("operation"));
            String get_script = uiScriptMappingService.selectByCondition(uiScriptMappingDTO).getScript();

            if(step.get("operationType").toString().equals("WebElement")) {
                // 元素操作

                if (StringUtils.isEmpty(step.get("waitTime").toString())){
                    // 默认等待10S
                    String waitTime = "new pkg.WebDriverWait(WDS.browser, 10).until(pkg.ExpectedConditions.presenceOfElementLocated(pkg.By.%s('%s')));";
                    script = script + String.format(waitTime,  step.get("locationType"), step.get("location"));
                }else {
                    String waitTime = "new pkg.WebDriverWait(WDS.browser, %s).until(pkg.ExpectedConditions.presenceOfElementLocated(pkg.By.%s('%s')));";
                    script = script + String.format(waitTime, step.get("waitTime"), step.get("locationType"), step.get("location"));
                }
                String script_tmp = String.format(get_script, step.get("location"), step.get("sendKeys"));
                script =  script + "try{" + script_tmp + " }catch(error){}finally{ var tempFile = WDS.browser.getScreenshotAs(pkg.OutputType.BASE64);WDS.sampleResult.setResponseHeaders(tempFile);}";
            }
            else if (step.get("operationType").toString().equals("browser")) {
                // 浏览器操作

                if (step.get("operation").equals("open")) {
                    String script_tmp = String.format(get_script, step.get("sendKeys"));
                    script = script + script_tmp + "WDS.browser.manage().window().setSize(new pkg.Dimension(1920, 1080));var tempFile = WDS.browser.getScreenshotAs(pkg.OutputType.BASE64);WDS.sampleResult.setResponseHeaders(tempFile);";

                } else if (step.get("operation").equals("refresh") || step.get("operation").equals("forward") || step.get("operation").equals("back")) {
                    script = script + get_script + "var tempFile = WDS.browser.getScreenshotAs(pkg.OutputType.BASE64);WDS.sampleResult.setResponseHeaders(tempFile);";

                } else if (step.get("operation").equals("to")) {
                    String script_tmp = String.format(get_script, step.get("sendKeys"));
                    script = script + script_tmp + "var tempFile = WDS.browser.getScreenshotAs(pkg.OutputType.BASE64);WDS.sampleResult.setResponseHeaders(tempFile);";
                }
            } else if (step.get("operationType").toString().equals("JavascriptExecutor")) {
                if (step.get("operation").equals("executeScript")) {
                    String script_tmp = String.format(get_script, step.get("sendKeys"));
                    script = script + script_tmp + "var tempFile = WDS.browser.getScreenshotAs(pkg.OutputType.BASE64);WDS.sampleResult.setResponseHeaders(tempFile);";
                }
            }
        }catch (Exception e){
            LogUtil.info(uiScriptMappingDTO.toString()+"step，script映射关系不存在");
        }
        return script;
    }

}
