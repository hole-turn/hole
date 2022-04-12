package com.xlh.service.impl;

import com.xlh.common.base.Result;
import com.xlh.expection.BusinessException;
import com.xlh.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: xielinhao
 * @title: RetryServiceImpl
 * @projectName: holeturn
 * @description:
 * @date: 13:29 2022/4/12
 */
@Slf4j
@Service
public class RetryServiceImpl implements RetryService {

    /**
     * @Retryable：加在方法上，就会给这个方法赋能，让它有用重试的功能。 value：抛出指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所有异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；
     * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒
     */
    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public Result get(String uuid) {
        log.info("调用时间,{}", LocalDateTime.now());
        if (!"hello".equals(uuid)) {
            throw new BusinessException("错误！");
        }
        log.info("调用成功！");
       return Result.success();
    }

    /**
     * 当重试耗尽时，RetryOperations可以将控制传递给另一个回调，即RecoveryCallback。
     * Spring-Retry还提供了@Recover注解，用于@Retryable重试失败后处理方法。
     * 如果不需要回调方法，可以直接不写回调方法，那么实现的效果是，重试次数完了后，如果还是没成功没符合业务判断，就抛出异常
     * <p>
     * <p>
     * 可以看到传参里面写的是 Exception e，这个是作为回调的接头暗号（重试次数用完了，还是失败，我们抛出这个Exception e通知触发这个回调方法）。
     * 对于@Recover注解的方法，需要特别注意的是：
     * 方法的返回值必须与@Retryable方法一致
     * 方法的第一个参数，必须是Throwable类型的，建议是与@Retryable配置的异常一致，其他的参数，需要哪个参数，写进去就可以了（@Recover方法中有的）
     * 该回调方法与重试方法写在同一个实现类里面
     */
    @Recover
    public Result recover(Exception e, String uuid) {

        log.error("回调方法执行uuid:{} , errorMsg:{}", uuid, e.getMessage());
        return Result.fail("回调成功");

    }
}
