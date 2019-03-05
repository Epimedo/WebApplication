<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Ehor Halavin
  Date: 06.02.2019
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <fmt:message bundle="${loc}" key="local.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.singout" var="singOut"/>
    <fmt:message bundle="${loc}" key="local.accept" var="accept"/>
    <fmt:message bundle="${loc}" key="local.decline" var="decline"/>
    <fmt:message bundle="${loc}" key="local.paid" var="paid"/>
    <fmt:message bundle="${loc}" key="local.curPosition" var="curPosition"/>
    <fmt:message bundle="${loc}" key="local.nextPostion" var="nextPostion"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.checkTrips" var="checkTrips"/>
    <fmt:message bundle="${loc}" key="local.activate" var="ACtivate"/>
    <fmt:message bundle="${loc}" key="local.deactivate" var="Deactivate"/>
    <fmt:message bundle="${loc}" key="local.infoBanAccount" var="infoBanAc"/>


</head>

<body id="page-top">
<script>
    if (typeof window.history.pushState == 'function') {
        window.history.pushState({}, "Hide", "http:/Taxi/order");
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
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=Go_To_Main"><c:out value="${home}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=SIGN_OUT"><c:out
                            value="${singOut}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/order?command=en">
                        <c:out value="${en_button}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/order?command=ru">
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
        <h1>Управление вашими заказами</h1>
        <p class="lead"></p>
    </div>
</header>

<section id="order" class="bg-light"
         style="background: url('https://www.goalcast.com/wp-content/uploads/2017/11/road-trip-2.jpg') no-repeat center center fixed">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 mx-auto text-center">
                <c:if test="${sessionScope.driverStatus==null}">
                    <div class="container">
                        <form method="post" action="/Taxi/driver">
                            <input name="command" type="hidden" value="activateDriver">
                            <button class="btn btn-lg btn-success btn-block" type="submit">
                                <c:out value="${ACtivate}"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <c:if test="${sessionScope.driverStatus=='accountBan'}">
                    <div class="container">
                        <h3 class="text-light"><c:out value="${infoBanAc}"/></h3>
                    </div>
                    <div class="container">
                        <form method="post" action="/Taxi/driver">
                            <input name="command" type="hidden" value="activateDriver">
                            <button class="btn btn-lg btn-success btn-block" type="submit">
                                <c:out value="${ACtivate}"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <c:if test="${sessionScope.driverStatus=='carChekupEnd'}">
                    <div class="container">
                        <h3 class="text-light">Истек срок действия техосмотра вашей машины.</h3>
                    </div>
                    <div class="container">
                        <form method="post" action="/Taxi/driver">
                            <input name="command" type="hidden" value="activateDriver">
                            <button class="btn btn-lg btn-success btn-block" type="submit">
                                <c:out value="${ACtivate}"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <c:if test="${sessionScope.driverStatus=='activate'}">
                    <div class="container">
                        <form method="post" action="/Taxi/driver">
                            <input name="command" type="hidden" value="deactivateDRIVER">
                            <button class="btn btn-lg btn-success btn-block" type="submit">
                                <c:out value="${Deactivate}"/>
                            </button>
                        </form>
                    </div>
                    <div class="container" style="padding-top: 25px;padding-bottom: 25px;"></div>
                    <div class="container">
                        <form method="post" action="/Taxi/driver">
                            <input name="command" type="hidden" value="checkTrips">
                            <button class="btn btn-lg btn-success btn-block" type="submit">
                                <c:out value="${checkTrips}"/>
                            </button>
                        </form>
                    </div>
                </c:if>
            </div>
            <c:if test="${sessionScope.order!=null}">
                <div class="col-lg-4 mx-auto text-left">
                    <div class="container">
                        <table class="table table-bordered bg-white" style="border-radius: 25px; border: hidden;">
                            <thead>
                            <tr>
                                <th class="col"><c:out value="${curPosition}"/></th>
                                <th class="col"><c:out value="${nextPostion}"/></th>
                                <th class="col"><c:out value="${phone}"/></th>
                                <th class="col"></th>
                                <th class="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="col"><c:out value="${sessionScope.order.departurePoint}"/></td>
                                <td class="col"><c:out value="${sessionScope.order.arrivalPoint}"/></td>
                                <td class="col"><c:out value="${sessionScope.order.passenger.tel}"/></td>
                                <c:if test="${sessionScope.driverStatus!='onWay'}">
                                    <td class="col">
                                        <form method="post" action="/Taxi/driver">
                                            <input name="command" value="accept" type="hidden">
                                            <button class="btn btn-sm btn-success btn-block" type="submit">
                                                <c:out value="${accept}"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${sessionScope.driverStatus!='onWay'}">
                                    <td class="col">
                                        <form method="post" action="/Taxi/driver">
                                            <input name="command" value="decline" type="hidden">
                                            <button class="btn btn-sm btn-success btn-block" type="submit">
                                                <c:out value="${decline}"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${sessionScope.driverStatus=='onWay'}">
                                    <td class="col">
                                        <form method="post" action="/Taxi/driver">
                                            <input name="command" value="paid" type="hidden">
                                            <button class="btn btn-sm btn-success btn-block" type="submit">
                                                <c:out value="${paid}"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${sessionScope.order!=null}">
            <div class="container text-center text-light" style="padding-top: 25px;">
                <h3>Your trip</h3>
                <div class="container">
                    <p class="text-lg-center"><c:out value="${sessionScope.order.cost}"/></p>
                </div>
            </div>
        </c:if>
    </div>
</section>

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
</footer>

<script src="${pageContext.servletContext.contextPath}/js/jquery/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${pageContext.servletContext.contextPath}/js/jquery/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="${pageContext.servletContext.contextPath}/js/scrolling-nav.js"></script>

</body>

</html>
