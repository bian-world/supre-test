import Sampler from "../sampler";
import {Body} from "../../../../../model/ApiTestModel";

const DEFAULT_OPTIONS = {
  options: {
    attributes: {
      enabled: "true"
    },
  }
};
export default class WebDriverSampler extends Sampler {
  constructor(options = DEFAULT_OPTIONS) {
    super(options);
    this.type = "StWebDriverSampler";
    // this.protocol = "HTTP";
    // this.domain = undefined;
    // this.port = undefined;
    // this.method = undefined;
    // this.path = undefined;
    // this.contentEncoding = undefined;
    //
    // this.autoRedirects = false;
    // this.followRedirects = true;
    // this.useKeepalive = true;
    // this.postBodyRaw = undefined;
    // this.doMultipartPost = false;
    // this.browserCompatibleMultipart = undefined;
    // this.embeddedUrlRe = undefined;
    // this.connectTimeout = 60000;
    // this.responseTimeout = 60000;
    // // 初始化主体对象
    // this.arguments = [];
    // this.rest = [];
    // this.files = [];
    // this.headers = [];
    this.script = "WDS.sampleResult.sampleStart()\n" +
      "WDS.browser.get(\"https://www.baidu.com/\");\n" +
      "var searchBox = WDS.browser.findElement(org.openqa.selenium.By.id(\"kw\"));\n" +
      "\n" +
      "searchBox.sendKeys('久曲健 博客园');\n" +
      "searchBox.sendKeys(org.openqa.selenium.Keys.ENTER);\n" +
      "//sleep(3000);\n" +
      "// 4. Verify successful form submission\n" +
      "var results = WDS.browser.findElements(org.openqa.selenium.By.cssSelector(\"div > h3 > a[target='_blank']\"));\n" +
      "//var encodedString = await WDS.browser.takeScreenshot();\n" +
      "//await fs.writeFileSync('/Users/zouxingli/Desktop/image.png', encodedString, 'base64');\n" +
      "if(results.empty) {\n" +
      "    WDS.sampleResult.successful = false\n" +
      "    WDS.sampleResult.responseMessage = 'There were no results returned'\n" +
      "}\n" +
      "WDS.sampleResult.sampleEnd()";
    this.hashTree = [];
  }
}

export const schema = {
  WebDriverSampler: WebDriverSampler
}

