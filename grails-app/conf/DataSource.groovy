dataSource {
  pooled = true
  driverClassName = "org.hsqldb.jdbcDriver"
  username = "sa"
  password = ""
}
hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = true
  cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
  development {
    dataSource {
      dbCreate = "update" // one of 'create', 'create-drop','update'
      url = "jdbc:hsqldb:file:devDB;shutdown=true"
    }
  }
  test {
    dataSource {
      dbCreate = "create"
      url = "jdbc:hsqldb:mem:testDb"
    }
  }
  production {
    dataSource {
      driverClassName = "org.postgresql.Driver"
      username = "mudu_user"
      password = "dp4ne2y3"
      dbCreate = "update" // one of 'create', 'create-drop','update'
      url = "jdbc:postgresql://localhost/mudu"
    }
  }
}
