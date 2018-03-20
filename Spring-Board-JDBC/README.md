# Spring_Study
JDBC를 이용해서 기존 게시판 코드를 조금 더 간단하게 진행할 수 있다.

![image](https://user-images.githubusercontent.com/31613683/37639758-fb41a692-2c55-11e8-90ff-d6f2bdefd7ae.png)

1. JDBC dependency 설정
>         <dependency>
>            <groupId>org.springframework</groupId>
>            <artifactId>spring-jdbc</artifactId>
>            <version>4.1.4.RELEASE</version>
>        </dependency>


2. dispatcher-servlet.xml 수정
>      <beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
>             <beans:property name="driverClassName" value="com.mysql.jdbc.Driver" />
>             <beans:property name="url" value="jdbc:mysql://localhost:3306/spring_ex?characterEncoding=UTF-8" />
>             <beans:property name="username" value="root"/>
>             <beans:property name="password" value="1234" />
>      </beans:bean>

    
    
3. util 패키지 생성 후 Constant 클래스 생성

>        import org.springframework.jdbc.core.JdbcTemplate;
>
>        public class Constant {
>          public static JdbcTemplate template;
>        }

4. Dao

> JdbcTemplate template = null;
>  public BDao() {
>        template = Constant.template; // 할당
>    }

5. 각 기능 구현
> ex) list

>>   public ArrayList<BDto> list(){
>>
>>     ArrayList<BDto> dtos = null;
>>
>>     String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
>>
>>     //데이터를 가져올 커맨드 객체를 명시하여야 한다.
>>     dtos = (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
>>
>>     return dtos;
>>    }
