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
<script language="javascript">
    path = "${pageContext.request.contextPath}";
</script>
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
                            <p>实验室设备管理系统</p>
                        </a>
                    </div>
                </div>
                <%-- <div class="collapse navbar-collapse" id="main-menu">
                     <ul class="nav navbar-nav navbar-right">
                         <li><span></span><a href="#top" class="home">APP</a></li>
                         <li><span></span><a href="#first-section" class="about">实验室</a></li>
                         <li><span></span><a href="#second-section" class="portfolio">事务公开</a></li>
                         <li><span></span><a href="#third-section" class="contact">登录</a></li>
                     </ul>
                 </div>--%>
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
                    <h2><strong>实验室设备管理APP</strong></h2>

                    <p>扫描二维码下载APP，实时查看设备信息。</p>
                </div>
                <img class="qrcode" src="${ctx}/assets/images/deviceManage.png">
                <div class="col-sm-12">
                   <%-- <button class="btn btn-primary" id="downloadBtn"></i>点击下载</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>


<%--<div id="first-section">
    <div class="heading">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    &lt;%&ndash;<h2 class="header-section-title">
                        <a href="/masses/createAppeal"><spring:message code="login.toolbar.createAppeal" /></a>
                        |
                        <a href="/masses/queryAppeal"><spring:message code="login.toolbar.queryAppeal" /></a>
                    </h2>&ndash;%&gt;
                        <h3>实验室新闻公示栏</h3>
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
                        <c:forEach items="${publicAppeals}" var="appeal" varStatus="status">

                                <div class="cd-timeline-block <c:if test="${status.count%2==1}"> right</c:if>">
                                    <div class="cd-timeline-img cd-picture">
                                        <i class="
                                        <c:if test="${appeal.status == '0' || appeal.status == '1'|| appeal.status == '2'}">icon-exclamation todo</c:if>
                                        <c:if test="${appeal.status == '3'}">icon-time doing</c:if>
                                        <c:if test="${appeal.status == '4' || appeal.status == '5'}">icon-ok done</c:if>"
                                                ></i>
                                    </div>

                                    <div class="cd-timeline-content service-box-content">
                                        <h4>${status.count}、${appeal.town}-
                                            <c:if test="${appeal.appealtype == '0'}">个人诉求</c:if>
                                            <c:if test="${appeal.appealtype == '1'}">集体诉求</c:if>
                                        -
                                            <c:if test="${appeal.affairtype == '0'}">发展生产</c:if>
                                            <c:if test="${appeal.affairtype == '1'}">基础设施</c:if>
                                            <c:if test="${appeal.affairtype == '2'}">矛盾纠纷</c:if>
                                            <c:if test="${appeal.affairtype == '3'}">社会治安</c:if>
                                            <c:if test="${appeal.affairtype == '4'}">生活救助</c:if>
                                            <c:if test="${appeal.affairtype == '5'}">征地拆迁</c:if>
                                            <c:if test="${appeal.affairtype == '6'}">政策咨询</c:if>
                                            <c:if test="${appeal.affairtype == '7'}">证照办理</c:if>
                                            <c:if test="${appeal.affairtype == '8'}">群众投诉</c:if>
                                            <c:if test="${appeal.affairtype == '9'}">其他</c:if>
                                        -
                                            <c:if test="${appeal.status == '0'}">未解决</c:if>
                                            <c:if test="${appeal.status == '1'}">已上报上级协调解决</c:if>
                                            <c:if test="${appeal.status == '2'}">延时解决</c:if>
                                            <c:if test="${appeal.status == '3'}">正在解决</c:if>
                                            <c:if test="${appeal.status == '4'}">已做好解释说明工作</c:if>
                                            <c:if test="${appeal.status == '5'}">已解决</c:if>

                                            <span>${appeal.createdate}</span></h4>

                                        <p title="${appeal.hardshipappeal}">${appeal.hardshipappeal}</p>
                                    </div>
                                </div>
                        </c:forEach>

                    </article>
                </section>
            </div>
        </div>
    </div>
</div>--%>
<%--<div id="second-section">
    <div class="heading">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h3>实验室事务公示栏</h3>
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

                        <c:forEach items="${publicDwgk}" var="smsSent" varStatus="status">

                            <div class="cd-timeline-block <c:if test="${status.count%2==1}"> right</c:if>">
                                <div class="cd-timeline-img cd-picture">
                                    <i class="icon-exclamation todo"></i>
                                </div>

                                <div class="cd-timeline-content service-box-content">

                                    <h4>${status.count}、${smsSent.parenttype}-${smsSent.childtype}<span>${smsSent.senddate}</span></h4>
                                    <p title="${smsSent.smscontent}">${smsSent.smscontent}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </article>
                </section>
            </div>
        </div>
    </div>
</div>--%>

<hr/>
<div id="third-section">
    <div class="container login">
        <div class="row">
            <h3>用户登录</h3>
        </div>
        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-8">
                <form class="form" id="login-form">
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
                                技术支持：XXX
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
<script type="text/javascript" src="${ctx}/assets/js/bootbox.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.similar.scroll.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/pages/login/login.js"></script>
<script type="text/javascript">
    //登录返回信息
    $(function () {
        <c:if test="${message!=null && message!=\"\"}">
        bootbox.dialog({
            message: "<span class='bigger-110'>${message}</span>",
            buttons: {
                "success": {
                    "label": "<i class='icon-ok'></i> 确定",
                    "className": "btn-sm btn-success",
                    "callback": function () {
                        //Example.show("great success");
                    }
                }
            }
        });
        </c:if>
    });
</script>
<script type="text/javascript">

    jQuery(function ($) {
        $(document).on('click', '.toolbar a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });
    //you don't need this, just used for changing background
    //    jQuery(function($) {
    //        $('#btn-login-dark').on('click', function(e) {
    //            $('body').attr('class', 'login-layout');
    //            $('#id-text2').attr('class', 'white');
    //            $('#id-company-text').attr('class', 'blue');
    //
    //            e.preventDefault();
    //        });
    //        $('#btn-login-light').on('click', function(e) {
    //            $('body').attr('class', 'login-layout light-login');
    //            $('#id-text2').attr('class', 'grey');
    //            $('#id-company-text').attr('class', 'blue');
    //
    //            e.preventDefault();
    //        });
    //        $('#btn-login-blur').on('click', function(e) {
    //            $('body').attr('class', 'login-layout blur-login');
    //            $('#id-text2').attr('class', 'white');
    //            $('#id-company-text').attr('class', 'light-blue');
    //
    //            e.preventDefault();
    //        });
    //
    //    });
</script>
</body>
</html>