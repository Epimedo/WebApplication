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
    <fmt:message bundle="${loc}" key="local.about" var="about"/>
    <fmt:message bundle="${loc}" key="local.admin" var="admin"/>
    <fmt:message bundle="${loc}" key="local.driver" var="driver"/>
    <fmt:message bundle="${loc}" key="local.order" var="order"/>
    <fmt:message bundle="${loc}" key="local.contact" var="contact"/>
    <fmt:message bundle="${loc}" key="local.singout" var="signOut"/>
    <fmt:message bundle="${loc}" key="local.services" var="services"/>
    <fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="local.aboutInfo" var="aboutInfo"/>
    <fmt:message bundle="${loc}" key="local.aboutFirstRow" var="fristRow"/>
    <fmt:message bundle="${loc}" key="local.aboutSecondRow" var="secondRow"/>
    <fmt:message bundle="${loc}" key="local.aboutThirdRow" var="thirdRow"/>
    <fmt:message bundle="${loc}" key="local.aboutFourthRow" var="fourthRow"/>
    <fmt:message bundle="${loc}" key="local.serviceOffer" var="serviceOffer"/>
    <fmt:message bundle="${loc}" key="local.signin" var="signIn"/>
    <fmt:message bundle="${loc}" key="local.registerin" var="registerIn"/>
    <fmt:message bundle="${loc}" key="local.close" var="close"/>
    <fmt:message bundle="${loc}" key="local.selectAccount" var="selectAccount"/>
    <fmt:message bundle="${loc}" key="local.passenger" var="passenger"/>
    <fmt:message bundle="${loc}" key="local.incorrectEmail" var="incorrectEmail"/>
    <fmt:message bundle="${loc}" key="local.incorrectPassword" var="incorrectPassword"/>
    <fmt:message bundle="${loc}" key="local.enterEmail" var="enterEmail"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.createAc" var="createAc"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.emailError" var="emailError"/>
    <fmt:message bundle="${loc}" key="local.telError" var="telError"/>
    <fmt:message bundle="${loc}" key="local.create" var="create"/>
    <fmt:message bundle="${loc}" key="local.createPassword" var="createPass"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.firstIntrs" var="firstInstr"/>
    <fmt:message bundle="${loc}" key="local.secondInstr" var="secondInstr"/>
    <fmt:message bundle="${loc}" key="local.contacUs" var="contactUs"/>
    <fmt:message bundle="${loc}" key="local.information" var="information"/>
    <fmt:message bundle="${loc}" key="local.contactFirstInfo" var="contactFirst"/>
    <fmt:message bundle="${loc}" key="local.contactSecondInfo" var="conctactSecond"/>
    <fmt:message bundle="${loc}" key="local.location" var="location"/>
    <fmt:message bundle="${loc}" key="local.street" var="street"/>
    <fmt:message bundle="${loc}" key="local.existingCarNumber" var="existingCarNumber"/>
    <fmt:message bundle="${loc}" key="local.checkupDateEnd" var="checkupDateEnd"/>
    <fmt:message bundle="${loc}" key="local.carNumber" var="carNumber"/>
    <fmt:message bundle="${loc}" key="local.car" var="car"/>
</head>

<body id="page-top">
<script>
    if (typeof window.history.pushState == 'function') {
        window.history.pushState({}, "Hide", "http:/Taxi/main");
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
                    <a class="nav-link js-scroll-trigger" href="#about">
                        <c:out value="${about}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="#services">
                        <c:out value="${services}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="#contact">
                        <c:out value="${contact}"/>
                    </a>
                </li>
                <c:if test="${sessionScope.responseStatus=='signed'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/Taxi/order">
                            <c:out value="${order}"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.responseStatus=='signedDriver'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/Taxi/driver">
                            <c:out value="${driver}"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.responseStatus=='signedAdmin'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/Taxi/admin?command=INIT_ADMIN">
                            <c:out value="${admin}"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.responseStatus=='signed'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=SIGN_OUT">
                            <c:out value="${signOut}"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.responseStatus=='signedAdmin'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=SIGN_OUT">
                            <c:out value="${signOut}"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.responseStatus=='signedDriver'}">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=SIGN_OUT">
                            <c:out value="${signOut}"/>
                        </a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=en">
                        <c:out value="${en_button}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="/Taxi/main?command=ru">
                        <c:out value="${ru_button}"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<header class="bg-primary text-white"
        style="background: url('https://www.xmple.com/wallpaper/squares-checkered-yellow-black-1920x1080-c2-ffd700-000000-l-260-a-25-f-2.svg') no-repeat center center fixed">
    <div class="container text-center">
        <h1><c:out value="${welcome}"/></h1>

    </div>
</header>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2><c:out value="${about}"/></h2>
                <p class="lead"><c:out value="${aboutInfo}"/></p>
                <ul>
                    <li><c:out value="${fristRow}"/></li>
                    <li><c:out value="${secondRow}"/></li>
                    <li><c:out value="${thirdRow}"/></li>
                    <li><c:out value="${fourthRow}"/></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg text-right">
                <div class="container">
                    <button class="btn btn-success btn-sm" type="button" data-toggle="modal" data-target="#delete">
                        <c:out value="${signIn}"/>
                    </button>
                    <div id="delete" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title"><c:out value="${signIn}"/></h4>
                                    <button class="close" type="button" data-dismiss="modal">×</button>
                                </div>
                                <div class="modal-body">
                                    <form method="post" action="/Taxi/main">
                                        <div class="form-group">
                                            <label class="text-left"><c:out value="${selectAccount}"/></label>
                                            <select name="accountType" class="form-control">
                                                <option value="Admin"><c:out value="${admin}"/></option>
                                                <option value="Driver"><c:out value="${driver}"/></option>
                                                <option value="Passenger"><c:out value="${passenger}"/></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <c:if test="${sessionScope.responseStatus=='incorrectEmail'}">
                                                <label><c:out value="${incorrectEmail}"/></label>
                                            </c:if>
                                            <input name="email" type="email"
                                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" class="form-control"
                                                   placeholder="${enterEmail}"
                                                   required autofocus>
                                        </div>
                                        <div class="form-group">
                                            <c:if test="${sessionScope.responseStatus=='incorrectPassword'}">
                                                <label><c:out value="${incorrectPassword}"/></label>
                                            </c:if>
                                            <input name="password" type="password" class="form-control"
                                                   placeholder="${password}"
                                                   required autofocus>
                                        </div>
                                        <input type="hidden" name="command" value="SIGN_IN">
                                        <button class="btn btn-sm btn-success btn-block" type="submit">
                                            <c:out value="${signIn}"/>
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
            <div class="col-lg text-left">
                <div class="container">
                    <button class="btn btn-success btn-sm" type="button" data-toggle="modal" data-target="#register">
                        <c:out value="${registerIn}"/>
                    </button>
                    <div id="register" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title"><c:out value="${createAc}"/></h4>
                                    <button class="close" type="button" data-dismiss="modal">×</button>
                                </div>
                                <div class="modal-body">
                                    <form method="post" action="/Taxi/main">
                                        <div class="form-group">
                                            <input name="name" type="text" pattern=".{1,20}" class="form-control"
                                                   placeholder="${name}"
                                                   required autofocus>
                                        </div>
                                        <div class="form-group">
                                            <input name="surname" type="text" pattern=".{1,20}" class="form-control"
                                                   placeholder="${surname}"
                                                   required autofocus>
                                        </div>
                                        <div class="form-group">
                                            <c:if test="${sessionScope.responseStatus=='existingEmail'}">
                                                <label><c:out value="${emailError}"/></label>
                                            </c:if>
                                            <input name="email" type="email"
                                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" class="form-control"
                                                   placeholder="${enterEmail}" required
                                                   autofocus>
                                        </div>
                                        <div class="form-group">
                                            <c:if test="${sessionScope.responseStatus=='existingPhone'}">
                                                <label><c:out value="${telError}"/></label>
                                            </c:if>
                                            <input name="tel"  pattern="\+375\s\d{2}\s\d{3}\s\d{2}\s\d{2}"
                                                   class="form-control" placeholder="${phone}"
                                                   required
                                                   autofocus>
                                        </div>
                                        <div class="form-group">
                                            <input name="password" type="password" pattern=".{6,}" class="form-control"
                                                   placeholder="${createPass}"
                                                   required autofocus>
                                        </div>
                                        <input type="hidden" name="accountType" value="Passenger">
                                        <input type="hidden" name="command" value="REGISTER_IN">
                                        <button class="btn btn-sm btn-success btn-block" type="submit">
                                            <c:out value="${create}"/>
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
</section>

<section id="services" class="bg-light">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2><c:out value="${serviceOffer}"/></h2>
                <p class="lead"><c:out value="${firstInstr}"/></p>
                <p class="lead"><c:out value="${secondInstr}"/>

                </p>
            </div>
        </div>
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
</footer>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.servletContext.contextPath}/js/jquery/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="${pageContext.servletContext.contextPath}/js/jquery/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="${pageContext.servletContext.contextPath}/js/scrolling-nav.js"></script>

</body>

</html>
