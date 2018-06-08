package com.example.demo.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 *
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /*public CustomInvocationSecurityMetadataSourceService(SResourceService sres,SRoleService sR) {
        this.sResourceService = sres;
        this.sRoleService = sR;
        loadResourceDefine();
    }*/
    @PostConstruct
    private void loadResourceDefine() {
        //List<Map<String, Object>> list = sRoleVODao.findAll();
        List<String> query = new ArrayList<String>();
//        if (list != null && list.size() > 0) {
//            for (Map<String, Object> sr : list) {
//                //String name = sr.get("name")
//                Object value = sr.get("name");
//                String name = String.valueOf(value);
//                query.add(name);
//            }
//        }

        query.add("ROLE_ADMIN");

        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        for (String auth : query) {
            ConfigAttribute ca = new SecurityConfig(auth);
            //List<Map<String,Object>> query1 = sResourceVODao.findByRoleName(auth);
            List<String> query1 = new ArrayList<String>();
            //List<Map<String, Object>> list1 = sResourceVODao.findByRoleName(auth);
            query1.add("/test/**");
            for (String url : query1) {

                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(ca);
                    resourceMap.put(url, atts);
                }
            }
        }

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    // 根据URL，找到相关的权限配置。</span>
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        System.out.println("--------- getAttributes ------ begin ------");

        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {

        return true;
    }

}
