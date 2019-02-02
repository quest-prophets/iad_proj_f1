<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/SponsorProfileStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Профиль спонсора</title>
</head>
<body>
<div class="grid-container">
    <!-- TODO: try to make ui component for header zone-->
    <div class="Header">
        <div class="Header-LogoContainer">
            <img src="/pictures/Formula_1_logo.jpg">
        </div>
        <div class="Header-UserInfo-container">
            Monster Energy
            <form action="/logout">
                <input type="submit" value="Выйти" id="out">
            </form>
        </div>
        <div class="UserPhoto-container">
            <img src="/pictures/MonsterEnergy.jpeg">
        </div>
    </div>
    <div class="HeaderMenu">
        <ul>
            <li><a href="MainPage.jsp">Профиль</a></li>
            <li><a href="MainPage.jsp">Команда</a></li>
            <li><a href="MainPage.jsp">Гараж</a></li>
            <li><a href="MainPage.jsp">Заезды</a></li>
            <li><a href="RanksPage.jsp">Рейтинг</a></li>
            <li><a href="MainPage.jsp">Сообщения</a></li>
        </ul>
        <div class="searcher">
            <form>
                <input type="text" placeholder="Поиск по сайту" id="search">
                <input type="submit" value="Найти" id="find">
            </form>
        </div>
    </div>
    <!-- end if-->
    <div class="MainArea">
        <div class="LeftArea"></div>
        <div class="SponsorPhotoArea">
        <div class="inside_block_wrapper" style="height: 95%">
            <div class="infotab" style="height: 93.5%">
                <div class="pic-container">
                    <img src="/pictures/MonsterEnergy.jpeg">
                </div>
            </div>
        </div>
        </div>
        <div class="SponsorInfoArea">
            <div class="inside_block_wrapper" style="height: 95%">
                <div class="infotab" style="height: 93.5%">
                <center><h3>Monster Energy</h3></center>
                    Бюджет: 34234
                    <br> <br>
                    Спонсировано команд: 11
                    <br> <br>
                    Суммарно потрачено: 5837584
                </div>
            </div>
        </div>
        <div class="SponsoringArea">
            <div class="inside_block_wrapper">
                <center><h3>Спонсирование</h3></center>
                <div class="sptab">
                    <div class="sp-team-info">
                        <h3>RogoNemRacing</h3>
                        Суммарно потрачено:
                        <br>
                        <form>
                            <label for="moneySp">Спонсировать</label>
                            <input type="number" id="moneySp" >
                            <input type="submit" class="res-selector" value="Перевести">
                        </form>
                    </div>
                    <div class="sp-team-story">
                        <center>
                            <table class="infotable" border="1">
                                <tr>
                                    <th> Дата </th>
                                    <th> Потрачено </th>
                                </tr>
                                <tr>
                                    <td> Дата </td>
                                    <td> Потрачено </td>
                                </tr>
                                <tr>
                                    <td> Дата </td>
                                    <td> Потрачено </td>
                                </tr>
                                <tr>
                                    <td> Дата </td>
                                    <td> Потрачено </td>
                                </tr>
                                <tr>
                                    <td> Дата </td>
                                    <td> Потрачено </td>
                                </tr>
                            </table>
                        </center>
                    </div>
                </div>
            </div>
        </div>
        <div class="RightArea"></div>
        </div>
    </div>
</div>
</body>
</html>