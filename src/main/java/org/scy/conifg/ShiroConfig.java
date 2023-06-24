package org.scy.conifg;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm(){
        return  new MyRealms();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

/**创建过滤连放在spring容器中
 * public class ShiroFilterFactoryBean implements FactoryBean, BeanPostProcessor {
 *     private static final transient Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);
 *     private SecurityManager securityManager;
 *     private Map<String, Filter> filters = new LinkedHashMap();
 *     private List<String> globalFilters = new ArrayList();
 *     private Map<String, String> filterChainDefinitionMap;
 *     private String loginUrl;
 *     private String successUrl;
 *     private String unauthorizedUrl;
 *     private AbstractShiroFilter instance;
 * 套了好几层 这个类
 * **/
//套了好几层 这个类
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager SecurityManager ){
        ShiroFilterFactoryBean shiroFilterFactoryChain = new ShiroFilterFactoryBean();
        shiroFilterFactoryChain.setSecurityManager(SecurityManager);
        // 配置过滤器链，指定需要进行认证和授权的 URL 路径、登录页面、未授权页面等
        shiroFilterFactoryChain.setLoginUrl("/login");//没有成功等陆的时候调转的URL
        shiroFilterFactoryChain.setUnauthorizedUrl("/unauthorized");//没有权限的的请求去往的url
        // 具体配置根据实际需求进行调整
        Map<String, Filter> filtersmap = shiroFilterFactoryChain.getFilters();
        MyFilter myFilter = new MyFilter();//自己创建的一个过滤连
        filtersmap.put("myfiltr",myFilter);//加入到shiro过滤中
        // 配置过滤器链，指定需要进行认证和授权的 URL 路径和相应的过滤器规则
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/public/**","anon");
        linkedHashMap.put("/admin/**","myfiltr");
        //map.put("key","map<key,value>")
        //设置过滤连执行我们的过滤
        shiroFilterFactoryChain.setFilterChainDefinitionMap(linkedHashMap);
        return shiroFilterFactoryChain;
    }



}
