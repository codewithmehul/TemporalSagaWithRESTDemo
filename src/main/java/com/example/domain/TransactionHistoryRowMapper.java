package com.example.domain;

import com.example.domain.TransactionHistory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionHistoryRowMapper implements RowMapper<TransactionHistory> {
    @Override
    public TransactionHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setCustomerid(rs.getLong("customerid"));
        transactionHistory.setName(rs.getString("name"));
        transactionHistory.setBalance(rs.getBigDecimal("balance"));
        transactionHistory.setUpdate_timestamp(rs.getTimestamp("update_timestamp").toInstant());
        transactionHistory.setActivity(rs.getString("activity"));
        return transactionHistory;
    }
}
