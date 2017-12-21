package demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/22 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class SongServiceCommand extends HystrixCommand<String> {

    private SongService songService;

    public SongServiceCommand(SongService songService) {
        super(setter());
        this.songService = songService;
    }


    @Override
    protected String run() throws Exception {
        return songService.sing("一次就好");
    }

    @Override
    protected String getFallback() {
        return "Sing nothing";
    }

    private static Setter setter() {
        // 服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("SongService");

        // 命令配置
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                .withFallbackEnabled(true)
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(100)
                .withExecutionIsolationThreadInterruptOnFutureCancel(true)
                .withExecutionIsolationThreadInterruptOnTimeout(true)
                .withExecutionTimeoutEnabled(true)
                .withExecutionTimeoutInMilliseconds(300);
        return HystrixCommand.Setter
                .withGroupKey(groupKey)
                .andCommandPropertiesDefaults(commandProperties);
    }
}
