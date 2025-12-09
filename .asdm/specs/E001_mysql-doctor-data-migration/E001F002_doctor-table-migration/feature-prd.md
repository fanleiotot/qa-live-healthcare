# Feature PRD: E001F002_doctor-table-migration

**Date**: 2025-12-09  
**Author**: AI Assistant  
**Status**: Draft  
**Parent Epic**: [E001_mysql-doctor-data-migration](./../epic-prd.md)

## 1. Feature Overview

### 1.1 Feature Description
设计并创建MySQL医生数据表结构，实现从JSON文件到MySQL数据库的医生数据迁移，包括表结构设计、数据迁移脚本和数据验证机制。

### 1.2 Business Value
此功能实现医生数据的持久化存储，为系统提供可靠的数据基础，支持复杂查询和数据分析，提高系统可扩展性，并为后续功能（如患者和问题数据迁移）奠定基础。

### 1.3 User Value
为开发人员提供结构化的医生数据存储方案，支持高效的医生信息管理和查询，提高数据一致性和完整性，减少数据管理错误。

## 2. Relationship to Parent Epic

### 2.1 Epic Objectives Supported
- **实现医生数据的持久化存储**: 设计并实现医生数据表结构，将JSON数据迁移到MySQL
- **建立开发环境的数据库基础设施**: 创建生产级别的数据模型和索引
- **为后续患者和问题数据迁移奠定基础**: 设计可扩展的表结构，便于关联其他数据

### 2.2 Dependencies on Other Features
- **E001F001_mysql-database-setup**: 依赖此功能提供的MySQL数据库环境
- **E001F003_backend-doctor-api-integration**: 此功能完成后，后端API才能基于数据库实现

## 3. User Stories

### 3.1 Target User Personas
- **数据库开发人员**: 负责设计表结构和编写迁移脚本
- **后端开发人员**: 需要基于表结构实现数据访问层
- **系统管理员**: 需要管理医生数据和执行数据迁移

### 3.2 Detailed User Stories
| ID | User Story | Priority | Acceptance Criteria |
|----|------------|----------|-------------------|
| F-US-001 | 作为数据库开发人员，我希望设计符合第三范式的医生表结构，以便确保数据一致性和减少冗余 | High | 1. 表结构符合第三范式要求<br>2. 字段类型和长度合理<br>3. 主键和外键设计正确<br>4. 索引设计优化查询性能 |
| F-US-002 | 作为数据库开发人员，我希望创建数据迁移脚本，以便将JSON文件中的医生数据导入MySQL数据库 | High | 1. 迁移脚本正确解析JSON文件<br>2. 所有医生数据成功导入<br>3. 数据类型转换准确<br>4. 特殊字符处理正确 |
| F-US-003 | 作为后端开发人员，我希望表结构支持高效的医生查询操作，以便实现各种业务功能 | High | 1. 常用查询字段有索引<br>2. 支持按科室和专长查询<br>3. 支持按活跃状态筛选<br>4. 查询性能满足要求 |
| F-US-004 | 作为系统管理员，我希望有数据验证机制，以便确保迁移后的数据完整性和准确性 | High | 1. 提供数据完整性验证脚本<br>2. 记录迁移前后的数据统计<br>3. 提供数据对比报告<br>4. 异常数据有处理机制 |

## 4. Functional Requirements

### 4.1 Core Functionality
- **表结构设计**: 设计符合规范的医生表结构，支持所有现有字段和未来扩展
- **数据迁移脚本**: 实现JSON到MySQL的数据迁移，处理数据类型转换
- **索引设计**: 为常用查询字段创建合适索引，优化查询性能
- **数据验证**: 提供数据完整性验证和对比机制
- **回滚机制**: 支持迁移失败时的数据回滚操作

### 4.2 User Interactions
- **命令行操作**: 通过命令执行迁移和验证脚本
- **日志查看**: 查看迁移过程和结果的详细日志
- **数据报告**: 生成迁移前后的数据对比报告

### 4.3 Data Requirements
- **数据完整性**: 确保迁移前后数据一致性和完整性
- **数据类型**: 正确处理JSON数据到MySQL数据类型的转换
- **特殊字符**: 正确处理特殊字符和多字节字符
- **约束条件**: 实现必要的数据约束和验证规则

## 5. Non-Functional Requirements

### 5.1 Performance Requirements
| Requirement | Target | Measurement |
|-------------|--------|-------------|
| 数据迁移速度 | > 1000条/秒 | 批量插入操作 |
| 查询响应时间 | < 100ms | 基于索引的查询 |
| 表空间占用 | 优化存储空间 | 合理的字段类型选择 |

### 5.2 Security Requirements
- **数据加密**: 敏感字段（如密码）需要加密存储
- **访问控制**: 数据库用户仅拥有必要权限
- **审计日志**: 记录数据迁移操作和结果
- **数据备份**: 迁移前自动备份原有数据

### 5.3 Data Integrity Requirements
- **主键唯一性**: 确保医生ID全局唯一
- **引用完整性**: 如有外键引用，确保引用完整性
- **数据一致性**: 迁移前后数据保持一致
- **事务支持**: 迁移过程支持事务操作

## 6. UI/UX Specifications

### 6.1 User Interface Requirements
- **命令行界面**: 提供清晰的命令行操作反馈
- **进度显示**: 迁移过程中显示进度信息
- **错误报告**: 详细的错误信息和解决建议

### 6.2 User Experience Requirements
- **操作简单**: 迁移过程尽可能自动化
- **反馈及时**: 提供实时的操作反馈
- **恢复便捷**: 出错时能快速恢复

### 6.3 Design Assets
- **SQL脚本**: 表创建、索引创建、迁移脚本
- **配置文件**: 数据库连接和迁移配置
- **文档**: 表结构说明和迁移指南

## 7. Technical Specifications

### 7.1 Architecture Impact
- **数据层架构**: 从JSON文件存储转向MySQL关系型数据库
- **数据模型**: 建立标准化的医生数据模型
- **数据访问**: 为后续API实现提供数据基础

### 7.2 API Specifications
不适用，此功能主要涉及数据库设计和迁移。

### 7.3 Database Schema
医生表结构设计：
```sql
-- 医生表结构
CREATE TABLE doctors (
    id VARCHAR(20) PRIMARY KEY COMMENT '医生ID，如doc001',
    username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名，用于登录',
    password_hash VARCHAR(255) NOT NULL COMMENT '密码哈希值',
    name VARCHAR(100) NOT NULL COMMENT '医生姓名',
    title VARCHAR(50) COMMENT '职称，如主任医师',
    department VARCHAR(100) COMMENT '科室',
    avatar_url TEXT COMMENT '头像URL',
    experience TEXT COMMENT '临床经验描述',
    specialties JSON COMMENT '专长领域数组',
    is_active BOOLEAN DEFAULT TRUE COMMENT '是否活跃/在线',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    last_login_at TIMESTAMP NULL COMMENT '最后登录时间',
    
    INDEX idx_doctors_username (username),
    INDEX idx_doctors_department (department),
    INDEX idx_doctors_is_active (is_active),
    INDEX idx_doctors_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医生信息表';
```

专长表结构（可选的规范化设计）：
```sql
-- 专长表（可选，如果需要更灵活的专长管理）
CREATE TABLE specialties (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL COMMENT '专长名称',
    description TEXT COMMENT '专长描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_specialties_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医疗专长表';

-- 医生专长关联表
CREATE TABLE doctor_specialties (
    doctor_id VARCHAR(20),
    specialty_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (doctor_id, specialty_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES specialties(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医生专长关联表';
```

## 8. Testing Requirements

### 8.1 Test Scenarios
- **表结构测试**: 验证表结构符合设计要求
- **数据迁移测试**: 验证JSON到MySQL的数据迁移
- **数据验证测试**: 验证迁移前后数据一致性
- **性能测试**: 验证查询性能满足要求

### 8.2 Acceptance Test Criteria
- **功能测试**: 所有迁移功能正常工作
- **数据完整性测试**: 迁移后数据完整准确
- **性能测试**: 查询响应时间满足要求
- **错误处理测试**: 异常情况处理正确

### 8.3 Quality Gates
- **代码审查**: SQL脚本通过代码审查
- **数据验证**: 数据完整性验证通过
- **性能基准**: 查询性能达到基准要求
- **文档完整**: 表结构文档完整准确

## 9. Deployment Requirements

### 9.1 Deployment Strategy
- **分阶段部署**: 先在开发环境验证，再推广到其他环境
- **数据备份**: 部署前自动备份相关数据
- **版本控制**: 所有SQL脚本纳入版本控制
- **回滚计划**: 提供完整的回滚方案

### 9.2 Rollback Plan
- **表结构回滚**: 提供删除表结构的脚本
- **数据回滚**: 迁移失败时恢复原始数据
- **配置回退**: 回退数据库配置更改

## 10. Success Metrics

### 10.1 Feature-specific Metrics
| Metric | Target | Measurement Frequency |
|--------|--------|---------------------|
| 数据迁移成功率 | 100% | 每次迁移执行 |
| 数据完整性 | 100% | 迁移后验证 |
| 查询性能提升 | > 50% | 相比JSON查询 |
| 存储空间优化 | 合理范围 | 迁移后评估 |

### 10.2 Business Impact Metrics
- **开发效率**: 后端开发效率提升30%
- **查询能力**: 支持复杂查询和数据分析
- **系统稳定性**: 数据持久化提高系统可靠性
- **扩展性**: 为未来功能扩展奠定基础

## 11. Risks and Mitigations

### 11.1 Technical Risks
| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| 数据类型转换错误 | 中 | 高 | 充分测试，处理边界情况 |
| 字符编码问题 | 中 | 中 | 使用UTF-8mb4，测试特殊字符 |
| 性能不达标 | 中 | 中 | 优化索引设计，进行性能测试 |
| 数据丢失 | 低 | 高 | 实现完整备份和恢复机制 |

### 11.2 Timeline Risks
| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| 表结构设计复杂 | 中 | 中 | 简化设计，预留扩展空间 |
| 数据迁移问题 | 中 | 高 | 充分测试，准备手动迁移方案 |

## 12. Timeline

### 12.1 Development Timeline
| Phase | Duration | Start Date | End Date | Deliverables |
|-------|----------|------------|----------|--------------|
| 表结构设计 | 1天 | 2025-12-10 | 2025-12-10 | 表结构设计文档 |
| SQL脚本开发 | 1天 | 2025-12-10 | 2025-12-11 | 创建表和索引脚本 |
| 迁移脚本开发 | 2天 | 2025-12-11 | 2025-12-13 | JSON到MySQL迁移脚本 |
| 测试和验证 | 2天 | 2025-12-13 | 2025-12-15 | 测试报告和验证结果 |

### 12.2 Dependencies Timeline
- **E001F001_mysql-database-setup**: 需要先完成MySQL环境搭建
- **E001F003_backend-doctor-api-integration**: 此功能完成后才能开始后端API集成

## 13. Appendix

### 13.1 References
- [Parent Epic PRD: E001_mysql-doctor-data-migration](./../epic-prd.md)
- [ASDM数据模型文档](../../../contexts/asdm.data-models.md)
- [MySQL 8.0参考手册](https://dev.mysql.com/doc/refman/8.0/en/)
- [医生数据JSON文件](../../../web/qa-web/src/data/doctor-user-list.json)

### 13.2 Glossary
| Term | Definition |
|------|------------|
| 第三范式 | 数据库设计规范，消除传递依赖 |
| 索引 | 数据库对象，提高查询性能 |
| 迁移脚本 | 将数据从一种格式转换到另一种格式的脚本 |
| 数据完整性 | 确保数据准确性和一致性的机制 |

### 13.3 Revision History
| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2025-12-09 | AI Assistant | 初始草稿 |