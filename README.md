Представлены 5 классов. Логика находится в MyCallableStatementWrapper и MyDriverWrapper. 

Для подключения к бд через данный инструмент необходимо в jndi datasource указать 
driverClassName="ru.unisuite.jdbc.wrapper.MyDriverWrapper". 
Также для чего-то была изменена часть урла для подключения к бд, теперь, вместо url="jdbc:oracle:thin:..."
необходимо указать url="jdbc:dbj2ee:orawrapper:...". Затрудняюсь ответить, зачем так сделано (было скопировано из решения). Если такая особенность не нужна, достаточно в классе MyDriverWrapper закомментировать вторую часть условия в методе acceptURL и строчку String myUrl = url.replaceFirst(ACCEPTABLE_URL_PREFIX, "jdbc:oracle:thin:"); в методе connect. Также можно удалить переменную ACCEPTABLE_URL_PREFIX.
