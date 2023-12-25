package com.supretest.commons.utils;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.*;
import com.ruiyun.jvppeteer.protocol.DOM.Margin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class HtmlTransferUtils {


    private static final String PDF_FORMAT_A2 = "A2";

    private static final String PDF_FORMAT_A3 = "A3";

    private static final String PDF_FORMAT_A4 = "A4";

    private static String EXECUTABLE_PATH;

    @Value("${chrome.executable_path}")
    public void setExecutable_path(String path){
        HtmlTransferUtils.EXECUTABLE_PATH = path;
    }

    public static byte[] htmlToPdf(String pageUrl) throws InterruptedException, IOException, ExecutionException {

        PDFOptions pdfOptions = new PDFOptions();
        //A2纸尺寸
        pdfOptions.setFormat(PDF_FORMAT_A2);
        pdfOptions.setPrintBackground(true);
        Margin margin = new Margin();
        margin.setLeft("100px");
        pdfOptions.setMargin(margin);
        PageNavigateOptions navigateOptions = new PageNavigateOptions();
        //dom加载完毕就算导航完成;
        navigateOptions.setWaitUntil(Collections.singletonList("networkidle0"));
        Browser browser = getBrowser();
        try {

            Page page = browser.newPage();
            // 页面有canvas时，需设置viewport，否则canvas尺寸和质量会失真
            gotoPage(page, setViewport(1280, 1024, 5),pageUrl);
//            page.goTo(pageUrl, navigateOptions);
            return page.pdf(pdfOptions);

        } finally {
            browser.close();
        }

    }

        private static void gotoPage(Page page, Viewport viewport, String pageUrl) throws InterruptedException, IOException, ExecutionException {

            if (Optional.ofNullable(viewport).isPresent()) {
                page.setViewport(viewport);
            }
            PageNavigateOptions navigateOptions = new PageNavigateOptions();
            //dom加载完毕就算导航完成;
            navigateOptions.setWaitUntil(Collections.singletonList("networkidle0"));
//            page.waitForNavigation(navigateOptions);
            page.goTo(pageUrl, navigateOptions);
        }


    private static Browser getBrowser() throws InterruptedException, IOException, ExecutionException {

        //自动下载，第一次下载后不会再下载，目前该步骤已放到镜像build中
//        BrowserFetcher.downloadIfNotExist(null);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("--no-sandbox");
        arrayList.add("--disable-setuid-sandbox");

        //生成pdf必须在无厘头模式下才能生效
        LaunchOptions launchOptions = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).withExecutablePath(EXECUTABLE_PATH).build();
        LogUtil.info("executable path is: {}", launchOptions.getExecutablePath() );

        if (Optional.ofNullable(launchOptions).isPresent()) {
             return Puppeteer.launch(launchOptions);
        } else {
            return Puppeteer.launch(true);
        }
    }


    private static Viewport setViewPort(int width, int height) {
        return setViewport(width, height, 1);
    }

    private static Viewport setViewport(int width, int heigtt, int scale) {
        Viewport viewport = new Viewport();
        viewport.setWidth(width);
        viewport.setHeight(heigtt);
        viewport.setDeviceScaleFactor(scale);
        return viewport;

    }
}
