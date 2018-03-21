# Spring_Study
Mybatis를 이용하여 데이터 처리하기
Intellij 환경에서 실습 진행


1. pom.xml에 mybatis 라이브러리 추가하기

>        <dependency>
>            <groupId>org.mybatis</groupId>
>            <artifactId>mybatis</artifactId>
>            <version>3.2.8</version>
>         </dependency>
>         <dependency>
>            <groupId>org.mybatis</groupId>
>            <artifactId>mybatis-spring</artifactId>
>            <version>1.2.2</version>
>         </dependency>


2. dispatcher-servlet.xml 설정 추가

>      <beans:bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
>          <beans:property name="dataSource" ref="dataSource"/>
>          <beans:property name="mapperLocations" value="classpath*:mappers/*.xml"/>
>      </beans:bean>

>      <beans:bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
>          <beans:constructor-arg index="0" ref="sqlSessionFactory"></beans:constructor-arg>
>      </beans:bean>


3. mapper 패키지 생성 후 mapper.xml 생성하기

>      <?xml version="1.0" encoding="UTF-8"?>
>      <!DOCTYPE mapper
>          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
>          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
>
>      <mapper namespace="com.mvc.board.dao.IDao">
>        <select id="listDao" resultType="com.mvc.board.dto.ContentDto">
>          SELECT * FROM board ORDER BY mId DESC;
>        </select>
>
>        <insert id="writeDao">
>          INSERT INTO board (mId, mWriter, mContent) VALUES (0, #{param1}, #{param2})
>        </insert>
>
>        <delete id="deleteDao">
>          DELETE FROM board WHERE mId = #{param1}
>        </delete>
>      </mapper>

4. Controller에 적용하기 
>    ex) list 화면 보여주기
>>          @Autowired
>>          private SqlSession sqlSession;
>>
>>          @RequestMapping("/list")
>>          public String list(Model model) {
>>
>>            IDao dao = sqlSession.getMapper(IDao.class);
>>            model.addAttribute("list", dao.listDao());
>>            return "/list";
>>          }
