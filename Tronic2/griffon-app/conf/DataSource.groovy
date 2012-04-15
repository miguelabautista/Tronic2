dataSource {
    driverClassName = 'com.mysql.jdbc.Driver'
    username = 'root'
    password = 'filan3001'
    tokenizeddl = true // set this to true if using MySQL or any other
    // RDBMS that requires execution of DDL statements
    // on separate calls
    pool {
        maxWait = 60000
        maxIdle = 5
        maxActive = 8
    }
}
environments {
    development {
        dataSource {
            dbCreate = 'skip' // one of ['create', 'skip']
            url = 'jdbc:mysql://localhost:3306/tiendatronic'
        }
    }
    test {
        dataSource {
            dbCreate = 'skip'
            url = 'jdbc:mysql://localhost:3306/tiendatronic'
        }
    }
    production {
        dataSource {
            dbCreate = 'skip'
            url = 'jdbc:mysql://localhost:3306/tiendatronic'
        }
    }
}
