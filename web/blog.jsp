<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--volledige blog pagina--%>
<%--NAME WORD VERSTUURD IN REQUEST--%>
<div>
    <h3>Was het een interessante projectweek?</h3>
    <ul id="feedback1"></ul>
    <p><label for="name1">naam </label> <input type="text" id="name1"></p>
    <p><label for="feedback1text">feedback </label> <input type="text" id="feedback1text"></p>
    <p><label for="feedback1nr">punten </label>
        <select name="feedback1nr" id="feedback1nr">
            <c:forEach begin="1" end="10" var="nr">
                <option value="${nr}">${nr}</option>
            </c:forEach>
        </select>
    </p>
    <%--send(1) is die id die ge in javascript van blog ophaalt als id in methode parameter--%>
    <p><button onclick="send(1)">send</button></p>
</div>

<div>
    <h3>Wat ben je van plan om te doen vandaag?</h3>
    <ul id="feedback2"></ul>
    <p><label for="name2">naam </label> <input type="text" id="name2"></p>
    <p><label for="feedback2text">feedback</label> <input type="text" id="feedback2text"></p>
    <p><label for="feedback2nr">punten</label>
        <select name="feedback2nr" id="feedback2nr">
            <c:forEach begin="1" end="10" var="nr">
                <option value="${nr}">${nr}</option>
            </c:forEach>
        </select>
    </p>

    <p><button onclick="send(2)">send</button></p>
</div>
<div>
    <h3>Naar welke muziek ben je momenteel aan het luisteren?</h3>
    <ul id="feedback3"></ul>
    <p> <label for="name3">naam</label> <input type="text" id="name3"></p>
    <p><label for="feedback3text">feedback </label><input type="text" id="feedback3text"></p>
    <p><label for="feedback3nr">punten </label>
        <select name="feedback3nr" id="feedback3nr">
            <c:forEach begin="1" end="10" var="nr">
                <option value="${nr}">${nr}</option>
            </c:forEach>
        </select>
    </p>

    <p><button onclick="send(3)">send</button></p>
</div>
<div>
    <h3>Wat zijn de examenvragen voor het vak Web4</h3>
    <ul id="feedback4"></ul>
    <p><label for="name4">naam</label> <input type="text" id="name4"></p>
    <p><label for="feedback4text">feedback</label> <input type="text" id="feedback4text"></p>
    <p><label for="feedback4nr">punten</label>
        <select name="feedback4nr" id="feedback4nr">
            <c:forEach begin="1" end="10" var="nr">
                <option value="${nr}">${nr}</option>
            </c:forEach>
        </select>
    </p>

    <p><button onclick="send(4)">send</button></p>
</div>
