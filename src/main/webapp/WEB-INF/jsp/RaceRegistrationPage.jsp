<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/RaceRegistrationStyle.css">
    <link rel="stylesheet" href="../styles/HeaderStyle.css">
    <title>Регистрация на гонку</title>
</head>
<body>
<script type="text/javascript" src="../scripts/RanksPageScript.js"> </script>
<div class="grid-container">
    <jsp:include page="Header.jsp"/>
    <div class="MainArea">
        <div class="InfoArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <center><h3>Ближайшая гонка</h3></center>
                    Гран-При России 2019
                    <br>
                    Участников: 20
                    <br>
                    Команд: 10
                    <br>
                    Начало: бла-бла-бла
                </div>
                <div class="infotab">
                    <center><h3>Принять участие</h3></center>
                    Команда: RogoNemRacing
                    <br> <br>
                    <form>
                        <label for="firstPilotreg">Первый пилот:</label>
                        <select class="res-selector" id="firstPilotreg">
                            <option>hello there</option>
                        </select>
                        <label for="firstCarreg">Болид:</label>
                        <select class="res-selector" id="firstCarreg">
                            <option>hello there</option>
                        </select>
                        <br>
                        <label for="secPilotreg">Второй пилот:</label>
                        <select class="res-selector" id="secPilotreg">
                            <option>hello there</option>
                        </select>
                        <label for="secCarreg">Болид:</label>
                        <select class="res-selector" id="secCarreg">
                            <option>hello there</option>
                        </select>
                        <br> <br>
                        <center><input type="submit" class="res-selector" value="Зарегистрироваться"></center>
                    </form>
                </div>
                <div class="infotab" style="text-align: center" id="toArch">
                    Узнать результаты прошлых гонок:
                    <input type="button" value="В архив" class="res-selector" onclick="location.href='/race-res'">
                </div>
            </div>
        </div>
        <div class="RegistrationTableArea">
            <div class="inside_block_wrapper">
                <div class="infotab">
                    <center> <h3>Список участников</h3> </center>
                    <table class="infotable" border="1">
                        <tr>
                            <th>№</th>
                            <th>Гонщик</th>
                            <th>Команда</th>
                            <th>Болид</th>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                        <tr>
                            <td>№</td>
                            <td>Гонщик</td>
                            <td>Команда</td>
                            <td>Болид</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>