package test.gspstore;

import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 测试基类,启动Spring运行上下文
 * <功能详细描述>
 * 
 * @author   qiang.zhou 
 * @version  [版本号, 2012-5-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("baseService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring.xml",
        "classpath:spring-hibernate.xml" })
public abstract class SpringTestBase {

}
