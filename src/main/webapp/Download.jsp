<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Укажите путь к файлу, который нужно скачать
    String filePath = request.getParameter("path");

    File file = new File(filePath);

    if (file.exists()) {
        //Устанавливается длина контента в ответе. Показал браузеру сколько байт получить до полной загрузки файла.
        response.setContentLengthLong(file.length());

        // Устанавливаю заголовок для сообщения браузеру, Content-Disposition, что файл нужно скачать, а не открыть
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");

        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];

        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    } else {

    }
%>