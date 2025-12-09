# Epic PRD: E001_mysql-doctor-data-migration

**Date**: 2025-12-09  
**Author**: AI Assistant  
**Status**: Draft

## 1. Epic Overview

### 1.1 Epic Description
将医生数据从JSON文件迁移到MySQL数据库，使用Docker Compose构建开发用的数据库服务器，当前只处理医生数据，对其他数据（患者和问题）不做处理。

### 1.2 Business Context
当前医疗问诊平台使用JSON文件和内存存储作为数据源，这种方案在开发阶段提供了便利，但存在以下限制：
1. 数据无法持久化，页面刷新后丢失
2. 不支持多用户并发访问
3. 数据操作效率低，不适合生产环境
4. 无法实现复杂查询和数据分析

通过将医生数据迁移到MySQL数据库，我们可以：
1. 实现数据持久化存储
2. 支持多用户并发访问
3. 提高数据操作效率和可靠性
4. 为后续功能扩展奠定基础

### 1.3 Success Definition
成功标准：
1. Docker Compose能够成功启动MySQL开发环境
2. 医生数据能够从JSON文件正确迁移到MySQL数据库
3. 后端服务能够成功连接MySQL数据库并操作医生数据
4. 前端应用能够通过后端API访问医生数据
5. 数据迁移过程不影响现有功能的正常使用

## 2. Business Objectives

### 2.1 Primary Objectives
1. 实现医生数据的持久化存储
2. 建立开发环境的数据库基础设施
3. 为后续患者和问题数据迁移奠定基础
4. 提高系统的可扩展性和可靠性

### 2.2 Key Results
| Key Result | Target | Measurement Method |
|------------|--------|-------------------|
| 数据库环境搭建 | Docker Compose成功启动MySQL | docker-compose up命令执行成功 |
| 数据迁移完整性 | 100%医生数据迁移成功 | 迁移脚本执行无错误，数据条数一致 |
| API功能可用 | 后端API能正确操作医生数据 | 所有医生相关API测试通过 |
| 前端功能正常 | 前端能正常显示医生数据 | 医生列表页面正常显示，医生登录功能正常 |

## 3. User Stories

### 3.1 Primary User Personas
1. **开发人员**: 需要稳定的数据库环境进行开发工作
2. **系统管理员**: 需要管理数据库环境和数据迁移
3. **最终用户**: 需要可靠的医生信息访问

### 3.2 User Stories
| ID | User Story | Priority | Acceptance Criteria |
|----|------------|----------|-------------------|
| US-001 | 作为开发人员，我希望使用Docker Compose快速启动MySQL开发环境，以便进行数据库相关开发 | High | 1. 执行docker-compose up命令能成功启动MySQL<br>2. 数据库可以正常连接<br>3. 数据库初始化脚本自动执行 |
| US-002 | 作为开发人员，我希望将JSON中的医生数据迁移到MySQL数据库，以便实现数据持久化 | High | 1. 迁移脚本能正确解析JSON文件<br>2. 所有医生数据成功迁移到数据库<br>3. 数据格式和结构与原有JSON保持一致 |
| US-003 | 作为开发人员，我希望后端服务能从MySQL读取和写入医生数据，以便替换原有的内存存储 | High | 1. 后端能成功连接MySQL数据库<br>2. 所有医生相关API正常工作<br>3. 数据操作性能满足要求 |
| US-004 | 作为开发人员，我希望前端应用能正常显示和操作医生数据，以便用户可以正常使用系统 | Medium | 1. 前端能正常显示医生列表<br>2. 医生登录功能正常工作<br>3. 医生状态更新功能正常 |

## 4. Scope

### 4.1 In Scope
* 列出此epic包含的内容：
- Docker Compose MySQL开发环境配置
- MySQL数据库表结构设计（医生表）
- JSON到MySQL的数据迁移脚本
- 后端Spring Boot服务数据库集成
- 后端医生相关API实现
- 前端与后端API的集成

### 4.2 Out of Scope
* 明确排除在此epic之外的内容：
- 患者数据的数据库迁移（将在后续epic处理）
- 问题数据的数据库迁移（将在后续epic处理）
- 生产环境数据库配置（仅开发环境）
- 数据库性能优化
- 数据备份和恢复策略
- 高可用数据库配置

## 5. Technical Considerations

### 5.1 Architecture Impact
此次Epic将引入以下架构变化：
1. **数据层变化**: 从JSON文件+内存存储迁移到MySQL数据库
2. **后端服务变化**: Spring Boot服务需要集成MySQL数据库
3. **API层变化**: 后端API需要实现完整的CRUD操作
4. **前端集成变化**: 前端需要从内存存储调用转向API调用

### 5.2 Technical Dependencies
* 列出技术依赖：
- Docker和Docker Compose（容器化环境）
- MySQL 8.0+（数据库）
- Spring Data JPA（数据访问层）
- H2 Database（测试环境）
- MyBatis或JPA（ORM框架）

### 5.3 Performance Requirements
* 指定性能要求：
- 数据库连接响应时间: < 100ms
- 医生列表查询响应时间: < 200ms
- 医生登录验证响应时间: < 300ms
- 支持并发用户数: 50+

## 6. Non-Functional Requirements

### 6.1 Security Requirements
1. 数据库访问需要使用环境变量配置凭据，不应硬编码在代码中
2. 敏感字段（如密码）需要加密存储
3. 数据库连接应使用SSL/TLS加密（生产环境）

### 6.2 Compliance Requirements
1. 医疗数据需要符合数据保护法规
2. 医生个人信息需要妥善保护
3. 操作日志需要记录关键数据变更

### 6.3 Accessibility Requirements
1. 数据库管理界面应提供清晰的错误信息
2. 数据迁移过程应有进度显示和日志记录

## 7. Dependencies

### 7.1 Internal Dependencies
| Dependency | Team/Owner | Status | Impact |
|------------|------------|--------|--------|
| 前端Vue应用 | 前端团队 | 开发中 | 中 |
| 后端Spring Boot用户服务 | 后端团队 | 开发中 | 高 |
| 现有JSON数据文件 | 数据团队 | 已完成 | 高 |

### 7.2 External Dependencies
| Dependency | Vendor/Provider | Status | Impact |
|------------|-----------------|--------|--------|
| MySQL数据库 | Oracle | 确认 | 高 |
| Docker环境 | Docker Inc. | 确认 | 高 |
| Spring Data JPA | Pivotal | 确认 | 中 |

## 8. Risks and Mitigations

### 8.1 Identified Risks
| Risk | Probability | Impact | Mitigation Strategy |
|------|-------------|--------|-------------------|
| 数据迁移过程中数据丢失 | 中 | 高 | 1. 执行前备份JSON文件<br>2. 使用事务确保迁移原子性<br>3. 迁移后验证数据完整性 |
| 数据库性能不满足要求 | 中 | 中 | 1. 设计合理的索引<br>2. 实现连接池管理<br>3. 进行性能测试 |
| 开发环境Docker配置复杂 | 低 | 中 | 1. 提供详细的文档说明<br>2. 创建自动化脚本<br>3. 预配置Docker Compose文件 |
| 前后端API集成问题 | 中 | 中 | 1. 定义清晰的API规范<br>2. 实现API测试用例<br>3. 提供Mock数据用于前端开发 |

## 9. Success Metrics

### 9.1 Business Metrics
- 数据可用性: 100%
- 开发效率提升: 30%（相比JSON文件操作）
- 系统稳定性: 99.9%

### 9.2 User Metrics
- 开发人员满意度: 目标4.5/5
- 数据操作错误率: < 1%
- API响应时间满足率: 95%

### 9.3 Technical Metrics
- 数据库连接成功率: 99.9%
- 数据迁移成功率: 100%
- API测试覆盖率: 90%

## 10. Timeline and Milestones

### 10.1 High-Level Timeline
| Phase | Start Date | End Date | Deliverables |
|-------|------------|----------|--------------|
| 环境准备 | 2025-12-09 | 2025-12-11 | Docker Compose配置，MySQL环境搭建 |
| 数据库设计 | 2025-12-11 | 2025-12-12 | 医生表结构设计，索引设计 |
| 数据迁移 | 2025-12-12 | 2025-12-14 | 迁移脚本，数据验证 |
| 后端集成 | 2025-12-14 | 2025-12-17 | Spring Boot数据库集成，API实现 |
| 前端集成 | 2025-12-17 | 2025-12-19 | 前端API调用，功能测试 |
| 测试验证 | 2025-12-19 | 2025-12-21 | 端到端测试，性能测试 |

### 10.2 Key Milestones
1. 2025-12-11: Docker Compose环境验证完成
2. 2025-12-14: 医生数据迁移完成
3. 2025-12-17: 后端API实现完成
4. 2025-12-19: 前端功能验证完成
5. 2025-12-21: 全功能测试通过

## 11. Appendix

### 11.1 References
- [ASDM数据模型文档](../../contexts/asdm.data-models.md)
- [ASDM架构文档](../../contexts/asdm.architecture.md)
- [ASDM部署文档](../../contexts/asdm.deployment.md)
- [医生数据JSON文件](../../../web/qa-web/src/data/doctor-user-list.json)

### 11.2 Glossary
| Term | Definition |
|------|------------|
| Docker Compose | 用于定义和运行多容器Docker应用程序的工具 |
| JPA | Java持久化API，用于将Java对象映射到关系数据库 |
| ORM | 对象关系映射，用于在面向对象编程语言和关系数据库之间转换数据 |
| CRUD | 创建(Create)、读取(Read)、更新(Update)、删除(Delete)操作的缩写 |

### 11.3 Revision History
| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2025-12-09 | AI Assistant | 初始草稿 |