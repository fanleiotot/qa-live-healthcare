-- ASDM MySQL数据库初始化脚本
-- 创建于: 2025-12-09
-- 描述: 创建ASDM开发所需的基础数据库和用户

-- 确保使用UTF-8字符集
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建医生表
CREATE TABLE IF NOT EXISTS `doctor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '医生ID',
  `name` varchar(100) NOT NULL COMMENT '医生姓名',
  `title` varchar(50) DEFAULT NULL COMMENT '职称',
  `department` varchar(100) DEFAULT NULL COMMENT '科室',
  `hospital` varchar(200) DEFAULT NULL COMMENT '医院',
  `specialization` varchar(200) DEFAULT NULL COMMENT '专业领域',
  `license_number` varchar(50) DEFAULT NULL COMMENT '执业证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `years_of_experience` int DEFAULT NULL COMMENT '从业年限',
  `status` tinyint DEFAULT '1' COMMENT '状态: 1-正常, 0-禁用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_license_number` (`license_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医生信息表';

-- 插入示例医生数据
INSERT INTO `doctor` (`name`, `title`, `department`, `hospital`, `specialization`, `license_number`, `phone`, `email`, `years_of_experience`) VALUES
('张医生', '主任医师', '内科', '北京协和医院', '心血管疾病', 'BJ20231201001', '13800138001', 'zhang@hospital.com', 15),
('李医生', '副主任医师', '外科', '上海华山医院', '普通外科', 'SH20231201002', '13800138002', 'li@hospital.com', 12),
('王医生', '主治医师', '儿科', '广州儿童医院', '小儿呼吸', 'GZ20231201003', '13800138003', 'wang@hospital.com', 8),
('刘医生', '主任医师', '妇产科', '深圳市妇幼保健院', '高危妊娠', 'SZ20231201004', '13800138004', 'liu@hospital.com', 18),
('陈医生', '副主任医师', '骨科', '成都华西医院', '关节置换', 'CD20231201005', '13800138005', 'chen@hospital.com', 14);

-- 创建数据库索引
CREATE INDEX idx_doctor_name ON doctor(name);
CREATE INDEX idx_doctor_department ON doctor(department);
CREATE INDEX idx_doctor_hospital ON doctor(hospital);
CREATE INDEX idx_doctor_status ON doctor(status);

SET FOREIGN_KEY_CHECKS = 1;