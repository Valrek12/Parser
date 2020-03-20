# Parcer
для запуска jar необходимо:

	а) java - 1.8 (open jdk или oracle, без разницы)
	б) maven - 3.6.0
  
перед запуском убедитсья, что настроены переменные окружения:

	а) JAVAHOME
	б) Maven Home
  
 1) Сделать клон репозитория командой: git clone https://github.com/Valrek12/Parcer.git
 2) перейти в папку с проектом и найти файл по пути /opencard/XmlParcer/src/main/resources/application.properties
	
		uri=https://ilgc-group.com/acrit.exportpro/xml_all_v2.xml - адресс с которого качаем xml
		sever=jdbc:mariadb://marsmart.ru:3306/opencart - адресс куда нужно стучатсья к бд
		login=***** - пользователь
		password=**** - пароль
		nameFile=yaml_catalog.xml - имя файла для xml
		driver=org.mariadb.jdbc.Driver - драйвер, на котором работаем, лучше не менять
		imagePath = /home/valerey/IdeaProjects/opencard/XmlParcer/src/main/resources - путь по которому будут лежать картинки

  3) После того как все настроили, возвращаемся в корень проекта там где лежит pom.xml и вводим команду:
	
	 	mvn package
			
  4) Вот что должно получится
	
	[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ XmlParcer ---
	[INFO] Building jar: /home/valerey/IdeaProjects/opencard/XmlParcer/target/XmlParcer-1.0-SNAPSHOT.jar
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time:  14.595 s
	[INFO] Finished at: 2020-02-24T16:51:57+03:00
	[INFO] ----

5) находим созданный jar - /opencard/XmlParcer/target/XmlParcer-1.0-SNAPSHOT.jar
копируем в рабочую область и запускаем командной:

	java -jar XmlParcer-1.0-SNAPSHOT.jar


