package com.test.demo;

import com.alibaba.fastjson.JSON;
import com.caox.ReqDTO.TestReqDTO;
import com.caox.ThreadPool.FactoryBean.Car;
import com.caox.ThreadPool.FactoryBean.CarFactoryBean;
import com.caox.ThreadPool.FactoryBean.FactoryBeanPojo;
import com.caox.ThreadPool.HelloWorldService;
import com.caox.dao.DemoDao;
import com.caox.dao.IUserDao;
import com.caox.model.User;
import com.caox.service.DemoService;
import com.caox.utils.PhoneAddrUtil;
import com.caox.utils.VerifyHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/28 10:27
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {

    private static Logger logger = Logger.getLogger(RedisTest.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private DemoService demoService;

    @Autowired
    private DemoDao demoDao;
//
//    @Autowired
//    private QueueSender queueSender;
//
//    @Autowired
//    private TopicSender topicSender;

//    @Autowired
//    private RocketService rocketService;

    @Test
    public void testDB(){
        StringBuilder sb = new StringBuilder();
        List<Map<String,Object>> arr = demoService.getAddress("testAddress");
        if(arr != null){
            for(Map<String,Object> map : arr){
                sb.append("id :"+map.get("id").toString()).append("\t");
                sb.append("address :"+map.get("address").toString()).append("\t");
                sb.append("remark :"+map.get("remark").toString()).append("\t");
                sb.append("\r\n");
            }
        }else {
            sb.append("no data");
        }
        System.out.println(sb);
        log.info("call testDB : {}",sb);
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setId("402891815170e8de015170f6520b0000");
        user.setUserName("zhangsan");
        boolean res = userDao.save(user);
        Assert.assertTrue(res);
        log.info("call testSaveUser :{}", res);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user = userDao.find("402891815170e8de015170f6520b0000");
        System.out.println("testGetUser:" + user.getId() + "-" + user.getUserName() );
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId("402891815170e8de015170f6520b0000");
        user.setUserName("lisi");
        boolean res = userDao.update(user);
        Assert.assertTrue(res);
    }

    @Test
    public void testDeleteUser() {
        boolean res = userDao.delete("402891815170e8de015170f6520b0000");
        Assert.assertTrue(res);
        log.info("call testDeleteUser :{}", res);
    }

    @Test
    public void test2(){
//        String requestUrl = "/index/";
        String requestUrl = "/task/processDiagramTrace/441829821983404487374db7fc9c96b6";
//        String requestUrl = "/task/getProcessTaskToDoPage";

        String requestUrlsByGet = "/task/processDiagramTrace/";
        String compareParamUrl = requestUrl.split("/")[requestUrl.split("/").length -1];

        String ignoreMenuList = "/task/processDiagramTrace/,/abc/url,/task/getProcessTaskToDoPage";
        String compareParamUrlPre = requestUrl.split(compareParamUrl)[0];
        if(!("/".equals(compareParamUrlPre)) && Arrays.asList(requestUrlsByGet.trim().split(",")).contains(compareParamUrlPre)){
            requestUrl = compareParamUrlPre;
        }
        log.info("call compareParamUrl :{}", compareParamUrl);
        log.info("call requestUrl:{}",requestUrl);
        log.info("call ignoreMenuList :{}", Arrays.asList(ignoreMenuList.split(",")));
        log.info("call compareParamUrl :{}", Arrays.asList(ignoreMenuList.trim().split(",")).contains(requestUrl.trim()));
        log.info("call compareParamUrlPre :{}", compareParamUrlPre);
    }

    @Test
    public void testActiveMq(){
        String queueName = "test.queue";
        String message = "hello ugly girl twice!";

//        queueSender.send(queueName, message);
//        topicSender.send("test.topic",message);
    }

    @Test
    public void testRocketMQ() throws Exception{
//        rocketService.send("test_rocketmq_topic2","rocketmq_tag2","hello rocketmq !");
    }

    /**
     * 可缓存线程池 无界线程池，可以进行自动线程回收
     */
    @Test
    public void testNewCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /**
     * newFixedThreadPool 固定大小线程池
     */
    @SuppressWarnings("all")
    @Test
    public void testNewFixedThreadPool(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        log.info("定长线程池的大小:{}", Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            final int index = i;

            if(!fixedThreadPool.isShutdown()){
                fixedThreadPool.execute(new Runnable() {
                    public void run() {
                        try {
                            System.out.println(index);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * 单线程化的Executor
     */
    @SuppressWarnings("all")
    @Test
    public void testNewSingleThreadExecutor(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            if(!singleThreadExecutor.isShutdown()){
                singleThreadExecutor.execute(new Runnable() {
                    public void run() {
                        try {
                            System.out.println(index);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * 定时周期性任务执行 newScheduleThreadPool
     */
    @Test
    public void testNewScheduleThreadPool(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        if(!scheduledThreadPool.isShutdown()){
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

    @Test
    public void testNewScheduleThreadPool2(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    @Test
    public void testCallableFuture(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<?> result = executor.submit(task);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

    @Test
    public void  testCallableFutureTask(){
        //第一种方式
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        executor.submit(futureTask);
//        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

    @Test
    public void testM() {
        // TODO todo.generated by zoer
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 1000, 2000);
    }

    @Test
    public void testFactoryBean(){
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("fbHelloWorldService");
        helloWorldService.getBeanName();
        helloWorldService.sayHello();
        log.info("RESULT:=======" + context.getBean("&fbHelloWorldService"));
    }

    @Test
    public void testCarFactoryBean() throws Exception{
        // 获取CarFactoryBean的实例
        CarFactoryBean carFactoryBean = (CarFactoryBean) context.getBean("&car");
        Car car = carFactoryBean.getObject();
        log.info("call testCarFactoryBean RESULT:{}", car);
    }

    @Test
    public void testFactoryBeanPojo(){
//        ResourceBundle bundle = ResourceBundle.getBundle("application-context.xml");
        String url = this.getClass().getClassLoader().getResource("application-context.xml").getPath();
//        String url = bundle.getString("url");
//        String url = System.getProperty("user.dir")+"/src/main/resources/application-context.xml";
//        String fileName = this.getClass().getClassLoader().getResource("application-context.xml").getPath();

        url = "classpath*:application-context.xml";
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext(url);
        Object school=  cpxa.getBean("factoryBeanPojo");
        FactoryBeanPojo factoryBeanPojo= (FactoryBeanPojo) cpxa.getBean("&factoryBeanPojo");
        System.out.println(school.getClass().getName());
        System.out.println(factoryBeanPojo.getClass().getName());
    }

    @Test
    public void testApplicationProperties(){
        String fileName = this.getClass().getClassLoader().getResource("application-context.xml").getPath();
        log.info("call testApplicationProperties : {}", fileName);

        String url = this.getClass().getResource("application-context.xml").getPath();
        log.info("call testApplicationProperties : {}", url);

    }

    @Test
    public void testAtomicLong(){
        AtomicLong mAtoLong = new AtomicLong();

        Long uid = mAtoLong.incrementAndGet();
        log.info("call testAtomicLong :{}",uid);
    }

    @Test
    public void testValidate(){
        TestReqDTO reqDTO = new TestReqDTO();
        List<User> userList = reqDTO.getUserList();
        String abc = JSON.toJSONString(userList);
//        VerifyHelper.validatorObject(abc);
        VerifyHelper.validatorObject(reqDTO);
    }

    @Test
    public void test(){
       boolean abc =  (5.3*0.1 == 0.3);
        log.info("call abc :{}",abc);
    }

    @Test
    public void testPhoneAddr(){
       log.info("call phone addr : {}", PhoneAddrUtil.checkPhoneNumber("19881961784","86"));
       log.info("call phone carrier : {}", PhoneAddrUtil.getCarrier("19881961784","86"));
       log.info("call phone Geo : {}", PhoneAddrUtil.getGeo("19881961784","86"));
       log.info("call phone loadJSON :{}", PhoneAddrUtil.loadJSON("13761290000"));
    }




    @Test
    public void testabc(){
        Long a = 120838L;
        boolean abc = (a < 400000L);
        log.info("testabc:{}",abc);
    }

    @Test
    public void listNoRepeat(){
        String[] mails = {"abc","bcd","abc"};
        Set<String> set=new HashSet<String>(Arrays.asList(mails));
        log.info("call list no repeat way 1:{}" ,set.toArray(new String[set.size()]));

        log.info("call list no repeat way 2:{}", set.toArray(new String[0]));
    }

}

class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}

class MyTask extends TimerTask {
    @Override
    public void run(){
        System.out.println("dddd");
    }
}
