Представлены 5 классов. Класс MyConnectionWrapper выдает обернутые версии Statement, CallableStatement и PreparedStatement. Вышеперечисленные классы в методах execute, executeUpdate и executeQuery, при возникновении ошибки ORA-04068, повторно исполняют выполнение метода.

Oracle jdbc библиотека должна быть в зависимостях проекта.

Для подключения к бд через данный инструмент необходимо в jndi datasource указать 
driverClassName="ru.unisuite.jdbc.wrapper.MyDriverWrapper". 

Также, необходимо изменить url для подключения к бд. Часть урла "jdbc:oracle:thin:" необходимо заменить на "jdbc:unisuite-wrapper:thin:". Это необходимо для того, чтобы при работе программы был выбран именно обернутый драйвер, а не ojdbc. 

Решение было взято с сайта http://dbj2ee.blogspot.com/2007/10/dealing-with-oracle-plsql-error-ora.html
