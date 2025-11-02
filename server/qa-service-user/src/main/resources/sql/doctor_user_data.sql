-- 清空现有数据并插入正确的中文数据
DELETE FROM doctor_user;

-- 插入医生数据
INSERT INTO doctor_user (id, username, password, name, title, department, avatar, experience, specialties, is_active) VALUES
('doc001', 'dr-zhang-wei', '123456', '张伟医生', '主任医师', '心内科', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop&crop=face', '从事心血管疾病诊疗20年', '["冠心病", "高血压", "心律失常"]', true),
('doc002', 'dr-li-na', '123456', '李娜医生', '副主任医师', '儿科', 'https://images.unsplash.com/photo-1594824475317-29bb4b5c3e4d?w=200&h=200&fit=crop&crop=face', '儿科临床经验15年', '["小儿感冒", "儿童发育", "疫苗接种"]', true),
('doc003', 'dr-wang-qiang', '123456', '王强医生', '主治医师', '骨科', 'https://images.unsplash.com/photo-1582750433449-648ed127bb54?w=200&h=200&fit=crop&crop=face', '骨科手术专家，临床经验12年', '["骨折治疗", "关节置换", "脊柱疾病"]', true),
('doc004', 'dr-liu-min', '123456', '刘敏医生', '主任医师', '妇产科', 'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=200&h=200&fit=crop&crop=face', '妇产科临床经验18年', '["产前检查", "妇科炎症", "不孕不育"]', false),
('doc005', 'dr-chen-jie', '123456', '陈杰医生', '副主任医师', '神经内科', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop&crop=face', '神经系统疾病专家，临床经验16年', '["脑卒中", "癫痫", "帕金森病"]', true);