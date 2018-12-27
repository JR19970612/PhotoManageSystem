 ###Authentication（实现类有：UsernamePasswordAuthenticationToken...）顶级接口主要变量的意义
- principal:       认证之前存放的是客户端提交密码的用户名，认证通过之后存放的是数据库内的用户详情信息
- credentials:    存放客户端提交密码，该数据从客户端获取
- authoritials:   经过认证后存放的权限信息
- details:    主要客户端主机信息（包括IP和sessionId）
- authenticated:   boolean类型表示该Authentication对象是否经过认证
###跨域配置
POST请求接口进行跨域配置需要在两个地方进行设置
1. 首先在SpringBoot对允许进行跨域的接口进行配置，主要的方式有下面两种
- 在WebConfig全局配置，
registry.addMapping("/comment")//允许跨域的接口
                .allowedOrigins("*")//允许访问的站点
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")//支持跨域的请求方法
                .maxAge(3600);
- 在接口Controller上进行跨域授权配置  
@CrossOrigin(
        origins = "*",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = RequestMethod.POST,
        maxAge = 3600
@RestController
public class CommentController{}
)
2. 在SpringSecurity中对允许跨域的接口进行授权，不给予拦击认证 
.antMatchers(HttpMethod.POST,"/comment").permitAll()
.antMatchers(HttpMethod.OPTIONS,"/comment").permitAll() 
注意！！！因为POST请求进行跨域时，浏览器会预先发送options请求进行认证，
确认当前的站点域名是服务器授权的。所以为了防止被Cors拦截，故还需配置全局的授权
