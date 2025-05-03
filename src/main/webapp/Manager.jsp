<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String directory = request.getParameter("path").replace("\\","/");
    File file = new File(directory);
    String parentDirectoryPath = "/";


    parentDirectoryPath = file.getParent();  // Получаем путь к родительской директории
    String login =(String) request.getAttribute("login");
    if (parentDirectoryPath == null || parentDirectoryPath.length() < ("C:\\Users\\sajbu\\IdeaProjects\\lab3\\lab5\\".length() + login.length())) {
        parentDirectoryPath = "C:\\Users\\sajbu\\IdeaProjects\\lab3\\lab5\\"+request.getAttribute("login");
    }


%>
<html>
<head>
    <title>Менеджер файлов</title>
</head>
<body>
<h1>Текущая директория: "<%=(String) request.getAttribute("currentPath")%> "</h1>
<a href=<%="?path="+parentDirectoryPath.replace("\\","/")%>>Назад</a>
<table>
    <tr>
        <th>Папка</th>
        <th>Перейти</th>
        <th>Размер(байты)</th>
        <th>Последнее изменение</th>
    </tr>
    <%
        File[] itemList = (File[]) request.getAttribute("folders"); // Получаем список из объекта запроса
        for (File item : itemList) {
    %>
    <tr>
        <th><%= item.getName()%></th>
        <th><a href=<%="?path="+item.getAbsolutePath().replace("\\", "/").replace(" ","%20")%>/>Перейти</th>


        <th><%= item.length()%></th>
        <th><%= new Date(item.lastModified())%></th>
    </tr>
    <% } %>
    <tr>
        <th>Файл</th>
        <th>Ссылка на скачивание</th>
        <th>Размер(байты)</th>
        <th>Последнее изменение</th>
    </tr>
    <%
        File[] list = (File[]) request.getAttribute("files"); // Получаем список из объекта запроса
        for (File item : list) {
    %>
    <tr>
        <th><%= item.getName()%></th>

        <th><a href=<%="http://localhost:8080/lab3-1.0-SNAPSHOT3/Download?path="+ item.getAbsolutePath().replace("\\","/").replace(" ","%20")%>> Скачать </a> </th>
        <th><%= item.length()%></th>
        <th><%= new Date(item.lastModified())%></th>
    </tr>
    <% } %>
</table>
<p>
<form action="Manager" method="POST">
    <input type="submit" value="Выйти">
</form>
</p>
</body>
</html>