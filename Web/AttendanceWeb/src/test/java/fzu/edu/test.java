/**
 * 
 */
package fzu.edu;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		User user = userServiceI.getUserById(1);
		System.out.println(user);
	}
}
