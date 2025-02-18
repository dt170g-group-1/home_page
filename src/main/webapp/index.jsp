<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Antons Skafferi</title>
    <link href="https://fonts.googleapis.com/css2?family=Italiana&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/styles.css">
</head>
<body>
<jsp:include page="header.jsp" />
<br/>
<div class="container">
    <div id="lunch">
        <jsp:include page="lunch-menu" /> <!-- Lunch menu is printed/added from LunchMenu.java and AddLunch.java -->
    </div>
    <div id="events">
        <jsp:include page="events-coming" /> <!-- Events are printed/added from Events.java and  AddEvent.java -->
    </div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>