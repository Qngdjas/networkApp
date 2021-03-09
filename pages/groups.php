<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <link rel="icon" type="image/png" href="../pics/book.png" />
        <title>Журнал преподавателя</title>
    </head>
    <body>
        <header>
            <!-- <div style="text-align: left">
                <?php
                //$name = $_GET["firstname"];
                //$surname = $_GET["lastname"];
                //$fathername = $_GET["fathername"];
                echo "<h3>Преподаватель</h3> <b>" . /* $surname */"Пупкин" . " " . /* $name */"Василий" . " " . /* $fathername */"Петрович" . "</b>";
                ?>
            </div> -->
            <div style="text-align: left">
                <h1 th:text="'Имя: ' + ${firstname}"></h1>
                <h1 th:text="'Фамилия: ' + ${lastname}"></h1>
                <h1 th:text="'Отчество: ' + ${fathername}"></h1>
            </div>
        </header>
        <main>
            <h3>Список групп:</h3>
            <form action = "../pages/group-info.php" method="get">
                <ul th:each="group :${groups}" type="disc">
                    <li><a th:text="${group}" th:href="'group?group=' + ${group}"></a></li>
                </ul>
            </form>
            <!--<form action="../pages/group-info.php" method="get">
                <button name="menu" value="header-menu">Группа 1</button>
            </form>
            <a href="../pages/group-info.php" onclick="document.getElementById('my_form').submit(); return false;">Test</a>-->
        </main>
    </body>
</html>