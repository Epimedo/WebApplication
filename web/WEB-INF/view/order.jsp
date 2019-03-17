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
    <fmt:message bundle="${loc}" key="local.makeOrder" var="makeOrder"/>
    <fmt:message bundle="${loc}" key="local.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.contact" var="contact"/>
    <fmt:message bundle="${loc}" key="local.order" var="order"/>
    <fmt:message bundle="${loc}" key="local.singout" var="singOut"/>
    <fmt:message bundle="${loc}" key="local.curPosition" var="currentPosition"/>
    <fmt:message bundle="${loc}" key="local.nextPostion" var="nextPosition"/>
    <fmt:message bundle="${loc}" key="local.findDriver" var="findDriver"/>
    <fmt:message bundle="${loc}" key="local.reset" var="reset"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.carNumber" var="carNumber"/>
    <fmt:message bundle="${loc}" key="local.nearDrivers" var="nearDrviers"/>
    <fmt:message bundle="${loc}" key="local.tripPrice" var="tripPrice"/>
    <fmt:message bundle="${loc}" key="local.car" var="car"/>
    <fmt:message bundle="${loc}" key="local.reset" var="reset"/>
    <fmt:message bundle="${loc}" key="local.contacUs" var="contactUs"/>
    <fmt:message bundle="${loc}" key="local.information" var="information"/>
    <fmt:message bundle="${loc}" key="local.contactFirstInfo" var="contactFirst"/>
    <fmt:message bundle="${loc}" key="local.contactSecondInfo" var="conctactSecond"/>
    <fmt:message bundle="${loc}" key="local.location" var="location"/>
    <fmt:message bundle="${loc}" key="local.street" var="street"/>
    <fmt:message bundle="${loc}" key="local.declineOrderInfo" var="declineOrderInfo"/>
    <fmt:message bundle="${loc}" key="local.driverOnWay" var="driverOnWay"/>
    <fmt:message bundle="${loc}" key="local.refresh" var="refresh"/>
    <fmt:message bundle="${loc}" key="local.waiting" var="waiting"/>
    <fmt:message bundle="${loc}" key="local.infoBanAccount" var="infoBanAc"/>
    <fmt:message bundle="${loc}" key="local.getOrders" var="getOrders"/>
    <fmt:message bundle="${loc}" key="local.closeOrders" var="closeOrders"/>
    <fmt:message bundle="${loc}" key="local.date" var="date"/>
    <fmt:message bundle="${loc}" key="local.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="local.noAvailableDrivers" var="noAvailableDrivers"/>

</head>

<body id="page-top">
<c:if test="${sessionScope.focusTable=='orderTable'}">
    <script>
        window.onload = function () {
            window.location = "#orderTable";
        }
    </script>
</c:if>
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
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=Go_To_Main"><c:out
                            value="${home}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="#order"><c:out value="${order}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main"><c:out value="${contact}"/></a>
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
        <h1><c:out value="${makeOrder}"/></h1>
        <p class="lead"></p>
    </div>
</header>

<section id="order" class="bg-light"
         style="background: url('https://www.goalcast.com/wp-content/uploads/2017/11/road-trip-2.jpg') no-repeat center center fixed">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-4 mx-auto">
                <div class="container">
                    <h1 class="mt-5 text-light"><c:out value="${order}"/></h1>
                </div>
                <c:if test="${sessionScope.orderStatus=='noAvailableDrivers'}">
                    <div class="container">
                        <h2 class="text-light"><c:out value="${noAvailableDrivers}"/></h2>
                    </div>
                    <form method="post" action="/Taxi/order">
                        <div class="form-group">
                            <input id="currentPositionNo" name="currentPosition" type="text" class="form-control"
                                   placeholder="${currentPosition}"
                                   required autofocus>
                            <datalist id="forCurPosNo">
                                <option id="forCurPos1No"></option>
                            </datalist>
                        </div>
                        <div class="form-group">
                            <input id="nextPositionNo" name="nextPosition" type="text" class="form-control"
                                   placeholder="${nextPosition}"
                                   required autofocus>
                            <datalist id="forNextPosNo">
                                <option id="forNextPos1No"></option>
                            </datalist>
                        </div>
                        <input name="command" type="hidden" value="driverSearch">
                        <button class="btn btn-sm btn-success btn-block" type="submit"><c:out
                                value="${order}"/></button>
                        <script>
                            var input1 = document.getElementById('currentPositionNo');

                            input1.oninput = function () {
                                var data = {};
                                var command = 'AJAX_CUR_STREET';
                                data = {"command": command, "curStreet": $("#currentPositionNo").val()}
                                $.ajax
                                ({
                                    type: "POST",
                                    data: data,
                                    url: '/Taxi/order',
                                    success: function (serverData) {
                                        console.log(serverData.answer);
                                        var obj = document.getElementById('forCurPos1No');
                                        obj.value = serverData.answer;
                                    },
                                    error: function () {
                                        console.log(data);
                                    }
                                });
                            };
                            var input2 = document.getElementById('nextPositionNo');
                            input2.oninput = function () {
                                var data = {};
                                var command = 'AJAX_CUR_STREET';
                                data = {"command": command, "curStreet": $("#nextPositionNo").val()}
                                $.ajax
                                ({
                                    type: "POST",
                                    data: data,
                                    url: '/Taxi/order',
                                    success: function (serverData) {
                                        console.log(serverData.answer);
                                        var obj = document.getElementById('forNextPos1No');
                                        obj.value = serverData.answer;
                                    },
                                    error: function () {
                                        console.log(data);
                                    }
                                })
                            }
                        </script>
                    </form>
                </c:if>
                <c:if test="${sessionScope.orderStatus==null}">
                    <form method="post" action="/Taxi/order">
                        <div class="form-group">
                            <input id="currentPosition" name="currentPosition" type="text" class="form-control"
                                   placeholder="${currentPosition}" list="forCurPos" autocomplete="off"
                                   required autofocus>
                            <datalist id="forCurPos">
                                <option id="forCurPos1"></option>
                            </datalist>
                        </div>
                        <div class="form-group">
                            <input id="nextPosition" name="nextPosition" type="text" class="form-control"
                                   placeholder="${nextPosition}" list="forNextPos" autocomplete="off"
                                   required autofocus>
                            <datalist id="forNextPos">
                                <option id="forNextPos1"></option>
                            </datalist>
                        </div>
                        <script>
                            var input1 = document.getElementById('currentPosition');

                            input1.oninput = function () {
                                var data = {};
                                var command = 'AJAX_CUR_STREET';
                                data = {"command": command, "curStreet": $("#currentPosition").val()}
                                $.ajax
                                ({
                                    type: "POST",
                                    data: data,
                                    url: '/Taxi/order',
                                    success: function (serverData) {
                                        console.log(serverData.answer);
                                        var obj = document.getElementById('forCurPos1');
                                        obj.value = serverData.answer;
                                    },
                                    error: function () {
                                        console.log(data);
                                    }
                                });
                            };
                            var input2 = document.getElementById('nextPosition');
                            input2.oninput = function () {
                                var data = {};
                                var command = 'AJAX_CUR_STREET';
                                data = {"command": command, "curStreet": $("#nextPosition").val()}
                                $.ajax
                                ({
                                    type: "POST",
                                    data: data,
                                    url: '/Taxi/order',
                                    success: function (serverData) {
                                        console.log(serverData.answer);
                                        var obj = document.getElementById('forNextPos1');
                                        obj.value = serverData.answer;
                                    },
                                    error: function () {
                                        console.log(data);
                                    }
                                })
                            }
                        </script>
                        <input name="command" type="hidden" value="driverSearch">
                        <button class="btn btn-sm btn-success btn-block" type="submit"><c:out
                                value="${order}"/></button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='accountBan'}">
                    <div class="container">
                        <h2 class="text-light"><c:out value="${infoBanAc}"/></h2>
                    </div>
                    <form method="post" action="/Taxi/order">
                        <div class="form-group">
                            <input name="currentPosition" type="text" class="form-control"
                                   placeholder="${currentPosition}"
                                   required autofocus>
                        </div>
                        <div class="form-group">
                            <input name="nextPosition" type="text" class="form-control" placeholder="${nextPosition}"
                                   required autofocus>
                        </div>
                        <input name="command" type="hidden" value="driverSearch">
                        <button class="btn btn-sm btn-success btn-block" type="submit"><c:out
                                value="${order}"/></button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='wait'}">
                    <form method="post" action="/Taxi/order">
                        <input type="hidden" name="command" value="reset">
                        <button class="btn btn-sm btn-danger btn-block" type="Submit"><c:out value="${reset}"/></button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='wait'}">
                    <div class="container">
                        <h2 class="text-light"><c:out value="${waiting}"/></h2>
                    </div>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='wait'}">
                    <form method="post" action="/Taxi/order">
                        <input type="hidden" name="command" value="refresh">
                        <button class="btn btn-sm btn-danger btn-block" type="Submit"><c:out
                                value="${refresh}"/></button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='accept'}">
                    <div class="container">
                        <h2 class="text-light"><c:out value="${driverOnWay}"/></h2>
                    </div>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='decline'}">
                    <div class="container">
                        <h2 class="text-light"><c:out value="${declineOrderInfo}"/></h2>
                    </div>
                </c:if>
                <c:if test="${sessionScope.orderStatus=='decline'}">
                    <form method="post" action="/Taxi/order">
                        <div class="form-group">
                            <input name="currentPosition" type="text" class="form-control"
                                   placeholder="${currentPosition}" value="${sessionScope.order.departurePoint}"
                                   required autofocus>
                        </div>
                        <div class="form-group">
                            <input name="nextPosition" type="text" class="form-control"
                                   placeholder="${nextPosition}" value="${sessionScope.order.arrivalPoint}"
                                   required autofocus>
                        </div>
                        <input name="command" type="hidden" value="newDriverSearch">
                        <button class="btn btn-sm btn-success btn-block" type="submit"><c:out
                                value="${order}"/></button>
                    </form>
                </c:if>
            </div>
            <c:if test="${sessionScope.order.driver!=null}">
                <div class="col-lg-4 mx-auto">
                    <div class="container">
                        <h2 class="mt-5 text-light"><c:out value="${nearDrviers}"/></h2>
                    </div>
                    <div class="container">
                        <table class="table table-bordered bg-white" style="border-radius: 25px; border: hidden;">
                            <thead>
                            <tr>
                                <th class="col-xs-8"><c:out value="${name}"/></th>
                                <th class="col-xs-3"><c:out value="${carNumber}"/></th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td class="col-xs-8">
                                    <c:out value="${sessionScope.order.driver.name}"/>
                                    <c:out value="${sessionScope.order.driver.surname}"/>
                                </td>
                                <td class="col-xs-3 ">
                                    <c:out value="${sessionScope.order.driver.carNumber}"/>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <div class="col-lg-4 mx-auto">
                <c:if test="${sessionScope.orders==null}">
                    <div class="container text-center">
                        <div class="container">
                            <form method="post" action="/Taxi/order">
                                <input type="hidden" name="command" value="GET_USER_ORDERS">
                                <button class="btn btn-sm btn-success btn-block" type="submit">
                                    <c:out value="${getOrders}"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.orders!=null}">
                    <div id="orderTable" class="container text-center" style="padding-bottom: 25px">
                        <div class="container">
                            <form method="post" action="/Taxi/order">
                                <input type="hidden" name="command" value="CLOSE_USER_ORDERS">
                                <button class="btn btn-sm btn-success btn-block" type="submit">
                                    <c:out value="${closeOrders}"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.orders!=null}">
                    <div class="container">
                        <table class="table table-bordered bg-white" style="border-radius: 25px; border: hidden;">
                            <thead>
                            <tr>
                                <th class="col-xs-8"><c:out value="${name}"/></th>
                                <th class="col-xs-3"><c:out value="${carNumber}"/></th>
                                <th class="col-xs-3"><c:out value="${date}"/></th>
                                <th class="col-xs-3"><c:out value="${cost}"/></th>
                            </tr>
                            </thead>
                            <c:forEach var="order" items="${sessionScope.orders}">
                                <tbody>
                                <tr>
                                    <td class="col-xs-8">
                                        <c:out value="${order.driver.name}"/>
                                        <c:out value="${order.driver.surname}"/>
                                    </td>
                                    <td class="col-xs-3 ">
                                        <c:out value="${order.driver.carNumber}"/>
                                    </td>
                                    <td class="col-xs-3">
                                        <c:out value="${order.date}"/>
                                    </td>
                                    <td class="col-xs-3">
                                        <c:out value="${order.cost}"/> $
                                    </td>
                                </tr>

                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="container">
                        <div class="row text-center">
                            <c:forEach var="block" items="${sessionScope.orderBlocks}">
                                <div class="col-form-label">
                                    <form action="/Taxi/order" method="post">
                                        <input type="hidden" name="blockNumber" value="${block}">
                                        <input type="hidden" name="command" value="NEXT_BY_VALUE_ORDERS">
                                        <button class="btn btn-sm" type="submit">
                                            <c:out value="${block}"/>
                                        </button>
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <c:if test="${sessionScope.order!=null}">
            <div class="container text-center text-light" style="padding-top: 25px;">
                <h3><c:out value="${tripPrice}"/></h3>
                <div class="container">
                    <p class="text-lg-center"><c:out value="${sessionScope.order.cost}"/></p>
                </div>
            </div>
        </c:if>
    </div>
</section>

<section id="contact">

    <div class="container">
        <div class="row">
            <div class="col-md-8 mb-5">
                <h2><c:out value="${contactUs}"/></h2>
                <hr>
                <p><c:out value="${contactFirst}"/></p>
                <p><c:out value="${conctactSecond}"/></p>
            </div>
            <div class="col-md-4 mb-5">
                <h2><c:out value="${information}"/></h2>
                <hr>
                <address>
                    <br><c:out value="${location}"/>
                    <br><c:out value="${street}"/>
                    <br>
                </address>
                <address>
                    <abbr title="Phone">P:</abbr>
                    +375 (29) 45-78-900
                    <br>
                    <abbr title="Email">E:</abbr>
                    <a href="mailto:#">taxi@gmail.com</a>
                </address>
            </div>
        </div>
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
