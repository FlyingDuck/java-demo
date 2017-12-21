package demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/19 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }


    @Override
    protected String run() throws Exception {
        if ("Virus".equalsIgnoreCase(name))
            throw new Exception("Virus");
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "Has Virus";
    }
}
