RetryDriver является JDBC Driver-обёрткой над Oracle JDBC Driver со следующим дополнительным поведением:  
при возникновении ошибки "ORA-04068: existing state of packages has been discarded" ("существующее состояние было сброшено")
основные методы (execute, executeQuery, executeUpdate и некоторые другие) выполняются ещё 1 раз.

Oracle JDBC библиотека должна быть в зависимостях проекта.

Для использование RetryDriver при создании Data Source необходимо: 
- в Driver Class Name указать `ru.unisuite.jdbc.wrapper.RetryDriver`
- в JDBC URL часть значения `jdbc:oracle:thin:` заменить на `jdbc:unisuite-retry:thin:`

Это необходимо для того, чтобы в runtime при выборе реализации JDBC был выбран RetryDriver, а не Oracle Driver. 

Решение основано на статье [Dealing with Oracle PL/SQL Error "ORA-04068: existing state of packages has been discarded" Transparently in Java/JDBC](http://dbj2ee.blogspot.com/2007/10/dealing-with-oracle-plsql-error-ora.html).