package demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/8/8 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class JsoupTest {


    @Test
    public void testGetTitle() {
        try {
//            Document document = Jsoup.connect("https://www.washingtonpost.com/blogs/erik-wemple/wp/2018/08/07/quit-nitpicking-cnns-jim-acosta/?utm_term=.0c94cd084a79").get();
//            Document document = Jsoup.connect("http://www.foxnews.com/food-drink/2018/08/12/jeff-sessions-photo-leads-texas-restaurant-to-shut-down-social-media-accounts.html").get();
            Document document = Jsoup.connect("https://fxn.ws/2MD6baw")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
//                    .followRedirects(true)
                    .validateTLSCertificates(false)
                    .get();
//            Document document = Jsoup.connect("http://www.foxnews.com/politics/2018/08/12/police-sessions-slam-elizabeth-warren-for-calling-criminal-justice-system-racist.html").get();


            Element titleElement = document.select("title").first();
            System.out.println("title = " + titleElement.text());

            /*Elements imgElements = document.select("dev > img");
            for (Element element : imgElements) {
                System.out.println("image = " + element);
            }*/


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
