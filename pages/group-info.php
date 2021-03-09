<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" type="image/png" href="../pics/book.png" />
        <style type="text/css">
            td{font-family: Arial;
               font-size: 14px;
               border-collapse: collapse;
               text-align: center;
               margin: 20px;
               border-style: solid;
               border-width: 1px 1px 1px 1px;
               border-color: black;}
            </style>
            <title>Журнал преподавателя</title>
        </head>
        <body>
            <header>
                <?php
                //$group = $_GET["group"];
                //echo "<h2>Группа:</h2> <b th:text="${group}"></b>";
                ?>
                <div style="text-align: left">
                <h1 th:text="'Группа: ' + ${group}"></h1>
            </div>
        </header>
        <main>
            <h3>Занятие:</h3>
            <form name="journal" method="get">
                <table>
                    <tr>
                        <th>Дата:</th>
                        <th>Дисциплина:</th>
                    </tr>
                    <tr>
                        <th><input type="date" name="date"></th>
                        <th><select th:each="discipline :${disciplines}" name="disciplines">
                                <option th:text="${discipline}"></option>
                            </select></th>
                    </tr>
                    <tr><th><input type="hidden"></th></tr>
                    <tr>
                        <th>Тема:</th>
                        <th>Тип занятия:</th>
                    </tr>
                    <tr>
                        <th><input type="text" name="topic"></th>
                        <th><!--<td>
                        <div style="text-align: center">
                        <select onchange="window.location.href = this.options[this.selectedIndex].value">
                        <option VALUE="http://ru.stackoverflow.com/">Ссылка №1</option>
                        <option VALUE="http://ru.stackoverflow.com/">Ссылка №2</option>
                        <option VALUE="http://ru.stackoverflow.com/">Ссылка №3</option>
                        </select>
                        </div>
                        </td>-->
                            <select name="lessontype">
                                <option value="lecture">лекция</option>
                                <option value="seminar">семинар</option>
                                <option value="lab">лабораторная</option>
                                <option value="consult">консультация</option>
                                <option value="exam">экзамен</option>
                            </select></th>
                    </tr>
                </table>
                <table>
                    <tr>
                        <th>Студент</th>
                        <th>Оценка</th>
                    </tr>
                    <div th:each="student :${students}">
                        <tr>
                            <td ><div th:text="${student}" style="text-align: center"></div></td>
                            <td>
                                <div style="text-align: center">
                                    <select name="mark">
                                        <option value="none">н/я</option>
                                        <option value="attend">+</option>
                                        <option value="five">отл</option>
                                        <option value="four">хор</option>
                                        <option value="three">удовл</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </div>
                </table>
                <p><input type="submit" value="Сохранить">
                    <input type="reset" value="Очистить"></p>
            </form>          
        </main>
    </body>
</html>