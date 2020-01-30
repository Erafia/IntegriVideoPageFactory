1. Обновить версии всех библиотек в проекте
mvn versions:use-latest-versions
2. Запустить тесты используя mvn clean test команду
mvn clean test
3. Использовать параметры для запуска конкретных тестов и методов
mvn -Dtest=ProjectTest test
mvn -Dtest=ProjectTest#editProject test
mvn -Dtest=ProjectTest#createProject+editProject test
mvn -Dtest=!Sign*,Projec**est#editProject test
4. Создать альтернативный pom.xml и запустить из него mvn билд
mvn -f alternative_pom.xml
5. Пробросить параметр из mvn command line внутрь теста
mvn test -Dprobroshennyi=1234
