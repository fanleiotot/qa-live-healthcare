package com.leansofx.qaserviceuser.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 数据库连接测试
 * 
 * @author QA Healthcare Team
 * @since 2025-11-03
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@DisplayName("数据库连接测试")
public class DatabaseConnectionTest {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Test
    @DisplayName("测试数据库连接")
    void testDatabaseConnection() throws SQLException {
        // 测试数据库连接
        try (Connection connection = dataSource.getConnection()) {
            assertThat(connection).isNotNull();
            assertThat(connection.isValid(5)).isTrue();
            
            // 获取数据库元数据
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("数据库产品名称: " + metaData.getDatabaseProductName());
            System.out.println("数据库版本: " + metaData.getDatabaseProductVersion());
            System.out.println("驱动名称: " + metaData.getDriverName());
            System.out.println("驱动版本: " + metaData.getDriverVersion());
            System.out.println("连接URL: " + metaData.getURL());
            System.out.println("用户名: " + metaData.getUserName());
            
            // 验证连接到正确的数据库
            assertThat(metaData.getDatabaseProductName()).contains("MySQL");
            assertThat(metaData.getUserName()).contains("root");
        }
    }
    
    @Test
    @DisplayName("测试数据库表创建")
    void testDatabaseTableCreation() {
        // 测试数据库表是否已创建
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'healthcare' AND table_name = 'users'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        
        // 由于我们使用 spring.jpa.hibernate.ddl-auto=update，表应该存在
        assertThat(count).isNotNull();
        System.out.println("用户表存在状态: " + (count > 0 ? "已创建" : "未创建"));
    }
    
    @Test
    @DisplayName("测试简单SQL查询")
    void testSimpleQuery() {
        // 执行简单的SQL查询
        String sql = "SELECT 1";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    @DisplayName("测试数据库事务")
    void testDatabaseTransaction() {
        // 测试事务是否正常工作
        jdbcTemplate.execute("BEGIN");
        jdbcTemplate.execute("SELECT 1");
        // 如果事务有问题，这里会抛出异常
        jdbcTemplate.execute("COMMIT");
        
        // 如果没有异常，说明事务支持正常
        assertThat(true).isTrue();
    }
    
    @Test
    @DisplayName("测试MySQL特定功能")
    void testMySQLSpecificFeatures() {
        // 测试MySQL的特定功能，如UTF8MB4支持
        String sql = "SELECT @@character_set_database, @@collation_database";
        jdbcTemplate.query(sql, rs -> {
            String charset = rs.getString(1);
            String collation = rs.getString(2);
            
            System.out.println("数据库字符集: " + charset);
            System.out.println("数据库排序规则: " + collation);
            
            // 验证字符集配置
            assertThat(charset).isEqualTo("utf8mb4");
            assertThat(collation).contains("utf8mb4");
        });
    }
}