package com.mvc.board.dao;

import com.mvc.board.dto.BDto;
import com.mvc.board.util.Constant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class BDao {

    DataSource dataSource;
    JdbcTemplate template = null;


    public BDao() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/spring_ex");
        }
        catch (NamingException e){
            e.printStackTrace();
        }

        template = Constant.template; // 할당
    }

    public ArrayList<BDto> list(){

        ArrayList<BDto> dtos = null;

        String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";

        //데이터를 가져올 커맨드 객체를 명시하여야 한다.
        dtos = (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));


        return dtos;
    }

    public BDto contentView(String strID) {
        upHit(strID);

        BDto dto;
        String query = "select * from mvc_board where bId = " + strID;

        //데이터를 가져오는 것이다
        dto = template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

        return dto;
    }

    public void write(final String bName, final String bTitle, final String bContent){
        // 글을 입력하는 과정

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "insert into mvc_board ( bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (?, ?, ?, 0, 0, 0,0)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, bName);
                preparedStatement.setString(2, bTitle);
                preparedStatement.setString(3, bContent);

                return preparedStatement;
            }
        });
    }
    public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
        String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, bName);
                preparedStatement.setString(2, bTitle);
                preparedStatement.setString(3, bContent);
                preparedStatement.setInt(4, Integer.parseInt(bId));
            }
        });

    }
    public void delete(final String bId) {
        String query = "delete from mvc_board where bId = ?";

        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, bId);

            }
        });
    }
    public void reply(String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {
        // TODO Auto-generated method stub

        replyShape(bGroup, bStep);
        String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(bID, ?, ?, ?, ?, ?, ?)";

        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, bName);
                preparedStatement.setString(2, bTitle);
                preparedStatement.setString(3, bContent);
                preparedStatement.setInt(4, Integer.parseInt(bGroup));
                preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
                preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
            }
        });


    }

    public BDto reply_view(String str) {
        BDto dto = null;
        String query = "select * from mvc_board where bID = " + str;

        // 실제 답변을 다는 것이 아니라 뷰 화면을 구해오는 것이기 때문에 Object를 받아온다.
        dto = template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

        return dto;
    }


    public void upHit(final String bId){

        String query = "update mvc_board set bHit = bHit+1 where bId =?";

        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, Integer.parseInt(bId));
            }
        });
    }
    private void replyShape(final String strGroup, final String strStep) {
        // 답변은 기본 글과 다르게 드려쓰기를 하기 위해서 호출하는 함수이다!
        String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, Integer.parseInt(strGroup));
                preparedStatement.setInt(2, Integer.parseInt(strStep));
            }
        });
    }
}
