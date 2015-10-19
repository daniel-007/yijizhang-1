package cn.ahyc.yjz.config;
/**
 * AppConfiguration
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/10/14
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by sanlli on 15/10/14.
 */
@Configuration
public class AppConfiguration {

		/**
		 * ThreadPoolTaskExecutor.
		 * @return
		 */
		@Bean
		public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
				ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
				poolTaskExecutor.setQueueCapacity(10000);
				poolTaskExecutor.setCorePoolSize(5);
				poolTaskExecutor.setMaxPoolSize(100);
				poolTaskExecutor.setKeepAliveSeconds(5000);
				poolTaskExecutor.initialize();
				return  poolTaskExecutor;
		}

}
