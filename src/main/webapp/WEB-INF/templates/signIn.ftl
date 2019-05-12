<!DOCTYPE html>
<html lang="en">
<head>
    <title>Вход</title>
    <meta charset="utf-8">
    <#--    <link rel="stylesheet" href='../../css/sign.css'/>-->
    <link href="https://fonts.googleapis.com/css?family=Prata" rel="stylesheet">
    <style type="text/css">
        body {
            font-family: 'Prata', serif;
            background: url(/images/body-bg.gif);

        }

        .feedback_input {
            color: black;
            font-size: 14px;
            font-weight: 300;
            line-height: 22px;
            background-color: white;
            padding: 13px;
            margin-bottom: 10px;
            width: 100%;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            -box-sizing: border-box;
            border: 0px solid transparent;
            border-bottom: 1px solid black;
        }

        .form {
            width: 50%;
            margin: 50px auto;
        }

        .submit_feedback {
            float: left;
            width: 100%;
            border: 0px solid transparent;
            cursor: pointer;
            background-color: black;
            color: #24ace3;
            opacity: 0.5;
            font-size: 18px;
            padding: 22px 0 22px 0;
            transition: all 0.3s;
            margin: -4px 0 0 0;
            font-weight: 300;

        }

        .submit_feedback:hover {
            background-color: white;
            color: black;
            border: 1px solid black;
        }

        .h1_project {
            text-align: center;
            margin-bottom: 20px;
            color: white;
        }

        a {
            color: #000;
        }

        a:hover, a:focus {
            color: #000;
        }

        ul li {
            display: inline-block;
            list-style-type: none;
        }

        .error input {
            border: 1px solid red;
        }
    </style>
</head>
<body>
<div class="main-bg">
    <!-- Header -->
    <header>
        <div class="inner">
            <h1 class="logo"><a href="/"></a></h1>
            <div class="clear"></div>
        </div>
    </header>

    <section id="content">
        <div class="container_24">
            <div class="wrapper">
                <div class="grid_24 content-bg">
                    <div class="wrapper">
                        <div class="grid_16 suffix_1 prefix_1 alpha" style="padding-top: 12px">

                            <div class="row" style="text-align: center">
                                <ul>
                                    <li><h1 class="h1_project" style="margin-right: 10px"><a href="#"
                                                                                             style="color: #24ace3; opacity: 0.5 ;">Вход</a>
                                        </h1></li>
                                    <li><h1 class="h1_project"><a href="/signUp"
                                                                  style="color: #24ace3; opacity: 0.5 ;text-decoration: none;">Регистрация</a>
                                        </h1></li>
                                </ul>
                            </div>

                            <form method="post" class="form" action="/signIn">
                                <p><input name="email" placeholder="Электронная почта" class="feedback_input"></p>
                                <p><input type="password" name="password" placeholder="Пароль" class="feedback_input">
                                </p>
                                <input type="submit" value="Войти" class="submit_feedback" onclick="isEmail()"
                                       style="margin: 10px 0">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>