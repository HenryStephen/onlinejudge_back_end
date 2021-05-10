package cn.edu.nciae.onlinejudge.judge;

import cn.edu.nciae.onlinejudge.judge.api.CompileServiceApi;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghonglin
 * @version 1.0
 * @date 2021/5/1 19:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JudgeTest {

    @Reference(version = "1.0.0", check = false)
    private CompileServiceApi compileServiceApi;

    @Test
    public void testCompileSelect(){
        System.out.println(compileServiceApi.getCompileById(1));
    }
}
