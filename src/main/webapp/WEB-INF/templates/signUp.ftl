<!DOCTYPE html>
<html lang="en">
<head>
    <title>Регистрация</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/sign.css">
    <link href="https://fonts.googleapis.com/css?family=Prata" rel="stylesheet">
</head>
<body>
<div class="main-bg">
    <!-- Header -->
    <header>
        <div class="inner">
            <h1 class="logo"><a href="home.ftl"></a></h1>
            <div class="clear"></div>
        </div>
    </header>

    <section id="content">
        <div class="container_24">
            <div class="wrapper">
                <div class="grid_24 content-bg">
                    <div class="wrapper">
                        <div class="grid_16 suffix_1 prefix_1 alpha" style="padding-top: 12px">

                            <h1 class="h1_project" style="margin: 16px; color: #24ace3; opacity: 0.5 ;">
                                Регистрация
                            </h1>
                            <form method="post" class="form" action="/signUp">
                                <p><input name="firstName" placeholder="Имя" class="feedback_input"></p>
                                <p><input name="lastName" placeholder="Фамилия" class="feedback_input"></p>
                                <p><input name="patronymic" placeholder="Отчество" class="feedback_input"></p>
                                <p><input type="email" name="email" placeholder="Электронная почта"
                                          class="feedback_input"></p>
                                <p><input name="phoneNumber" placeholder="Мобильный телефон" class="feedback_input"></p>
                                <p><input type="password" name="password" placeholder="Пароль" class="feedback_input">
                                </p>
                                <#--                                <input type="submit" onclick="return validate(this.form)"  value="Зарегистрироваться" class="submit_feedback">-->
                                <input type="submit" value="Зарегистрироваться" class="submit_feedback">
                            </form>

                            <script>
                                function showError(container, errorMessage) {
                                    container.className = 'error';
                                    var msgElem = document.createElement('span');
                                    msgElem.className = "error-message";
                                    msgElem.innerHTML = errorMessage;
                                    container.appendChild(msgElem);
                                }

                                function resetError(container) {
                                    container.className = '';
                                    if (container.lastChild.className == "error-message") {
                                        container.removeChild(container.lastChild);
                                    }
                                }

                                function validate(form) {
                                    var elems = form.elements;

                                    resetError(elems.firstName.parentNode);
                                    if (!elems.firstName.value) {
                                        showError(elems.firstName.parentNode, ' Укажите Ваше имя.');
                                        return false;
                                    }

                                    resetError(elems.lastName.parentNode);
                                    if (!elems.lastName.value) {
                                        showError(elems.lastName.parentNode, ' Укажите Вашу фамилию.');
                                        return false;
                                    }

                                    // var v=elems.email.value;
                                    resetError(elems.email.parentNode);
                                    if (!elems.email.value) {
                                        showError(elems.email.parentNode, ' Укажите электронную почту.');
                                        return false;
                                    }
                                    // <%--else {--%>
                                    // <%--<%ServletContext context = config.getServletContext();%>--%>
                                    // <%--<% UsersService usersService = (UsersService) context.getAttribute("usersService"); %>--%>
                                    // <%--<% if (!usersService.findByEmail(%>v<%))%>--%>
                                    // <%--}--%>


                                    resetError(elems.password.parentNode);
                                    if (!elems.password.value) {
                                        showError(elems.password.parentNode, ' Укажите пароль.');
                                        return false;
                                    }

                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>