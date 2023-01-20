<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionList" %>
<%@ page import="ru.javawebinar.basejava.model.SectionOrganization" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Name:</h1>
        <dl>
            <input type="text" name="fullName" size="50" value="${resume.fullName}">
        </dl>
        <h2>Contacts:</h2>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30"
                           value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <h3>Sections:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.Section"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type='text' name='${type}' size=75 value=<%=section%>>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name='${type}' cols=75 rows=5><%=section%></textarea>
                </c:when>
                <c:when test="${type=='QUALIFICATION' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=75 rows=5>
                        <%=String.join("\n", ((SectionList) section).getContent())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org"
                               items="<%=((SectionOrganization) section).getOrganizations()%>"
                               varStatus="counter">
                        <dl>
                            <dt>Organization name:</dt>
                            <dd><input type="text" name='${type}' size=100
                                       value="${org.homePage.name}"></dd>
                        </dl>
                        <dl>
                            <dt>Organization url:</dt>
                            <dd><input type="text" name='${type}url' size=100
                                       value="${org.homePage.url}"></dd>
                        </dl>
                        <br>
                        <div style="margin-left: 30px">
                            <c:forEach var="pos" items="${org.positions}">
                                <jsp:useBean id="pos"
                                             type="ru.javawebinar.basejava.model.Organization.Position"/>
                                <dl>
                                    <dt>Start date:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}startDate"
                                               size=10
                                               value="<%=DateUtil.format(pos.getStartDate())%>"
                                               placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>End date:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}endDate"
                                               size=10
                                               value="<%=DateUtil.format(pos.getEndDate())%>"
                                               placeholder="MM/yyyy">
                                </dl>
                                <dl>
                                    <dt>Position:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}title"
                                               size=75
                                               value="${pos.title}">
                                </dl>
                                <dl>
                                    <dt>Description:</dt>
                                    <dd>
                                        <textarea name="${type}${counter.index}description" rows=5
                                                  cols=75>${pos.description}</textarea>
                                    </dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>