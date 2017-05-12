<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en">
<![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang="en">
<![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <title><spring:message code="login.page.title" /></title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/login-style.css">

    <!-- JavaScripts -->
    <script src="${ctx}/assets/js/jquery-1.10.2.min.js"></script>
    <script src="${ctx}/assets/js/modernizr.min.js"></script>
</head>
<body>

<header class="site-header" id="top">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <i class="fa-bars"></i>
                    </button>
                    <div class="logo-wrapper">
                        <a class="navbar-brand" href="#templatemo">
                            <p>沙县民情管理服务工作平台</p>
                        </a>
                    </div>
                </div>
                <div class="collapse navbar-collapse" id="main-menu">
                    <ul class="nav navbar-nav navbar-right">
                        <li><span></span><a href="#top" class="home">APP</a></li>
                        <li><span></span><a href="#first-section" class="about">民情诉求</a></li>
                        <li><span></span><a href="#second-section" class="portfolio">党务公开</a></li>
                        <li><span></span><a href="#third-section" class="contact">登录</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="big-banner">
    <div class="container">
        <div class="row app-show">
            <div class="col-md-6 col-xs-6">
                <div class="phone-example">
                    <img src="${ctx}/assets/images/slider_bgrnd.png">
                </div>
            </div>
            <div class="col-md-6 col-xs-6">
                <div class="col-sm-12 description">
                    <h2><strong>沙县民情管理服务APP</strong></h2>
                    <p>扫描二维码下载APP，快速掌握诉求信息，随时随地提出诉求。</p>
                </div>
                <img class="qrcode" src="${ctx}/assets/images/app_qrcode.png">
                <div class="col-sm-12">
                    <button class="btn btn-primary"></i>点击下载</button>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="first-section">
    <div class="heading">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="header-section-title">
                        <a href="/masses/createAppeal"><spring:message code="login.toolbar.createAppeal" /></a>
                        |
                        <a href="/masses/queryAppeal"><spring:message code="login.toolbar.queryAppeal" /></a>
                    </h2>
                    <h3>民情诉求公示栏</h3>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="first-section1">
    <div class="container">
        <div class="row">
            <div class="triangle"></div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <section id="people-demand" class="cd-container">
                    <article>
                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-picture">
                                <i class="icon-time doing"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>1、基础设施-公共事务<span>2015-03-25</span></h4>

                                <p title="际硋村到城关公路一共7公里，路面坑洼 ，不方便村民出行，希望得以改善。">际硋村到城关公路一共7公里，路面坑洼 ，不方便村民出行，希望得以改善。</p>
                            </div>
                        </div>

                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-movie">
                                <i class="icon-ok done"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>2、生活救助-个人事务</h4>
                                <p>老弱病残，希望得到经济救助。</p>
                            </div>
                        </div>

                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-icon">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>3、发展生产-公共事务</h4>
                                <p>加大招租宣传力度，同时计划将村部搬至信息大楼。</p>
                            </div>
                        </div>

                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-ok done"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>4、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>5、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>6、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>7、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>8、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                    </article>
                </section>
            </div>
        </div>
    </div>
</div>
<div id="second-section">
    <div class="heading">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h3>党务公开公示栏</h3>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="second-section1">
    <div class="container">
        <div class="row">
            <div class="triangle"></div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <section id="party-affairs" class="cd-container">
                    <article>
                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-picture">
                                <i class="icon-time doing"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>1、基础设施-公共事务<span>2015-03-25</span></h4>

                                <p title="际硋村到城关公路一共7公里，路面坑洼 ，不方便村民出行，希望得以改善。">际硋村到城关公路一共7公里，路面坑洼 ，不方便村民出行，希望得以改善。</p>
                            </div>
                        </div>

                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-movie">
                                <i class="icon-ok done"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>2、生活救助-个人事务</h4>
                                <p>老弱病残，希望得到经济救助。</p>
                            </div>
                        </div>

                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-icon">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>3、发展生产-公共事务</h4>
                                <p>加大招租宣传力度，同时计划将村部搬至信息大楼。</p>
                            </div>
                        </div>

                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-ok done"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>4、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                        <div class="cd-timeline-block">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>5、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                        <div class="cd-timeline-block right">
                            <div class="cd-timeline-img cd-location">
                                <i class="icon-exclamation todo"></i>
                            </div>

                            <div class="cd-timeline-content service-box-content">
                                <h4>6、生活救助-个人事务</h4>
                                <p>生活困难，希望村里帮忙申请低保户。</p>
                            </div>
                        </div>
                    </article>
                </section>
            </div>
        </div>
    </div>
</div>

<hr/>
<div id="third-section">
    <div class="container login">
        <div class="row">
            <h3><spring:message code="login.box.title" /></h3>
        </div>
        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-8">
                <form class="form" id="login-form" method="post" action="/login">
                    <div class="row">
                        <div class="form-group name col-md-4">
                            <label class="block clearfix" for="username">
                                <input type="text" name="username" id="username" placeholder="<spring:message code="login.placeholder.username" />" />
                            </label>
                        </div>
                        <div class="form-group password col-md-4">
                            <label class="block clearfix" for="username">
                                <input type="password" name="password" id="password" placeholder="<spring:message code="login.placeholder.password" />" />
                            </label>
                        </div>
                        <div class="submit">
                            <button type="submit">
                                <spring:message code="login.button.name" />
                            </button>
                        </div>
                </form>
            </div>
        </div>
    </div>
</div>


<footer>
    <div class="container">
        <div class="footer-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="col-md-4">
                        <div class="back-to-top">
                            <a href="#top">返回顶部</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="copyright-text">
                            <p>Copyright &copy; 2015 simastudio
                                技术支持：司码工作室
                            </p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="social-icons">
                            <ul>
                                <li><a href="http://weibo.com/simastudio/"><i class="icon-weibo"></i></a></li>
                                <li><a href="mailto:simastudio@163.com"><i class="icon-envelope"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>


<!-- Javascripts -->
<script type="text/javascript" src="${ctx}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.similar.scroll.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/pages/login/login.js"></script>
<script type="text/javascript">
    $(function () {
        $("#people-demand").Scroll({ direction: "y" }); //设置为纵向滚动
        $("#party-affairs").Scroll({ direction: "y" }); //设置为纵向滚动
    });
</script>
</body>
</html>