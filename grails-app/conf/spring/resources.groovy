import org.springframework.cache.ehcache.EhCacheFactoryBean

// Place your Spring DSL code here
beans = {
 /** statsCache(EhCacheFactoryBean) { bean ->
    cacheManager = ref("springcacheCacheManager")
    cacheName = "topTenCache"
    // these are just examples of properties you could set
    eternal = false
    diskPersistent = false
    timeToLive = 15
    memoryStoreEvictionPolicy = "LRU"
  } **/
}
