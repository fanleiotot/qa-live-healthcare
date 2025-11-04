根据当前git status状态，阅读所有改动文件内容，使用中文生成详细的git commit message 包括一个标题和一行补充说明（如果有）。 保持message内容尽量简洁但是不要遗漏任何关键信息，只展示message给我，不要执行commit动作。

以下是一个示例
feat: 添加医生API代理和前端集成
- 添加axios依赖用于HTTP请求
- 配置Vite代理将/api请求转发到后端服务
- 更新Doctors.vue组件集成API调用