package com.aaron.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountDao extends JdbcDaoSupport {

    @Autowired
    public AccountDao(DataSource dataSource){
        setDataSource(dataSource);
    }

    public void update(String name,Double money){
        Object[] args = {money,name};
        this.getJdbcTemplate().update("update account set money = ? where name = ?",args);
    }

    public Double queryMoney(String name){
        return this.getJdbcTemplate().queryForObject("select money from account where name = ?",new DoubleMapper(),name);
    }

    class DoubleMapper implements RowMapper<Double>{

        @Override
        public Double mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getDouble("money");
        }
    }
}
