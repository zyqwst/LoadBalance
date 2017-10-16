## LoadBalance(nginx+springboot+redis 实现的负载均衡Demo)

## 说明
   
1. 在application.properties 添加redis相关配置
	
   ```
    #spring.redis.database
	spring.redis.hostname=127.0.0.1
	spring.redis.port=6379  
	spring.redis.password=admin
	spring.redis.pool.maxActive=8  
	spring.redis.pool.maxWait=-1  
	spring.redis.pool.maxIdle=8  
	spring.redis.pool.minIdle=0  
	spring.redis.timeout=0
   ```
2. 在application.properties配置文件中修改两个不同端口并用 `mvn package spring-boot:repackage` 命令生成两个jar文件.然后java -jar 命令分别运行Demo

3. 假设我们这里分配的端口是9001和9002，然后在nginx.conf配置反向代理
   Mac下nginx配置如下
   
   ```
   http {
    ...
    
    upstream dis{
        server 127.0.0.1:9001 weight=1; //weight表示请求分配的权重
        server 127.0.0.1:9002 weight=1;
    }
   
    server {
       listen  80;
        server_name  localhost;
        location / {
            proxy_pass http://dis;
        }
    }
}
   
   ```

4. 效果预览