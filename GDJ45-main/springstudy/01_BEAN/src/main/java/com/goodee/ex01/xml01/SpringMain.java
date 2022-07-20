package com.goodee.ex01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// GenericXmlApplicationContext 클래스, ClassPathXmlApplicationContext 클래스
		// 1. spring bean configuration file에 등록된 <bean>을 가져오는 스프링 클래스
		// 2. AbstractApplicationContext 클래스의 자식 클래스
		
		// <bean>을 가지고 올 context(xml) 지정하기
		String resourceLocations = "classpath:xml/context01.xml";  // src/main/resources 아래 xml 폴더에 저장된 context01.xml을 의미한다.
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(resourceLocations);
		
		// <bean> 가지고 오기
		Calculator calc1 = ctx.getBean("calculator1", Calculator.class);
		calc1.add(1, 1);
		calc1.sub(2, 1);
		calc1.add(3, 2);
		calc1.div(5, 2);
		calc1.mod(7, 3);
		
		// <bean> 가지고 오기
		EngineerCalculator eCalc1 = ctx.getBean("eCalculator1", EngineerCalculator.class);
		eCalc1.add();
		eCalc1.sub();
		eCalc1.mul();
		eCalc1.div();
		eCalc1.mod();
		
		// <bean> 가지고 오기
		EngineerCalculator eCalc2 = ctx.getBean("eCalculator2", EngineerCalculator.class);
		eCalc2.add();
		eCalc2.sub();
		eCalc2.mul();
		eCalc2.div();
		eCalc2.mod();
		
		// <bean> 가지고 오기
		EngineerCalculator eCalc3 = ctx.getBean("eCalculator3", EngineerCalculator.class);
		eCalc3.add();
		eCalc3.sub();
		eCalc3.mul();
		eCalc3.div();
		eCalc3.mod();
		
		// <bean> 가지고 오기
		EngineerCalculator eCalc4 = ctx.getBean("eCalculator4", EngineerCalculator.class);
		eCalc4.add();
		eCalc4.sub();
		eCalc4.mul();
		eCalc4.div();
		eCalc4.mod();
		
		// <bean>의 scope 이야기
		// 스프링이 만들어 놓은 <bean>을 여러 번 요청하면
		// 매번 새로 만들어 줄까? 아니면 하나만 만들어 놓고 계속 같은 걸 줄까?
		
		// 확인해 보면 하나만 만들어 놓고 계속 같은 걸 주는 것을 볼 수 있다.
		// 스프링은 <bean> 태그를 보고 객체를 만들 때 기본적으로(디폴트) "singleton"으로 만든다.
		
		Calculator c1 = ctx.getBean("calculator1", Calculator.class);
		Calculator c2 = ctx.getBean("calculator1", Calculator.class);
		
		System.out.println(c1 == c2);  // 참조값이 같으면 같은 객체라는 의미이다.
		
		
		// <bean>의 scope를 변경하려면
		// <bean> 태그에 scope 속성을 추가하면 된다.
		// context01.xml 파일을 열고 확인해 봅니다.
		// calculator2는 scope="prototype"으로 설정했다.
		Calculator c3 = ctx.getBean("calculator2", Calculator.class);
		Calculator c4 = ctx.getBean("calculator2", Calculator.class);
		
		System.out.println(c3 == c4);  // 참조값이 다르면 다른 객체라는 의미이다.
		
		ctx.close();

	}

}
