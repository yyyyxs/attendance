/**
 * 
 */
package fzu.edu;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import fzu.edu.model.User;
import fzu.edu.service.UserServiceI;

/**
 * @author xiangtao
 *
 * 2017年3月30日
 */
public class test {
	@Test
	public void test1(){
		ApplicationContext xtt = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
		UserServiceI userServiceI = (UserServiceI)xtt.getBean("userService");
		List<User> user = userServiceI.getListByRole("1");
		System.out.println(JSON.toJSONString(user));
	}
}
