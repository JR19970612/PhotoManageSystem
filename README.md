 ###Authentication（实现类有：UsernamePasswordAuthenticationToken...）顶级接口主要变量的意义
- principal:       认证之前存放的是客户端提交密码的用户名，认证通过之后存放的是数据库内的用户详情信息
- credentials:    存放客户端提交密码，该数据从客户端获取
- authoritials:   经过认证后存放的权限信息
- details:    主要客户端主机信息（包括IP和sessionId）
- authenticated:   boolean类型表示该Authentication对象是否经过认证