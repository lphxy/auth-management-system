package com.wan.cms.service;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by w1992wishes on 2017/6/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext-ehcache.xml"
})
@Transactional(transactionManager = "transactionManager")
public class cacheTest {

    @Test
    public void index() {
        // 自定义接口调用
        // Create a cache manager
        final CacheManager cacheManager = CacheManager.getInstance();

        // create the cache called "hello-world"
        final Cache cache = cacheManager.getCache("ehcache");

        // create a key to map the data to
        final String key = "key";

        // Create a data element
        final Element element = new Element(key, "value");

        // Put the element into the data store
        cache.put(element);

        // Retrieve the data element
        final Element cacheElement = cache.get(key);

        // Print the value
        System.out.println(cacheElement.getObjectValue());
    }
}

