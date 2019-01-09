package test.java;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangmeng
 * @date 19/1/8
 */
public class PerformanceTest implements JavaSamplerClient{
    private SampleResult results;
    private String a;
    private String b;
    private String filename;


//    private static Logger logger = LogManager.getLogger(PerformanceTest.class);
    private static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);



@Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("filename", "0");//设置参数，并赋予默认值0
        params.addArgument("a", "0");//设置参数，并赋予默认值0
        params.addArgument("b", "0");//设置参数，并赋予默认值0
        return params;
    }

@Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
    results = new SampleResult();
        System.out.println("setup test");
}

    @Override
    public SampleResult runTest(JavaSamplerContext arg0) {
        b = arg0.getParameter("b"); // 获取在Jmeter中设置的参数值
        a = arg0.getParameter("a"); // 获取在Jmeter中设置的参数值
        filename = arg0.getParameter("filename"); // 获取在Jmeter中设置的参数值
        results.sampleStart();// jmeter 开始统计响应时间标记
        try {
            com.xiaomi.services.OutPutService test = new com.xiaomi.services.OutPutService();
            test.output(filename,Integer.parseInt(a), Integer.parseInt(b));
            results.setSuccessful(true);
// 被测对象调用
        } catch (Throwable e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } finally {
            results.sampleEnd();// jmeter 结束统计响应时间标记
        }
        return results;
    }
@Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("tearDown test");
    }

    public static void main(String[] args) {
        logger.debug("logger debug");
        Arguments params = new Arguments();
        params.addArgument("a", "0");//设置参数，并赋予默认值0
        params.addArgument("b", "0");//设置参数，并赋予默认值0
        params.addArgument("filepath","");
        JavaSamplerContext arg0 = new JavaSamplerContext(params);
        PerformanceTest test = new PerformanceTest();
        test.setupTest(arg0);
        test.runTest(arg0);
        test.teardownTest(arg0);
    }
}
