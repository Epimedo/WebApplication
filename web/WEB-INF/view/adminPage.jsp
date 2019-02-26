<%--
  Created by IntelliJ IDEA.
  User: Ehor Halavin
  Date: 06.02.2019
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Taxi</title>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/scrolling-nav.css" rel="stylesheet">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.status" var="status"/>
    <fmt:message bundle="${loc}" key="local.bonus" var="bonus"/>
    <fmt:message bundle="${loc}" key="local.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="local.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="local.close" var="close"/>
    <fmt:message bundle="${loc}" key="local.next" var="next"/>
    <fmt:message bundle="${loc}" key="local.carNumber" var="carNumber"/>
    <fmt:message bundle="${loc}" key="local.editAc" var="editAc"/>
    <fmt:message bundle="${loc}" key="local.enterDis" var="enterDis"/>
    <fmt:message bundle="${loc}" key="local.enterBon" var="enterBon"/>
    <fmt:message bundle="${loc}" key="local.singout" var="singOut"/>
    <fmt:message bundle="${loc}" key="local.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.car" var="car"/>
    <fmt:message bundle="${loc}" key="local.registerDriver" var="registerIn"/>
    <fmt:message bundle="${loc}" key="local.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.emailError" var="emailError"/>
    <fmt:message bundle="${loc}" key="local.telError" var="telError"/>
    <fmt:message bundle="${loc}" key="local.existingCarNumber" var="existingCarNumber"/>
    <fmt:message bundle="${loc}" key="local.createPassword" var="createPass"/>
    <fmt:message bundle="${loc}" key="local.create" var="create"/>

</head>

<body id="page-top">
<script>
    if (typeof window.history.pushState == 'function') {
        window.history.pushState({}, "Hide", "http:/Taxi/admin");
    }
</script>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/">
                        <c:out value="${home}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=SIGN_OUT">
                        <c:out value="${singOut}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/admin?command=en">
                        <c:out value="${en_button}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/admin?command=ru">
                        <c:out value="${ru_button}"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<header class="bg-primary text-white"
        style="background: url('https://www.goalcast.com/wp-content/uploads/2017/11/road-trip-2.jpg') no-repeat center center fixed">
    <div class="container text-center">
        <h1></h1>
        <p class="lead"></p>
    </div>
</header>

<section id="order" class="bg-light"
         style="background: url('https://www.goalcast.com/wp-content/uploads/2017/11/road-trip-2.jpg') no-repeat center center fixed">
    <div class="container">
        <div class="row text-center">
            <div class="container">
                <table class="table table-bordered bg-white" style="border-radius: 25px; border: hidden;">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th><c:out value="${name}"/></th>
                        <th><c:out value="${email}"/></th>
                        <th><c:out value="${phone}"/></th>
                        <th><c:out value="${status}"/></th>
                        <th><c:out value="${bonus}"/></th>
                        <th><c:out value="${discount}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="passenger" items="${sessionScope.passengers}">
                        <tr>
                            <td>
                                <c:out value="${passenger.id}"/>
                            </td>
                            <td>
                                <c:out value="${passenger.name}"/>
                                <c:out value="${passenger.surname}"/>
                            </td>
                            <td>
                                <c:out value="${passenger.email}"/>
                            </td>
                            <td>
                                <c:out value="${passenger.tel}"/>
                            </td>
                            <td>
                                <c:out value="${passenger.status}"/>
                            </td>
                            <td>
                                <c:out value="${passenger.bonus}"/>
                            </td>
                            <td>
                                <c:out value="${passenger.discount}"/>%
                            </td>
                            <td>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="container">
                <div class="row">
                    <c:forEach var="block" items="${sessionScope.passengerBlocks}">
                        <div class="col-form-label">
                            <form action="/Taxi/admin" method="post">
                                <input type="hidden" name="blockNumber" value="${block}">
                                <input type="hidden" name="command" value="NEXT_BY_VALUE_PASSENGERS">
                                <button class="btn btn-sm">
                                    <c:out value="${block}"/>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                    <div class="col-sm">
                        <form method="post" action="/Taxi/admin">
                            <input type="hidden" name="command" value="NEXT_PASSENGERS">
                            <button class="btn btn-sm">
                                <c:out value="${next}"/>
                            </button>
                        </form>
                    </div>
                    <div class="col-sm">
                        <div class="container">
                            <button class="btn btn-success btn-sm" type="button" data-toggle="modal"
                                    data-target="#passengerEditor">
                                Edit
                            </button>
                            <div id="passengerEditor" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title"><c:out value="${editAc}"/></h4>
                                            <button class="close" type="button" data-dismiss="modal">×</button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="/Taxi/admin">
                                                <div class="form-group">
                                                    <input class="form-control" pattern="\d{1,10}"
                                                           name="id"
                                                           placeholder="№ id" required autofocus>
                                                </div>
                                                <div class="form-group">
                                                    <select name="status" class="form-control">
                                                        <option value="ban">Ban</option>
                                                        <option value="unban">Unban</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label><c:out value="${bonus}"/></label>
                                                    <input pattern="[1-9][0-9]{0,3}(\.[0-9]{0,2})?"
                                                           name="bonus" class="form-control"
                                                           placeholder="${enterBon}"
                                                           required autofocus>
                                                </div>
                                                <div class="form-group">
                                                    <label><c:out value="${passenger.id}"/></label>
                                                    <label><c:out value="${discount}"/></label>
                                                    <input name="discount" pattern="[1-9][0-9]{0,3}*(\.[0-9])?"
                                                           class="form-control"
                                                           placeholder="${enterDis}"
                                                           required autofocus>
                                                </div>
                                                <input type="hidden" name="command" value="EDIT_PASSENGER">
                                                <button class="btn btn-dark btn-block" type="submit">
                                                    <c:out value="${edit}"/>
                                                </button>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-default" type="button" data-dismiss="modal">
                                                <c:out value="${close}"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <hr/>
    </div>

    <div class="container">
        <div class="row text-center">
            <div class="container">
                <table class="table table-bordered bg-white" style="border-radius: 25px; border: hidden;">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th><c:out value="${name}"/></th>
                        <th><c:out value="${status}"/></th>
                        <th><c:out value="${car}"/></th>
                        <th><c:out value="${carNumber}"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="driver" items="${sessionScope.drivers}">
                        <tr>
                            <td>
                                <c:out value="${driver.id}"/>
                            </td>
                            <td>
                                <c:out value="${driver.name}"/>
                                <c:out value="${driver.surname}"/>
                            </td>
                            <td>
                                <c:out value="${driver.status}"/>
                            </td>
                            <td>
                                <c:out value="${driver.carName}"/>
                            </td>
                            <td>
                                <c:out value="${driver.carNumber}"/>
                            </td>
                            <td>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="container">
                <div class="row">
                    <c:forEach var="block" items="${sessionScope.driverBlocks}">
                        <div class="col-form-label">
                            <form action="/Taxi/admin" method="post">
                                <input type="hidden" name="blockNumber" value="${block}">
                                <input type="hidden" name="command" value="NEXT_BY_VALUE_DRIVERS">
                                <button class="btn btn-sm">
                                    <c:out value="${block}"/>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                    <div class="col-sm">
                        <form action="/Taxi/admin" method="post">
                            <input type="hidden" name="command" value="NEXT_DRIVERS">
                            <button class="btn btn-sm">
                                <c:out value="${next}"/>
                            </button>
                        </form>
                    </div>
                    <div class="col-sm">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm">
                                    <div class="container">
                                        <button class="btn btn-success btn-sm" type="button" data-toggle="modal"
                                                data-target="#register">
                                            <c:out value="${registerIn}"/>
                                        </button>
                                        <div id="register" class="modal fade">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title"><c:out value="${createAc}"/></h4>
                                                        <button class="close" type="button" data-dismiss="modal">×
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form method="post" action="/Taxi/admin">
                                                            <div class="form-group">
                                                                <input name="name" type="text" class="form-control"
                                                                       placeholder="${name}"
                                                                       required autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <input name="surname" type="text" class="form-control"
                                                                       placeholder="${surname}"
                                                                       required autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <c:if test="${sessionScope.responseStatus=='existingEmail'}">
                                                                    <label><c:out value="${emailError}"/></label>
                                                                </c:if>
                                                                <input name="email" type="email"
                                                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                                                                       class="form-control"
                                                                       placeholder="${email}" required
                                                                       autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <c:if test="${sessionScope.responseStatus=='existingPhone'}">
                                                                    <label><c:out value="${telError}"/></label>
                                                                </c:if>
                                                                <input name="tel"
                                                                       pattern="\+375\s\d{2}\s\d{3}\s\d{2}\s\d{2}"
                                                                       class="form-control"
                                                                       placeholder="${phone}"
                                                                       required
                                                                       autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <input name="checkupEnd" type="date"
                                                                       class="form-control"
                                                                       placeholder="${checkupDateEnd}"
                                                                       required
                                                                       autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <input name="car" type="text" class="form-control"
                                                                       placeholder="${car}"
                                                                       required
                                                                       autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <c:if test="${sessionScope.responseStatus=='existingCarNumber'}">
                                                                    <label><c:out value="${existingCarNumber}"/></label>
                                                                </c:if>
                                                                <input name="carNumber" type="text" class="form-control"
                                                                       placeholder="${carNumber}"
                                                                       required
                                                                       autofocus>
                                                            </div>
                                                            <div class="form-group">
                                                                <input name="password" type="password"
                                                                       class="form-control"
                                                                       placeholder="${createPass}"
                                                                       required autofocus>
                                                            </div>
                                                            <input type="hidden" name="accountType" value="Driver">
                                                            <input type="hidden" name="command" value="REGISTER_IN">
                                                            <button class="btn btn-sm btn-success btn-block"
                                                                    type="submit">
                                                                <c:out value="${create}"/>
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn btn-default" type="button"
                                                                data-dismiss="modal">
                                                            <c:out value="${close}"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm">
                        <div class="container">
                            <button class="btn btn-success btn-sm" type="button" data-toggle="modal"
                                    data-target="#driverEditor">
                                Edit
                            </button>
                            <div id="driverEditor" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title"><c:out value="${editAc}"/></h4>
                                            <button class="close" type="button" data-dismiss="modal">×</button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="/Taxi/admin">
                                                <div class="form-group">
                                                    <input pattern="\d{1,10}" class="form-control" name="id"
                                                           placeholder="№ id" required
                                                           autofocus>
                                                </div>
                                                <div class="form-group">
                                                    <select name="status" class="form-control">
                                                        <option value="ban">Ban</option>
                                                        <option value="unban">Unban</option>
                                                    </select>
                                                </div>
                                                <input type="hidden" name="command" value="EDIT_DRIVER">
                                                <button class="btn btn-dark btn-block" type="submit">
                                                    <c:out value="${edit}"/>
                                                </button>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-default" type="button" data-dismiss="modal">
                                                <c:out value="${close}"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
</footer>

<script src="${pageContext.servletContext.contextPath}/js/jquery/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>

<script src="${pageContext.servletContext.contextPath}/js/jquery/jquery-easing/jquery.easing.min.js"></script>

<script src="${pageContext.servletContext.contextPath}/js/scrolling-nav.js"></script>

</body>

</html>
