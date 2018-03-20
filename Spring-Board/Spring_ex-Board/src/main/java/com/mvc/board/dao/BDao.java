package com.mvc.board.dao;

import com.mvc.board.dto.BDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BDao {

    DataSource dataSource;

    public void delete(String bId) {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            connection = dataSource.getConnection();
            String query = "delete from mvc_board where bId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(bId));
            int rn = preparedStatement.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }
    public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
        // TODO Auto-generated method stub

        replyShape(bGroup, bStep);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(0, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, bName);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setString(3, bContent);
            preparedStatement.setInt(4, Integer.parseInt(bGroup));
            preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
            preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);

            int rn = preparedStatement.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }

    }

    public BDto reply_view(String str) {
        BDto dto = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;  // 결과값을 출력하기 위해서 필요하다.

        try{
            connection = dataSource.getConnection();
            String query = "select * from mvc_board where bID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(str));
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int bId = resultSet.getInt("bId");
                String bName = resultSet.getString("bName");
                String bTitle = resultSet.getString("bTitle");
                String bContent = resultSet.getString("bContent");
                Timestamp bDate = resultSet.getTimestamp("bDate");
                int bHit = resultSet.getInt("bHit");
                int bGroup = resultSet.getInt("bGroup");
                int bStep = resultSet.getInt("bStep");
                int bIndent = resultSet.getInt("bIndent");

                dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }



        return dto;
    }
    public void modify(String bId, String bName, String bTitle, String bContent) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = dataSource.getConnection();

            String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bName);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setString(3, bContent);
            preparedStatement.setInt(4, Integer.parseInt(bId));

            int rn = preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }


    public BDto contentView(String strID) {
        upHit(strID);

        BDto dto = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = dataSource.getConnection();

            String query = "select * from mvc_board where bId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(strID));
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int bId = resultSet.getInt("bId");
                String bName = resultSet.getString("bName");
                String bTitle = resultSet.getString("bTitle");
                String bContent = resultSet.getString("bContent");
                Timestamp bDate = resultSet.getTimestamp("bDate");
                int bHit = resultSet.getInt("bHit");
                int bGroup = resultSet.getInt("bGroup");
                int bStep = resultSet.getInt("bStep");
                int bIndent = resultSet.getInt("bIndent");

                dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return dto;
    }

    public BDao() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/spring_ex");
        }
        catch (NamingException e){
            e.printStackTrace();
        }
    }
    public void write(String bName, String bTitle, String bContent){
        // 글을 입력하는 과정

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = dataSource.getConnection();
            String query = "insert into mvc_board ( bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (?, ?, ?, 0, 0, 0,0)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, bName);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setString(3, bContent);

            int rn = preparedStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) preparedStatement.close();
            }
            catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public ArrayList<BDto> list(){

        ArrayList<BDto> dtos = new ArrayList<BDto>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = dataSource.getConnection();
            String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(); //결과물

            while(resultSet.next()){
                int bId = resultSet.getInt("bId");
                String bName = resultSet.getString("bName");
                String bTitle = resultSet.getString("bTitle");
                String bContent = resultSet.getString("bContent");
                Timestamp bDate = resultSet.getTimestamp("bDate");
                int bHit = resultSet.getInt("bHit");
                int bGroup = resultSet.getInt("bGroup");
                int bStep = resultSet.getInt("bStep");
                int bIndent = resultSet.getInt("bIndent");

                BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
                dtos.add(dto);
            }

        }catch (Exception e){

        }finally {
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement !=  null)  preparedStatement.close();
                if(connection != null ) connection.close();
            }
            catch (Exception e){

            }
            }

        return dtos;
    }
    public void upHit(String bId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = dataSource.getConnection();
            String query = "update mvc_board set bHit = bHit+1 where bId =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bId);

            int rn = preparedStatement.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
    private void replyShape( String strGroup, String strStep) {
        // 답변은 기본 글과 다르게 드려쓰기를 하기 위해서 호출하는 함수이다!
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(strGroup));
            preparedStatement.setInt(2, Integer.parseInt(strStep));

            int rn = preparedStatement.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
    }
}
