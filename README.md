Представлены 5 классов. Логика находится в MyCallableStatementWrapper и MyDriverWrapper, остальные просто делегируют методы.

Для подключения к бд через данный инструмент необходимо в jndi datasource указать 
driverClassName="ru.unisuite.jdbc.wrapper.MyDriverWrapper". 

Существует возможность изменения url'а для подключения к бд. В классе MyDriverWrapper создается переменная ACCEPTABLE_URL_PREFIX, указывающая префикс, который заменяет "jdbc:oracle:thin:":

public static final String ACCEPTABLE_URL_PREFIX = "jdbc:dbj2ee:orawrapper:";

В этом же классе необходимо заменить введенный url на необходимый, для подключения к oracle бд:

String myUrl = url.replaceFirst(ACCEPTABLE_URL_PREFIX, "jdbc:oracle:thin:");

Также, необходимо изменить условие в методе acceptURL следующим образом:

	public boolean acceptsURL(String url) throws SQLException {

		return url != null && url.startsWith(ACCEPTABLE_URL_PREFIX);
	
	}

Сейчас данная возможность закомментирована.

Решение было взято с сайта http://dbj2ee.blogspot.com/2007/10/dealing-with-oracle-plsql-error-ora.html
