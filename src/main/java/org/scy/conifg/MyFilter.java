package org.scy.conifg;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * 如果你想要自定义过滤器并将其应用到 Shiro 的过滤器链中，可以按照以下步骤进行操作：
 *
 * 1创建自定义过滤器类：创建一个自定义的过滤器类，实现 javax.servlet.Filter
 * 接口或者继承 Shiro 提供的过滤器类（例如 org.apache.shiro.web.filter.AccessControlFilter）。
 * 在自定义过滤器中，你可以编写自己的过滤逻辑和处理方法。
 *
 * 2配置 Shiro 过滤器链：在 Shiro 配置类中，配置 ShiroFilterFactoryBean 的过滤器链。
 * 使用 filterFactoryBean.getFilters() 方法获取过滤器链中的过滤器实例的映射（Map），
 * 然后将自定义过滤器实例添加到该映射中。
 *
 * 3设置自定义过滤器规则：使用 filterChainDefinitionMap 定义过滤器链中的过滤器规则时，
 * 使用自定义过滤器的名称来指定相应的过滤器。**/
public class MyFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        /***配置的过滤*/
        // 自定义未授权时的处理逻辑，例如重定向到指定页面、返回错误信息等
        // 返回 true 表示继续处理，返回 false 表示中断处理
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        /***配置的过滤*/
        // 自定义未授权时的处理逻辑，例如重定向到指定页面、返回错误信息等
        // 返回 true 表示继续处理，返回 false 表示中断处理
        return false;
    }
}
