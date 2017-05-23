<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span> </a>
<div class="sidebar" id="sidebar">
<script type="text/javascript">
    try {
        ace.settings.check('sidebar', 'fixed')
    } catch (e) {
    }
</script>

<div class="sidebar-shortcuts" id="sidebar-shortcuts">
    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
        <shiro:hasPermission name="user:*">
        <button class="btn btn-success no-border" id="userBtn" title="用户管理"
                onclick="javascript:window.location.href='${ctx}/user/showUser'">
            <i class="icon-user"></i>
        </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="role:*">
        <button class="btn btn-info no-border" id="roleBtn" title="角色管理"
                onclick="javascript:window.location.href='${ctx}/role'">
            <i class="icon-link" ></i>
        </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="resource:*">
        <button class="btn btn-warning no-border" id="resourceBtn" title="菜单管理"
                onclick="javascript:window.location.href='${ctx}/resource'">
            <i class="icon-cogs"></i>
        </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="audit:*">
        <button class="btn btn-danger no-border" id="orgBtn" title="登录日志"
                onclick="javascript:window.location.href='${ctx}/audit'">
            <i class="icon-sitemap"></i>
        </button>
        </shiro:hasPermission>
    </div>

    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
        <shiro:hasPermission name="user:*">
        <span class="btn btn-success"></span>
        </shiro:hasPermission>
        <shiro:hasPermission name="role:*">
        <span class="btn btn-info"></span>
        </shiro:hasPermission>
        <shiro:hasPermission name="resource:*">
        <span class="btn btn-warning"></span>
        </shiro:hasPermission>
        <shiro:hasPermission name="audit:*">
        <span class="btn btn-danger"></span>
        </shiro:hasPermission>
    </div>
</div>

<!-- #sidebar-shortcuts -->
<div class="sidebar-collapse" id="sidebar-collapse">
    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
</div>
<ul class="nav nav-list">
    <%--个人用户-学生--%>
        <shiro:hasPermission name="student:*">
        <li <c:if test="${navMenu == 'studentInfo'}">class="active open"</c:if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-user"></i>
            <span class="menu-text">
                个人信息
            </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li <c:if test="${subMenu == 'studentInfoLook'}">class="active"</c:if>>
                    <a href="${ctx}/user/studentInfo"><%--控制器/方法名--%>
                        <i class="icon-double-angle-right"></i>
                        查看详情
                    </a>
                </li>
            </ul>
        </li>
    </shiro:hasPermission>
    <%--个人用户-老师--%>
        <shiro:hasPermission name="teacher:*">
        <li <c:if test="${navMenu == 'teacherInfo'}">class="active open"</c:if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-user"></i>
            <span class="menu-text">
                个人信息
            </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li <c:if test="${subMenu == 'teacherInfoLook'}">class="active"</c:if>>
                    <a href="${ctx}/user/teacherInfo">
                        <i class="icon-double-angle-right"></i>
                        查看详情
                    </a>
                </li>
            </ul>
        </li>
    </shiro:hasPermission>
        <%--设备管理，用户--%>
        <shiro:hasPermission name="userdevice:*">
        <li <c:if test="${navMenu == 'device'}">class="active open"</c:if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-comments"></i>
            <span class="menu-text">
                设备管理
            </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li <c:if test="${subMenu == 'deviceshow'}">class="active"</c:if>>
                    <a href="${ctx}/devicemg/show">
                        <i class="icon-double-angle-right"></i>
                        查看设备
                    </a>
                </li>
                <li <c:if test="${subMenu == 'deviceadd'}">class="active"</c:if>>
                    <a href="${ctx}/devicemg/add">
                        <i class="icon-double-angle-right"></i>
                        添加设备
                    </a>
                </li>
            </ul>
        </li>
    </shiro:hasPermission>
        <%-- 设备管理，管理员--%>
        <shiro:hasPermission name="alldevice:*">
            <li <c:if test="${navMenu == 'equipmentmanage'}">class="active open"</c:if>>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-th-large" style="color: #d15b47;"></i>
            <span class="menu-text" style="font-weight: bolder; color: #d15b47;">
                设备管理
            </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li <c:if test="${subMenu == 'equipmentadd'}">class="active"</c:if>>
                        <a href="${ctx}/equipment/add">
                            <i class="icon-double-angle-right"></i>
                            设备添加
                        </a>
                    </li>
                    <li <c:if test="${subMenu == 'equipmentlist'}">class="active"</c:if>>
                        <a href="${ctx}/equipment/show">
                            <i class="icon-double-angle-right"></i>
                            设备信息
                        </a>
                    </li>
                </ul>
            </li>
        </shiro:hasPermission>
        <%--故障申报_普通用户--%>
        <shiro:hasPermission name="fault:*">
        <li <c:if test="${navMenu == 'fault'}">class="active open"</c:if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-th-large"></i>
            <span class="menu-text">
                故障申报
            </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li <c:if test="${subMenu == 'approve'}">class="active"</c:if>>
                    <a href="${ctx}/fault/showapply">
                        <i class="icon-double-angle-right"></i>
                        查看审核进度
                    </a>
                </li>
            </ul>
        </li>
    </shiro:hasPermission>
        <%--故障申报_管理员--%>
        <shiro:hasPermission name="FaultHandling:*">
            <li <c:if test="${navMenu == 'appply'}">class="active open"</c:if>>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-th-large"></i>
            <span class="menu-text">
                故障处理
            </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li <c:if test="${subMenu == 'faultaapply'}">class="active"</c:if>>
                        <a href="${ctx}/fault/approve">
                            <i class="icon-double-angle-right"></i>
                            故障处理
                        </a>
                    </li>
                </ul>
            </li>
        </shiro:hasPermission>
        <%--升级处理_用户--%>
        <shiro:hasPermission name="upgrade:*">
        <li <c:if test="${navMenu == 'upgrade'}">class="active open"</c:if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-th-large"></i>
            <span class="menu-text">
                升级申报
            </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                    <%--  <li <c:if test="${subMenu == 'farmerCreate'}">class="active"</c:if>>
                          <a href="${ctx}/Upgrade/upgrade">
                              <i class="icon-double-angle-right"></i>
                              升级申请
                          </a>
                      </li>--%>
                        <li <c:if test="${subMenu == 'upgradeView'}">class="active"</c:if>>
                    <a href="${ctx}/Upgrade/showapply">
                        <i class="icon-double-angle-right"></i>
                        查看审核进度
                    </a>
                </li>
                    <%-- <li <c:if test="${subMenu == 'farmerView'}">class="active"</c:if>>
                         <a href="${ctx}/farmer/show">
                             <i class="icon-double-angle-right"></i>
                             升级日志
                         </a>
                     </li>--%>
            </ul>
        </li>
    </shiro:hasPermission>
        <%--升级处理_管理员--%>
        <shiro:hasPermission name="upgradehandling:*">
            <li <c:if test="${navMenu == 'update'}">class="active open"</c:if>>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-th-large"></i>
            <span class="menu-text">
                升级处理
            </span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li <c:if test="${subMenu == 'updatedeal'}">class="active"</c:if>>
                        <a href="${ctx}/Upgrade/approve">
                            <i class="icon-double-angle-right"></i>
                            升级处理
                        </a>
                    </li>

                </ul>
            </li>
        </shiro:hasPermission>
<%--信息统计--%>
        <shiro:hasPermission name="Statistics:*">
        <li <c:if test="${navMenu == 'statistics'}">class="active open"</c:if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-bar-chart" style="color: #d15b47;"></i>
            <span class="menu-text" style="font-weight: bolder; color: #d15b47;">
                <%--<spring:message code="global.navMenu.appeal" />--%>
                信息统计
            </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
                <li <c:if test="${subMenu == 'devicestatistics'}">class="active"</c:if>>
                  <a href="${ctx}/statistics/devicestatistics">
                    <i class="icon-double-angle-right"></i>
                        <%--<spring:message code="global.subMenu.mainProject" />--%>
                    设备统计
                  </a>
                </li>
                <li <c:if test="${subMenu == 'updatestatistics'}">class="active"</c:if>>
                    <a href="${ctx}/statistics/updatestatistics">
                        <i class="icon-double-angle-right"></i>
                            <%--<spring:message code="global.subMenu.appealStatistics" />--%>
                        升级概况
                    </a>
                </li>
                <li <c:if test="${subMenu == 'repairstatistics'}">class="active"</c:if>>
                    <a href="${ctx}/statistics/repairstatistics">
                        <i class="icon-double-angle-right"></i>
                        <%--<spring:message code="global.subMenu.appealStatistics" />--%>
                        维修状态
                    </a>
                </li>
                    <li <c:if test="${subMenu == 'yearandposition'}">class="active"</c:if>>
                        <a href="${ctx}/statistics/yearandposition">
                            <i class="icon-double-angle-right"></i>
                                <%--<spring:message code="global.subMenu.appealStatistics" />--%>
                            年份地点
                        </a>
                    </li>
            </ul>
        </li>
    </shiro:hasPermission>


    <%--用户管理--%>
    <shiro:hasPermission name="user:*">
    <li <c:if test="${navMenu == 'user'}">class="active open"</c:if>>
        <a href="#" class="dropdown-toggle">
            <i class="icon-user"></i>
            <span class="menu-text">
               用户管理
            </span>
            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li <c:if test="${subMenu == 'userCreate'}">class="active"</c:if>>
                <a href="${ctx}/user/create">
                    <i class="icon-double-angle-right"></i>
                    添加用户
                </a>
            </li>
            <li <c:if test="${subMenu == 'userView'}">class="active"</c:if>>
                <a href="${ctx}/user/showUser">
                    <i class="icon-double-angle-right"></i>
                    全部用户
                </a>
            </li>

        </ul>
    </li>
    </shiro:hasPermission>
    <shiro:hasPermission name="role:*,resource:*,organization:*,audit:*">
    <li <c:if test="${navMenu == 'system'}">class="active open"</c:if>>
        <a href="#" class="dropdown-toggle">
            <i class="icon-cogs"></i>
            <span class="menu-text">
                <spring:message code="global.navMenu.system" />
            </span>
            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <shiro:hasPermission name="role:*">
            <li <c:if test="${subMenu == 'role'}">class="active"</c:if>>
                <a href="${ctx}/role">
                    <i class="icon-double-angle-right"></i>
                    <spring:message code="global.subMenu.role" />
                </a>
            </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="resource:*">
            <li <c:if test="${subMenu == 'resource'}">class="active"</c:if>>
                <a href="${ctx}/resource">
                    <i class="icon-double-angle-right"></i>
                    <spring:message code="global.subMenu.resource" />
                </a>
            </li>
            </shiro:hasPermission>
           <%-- <shiro:hasPermission name="organization:*">
            <li <c:if test="${subMenu == 'organization'}">class="active"</c:if>>
                <a href="${ctx}/organization">
                    <i class="icon-double-angle-right"></i>
                    <spring:message code="global.subMenu.organization" />
                </a>
            </li>
            </shiro:hasPermission>--%>
            <shiro:hasPermission name="audit:*">
                <li <c:if test="${subMenu == 'audit'}">class="active"</c:if>>
                    <a href="${ctx}/audit">
                        <i class="icon-double-angle-right"></i>
                        <spring:message code="global.subMenu.audit" />
                    </a>
                </li>
            </shiro:hasPermission>
        </ul>
    </li>
    </shiro:hasPermission>
    <%--不需要权限控制--%>
    <li <c:if test="${navMenu == 'feedback'}">class="active"</c:if>>
        <a href="${ctx}/feedback">
            <i class="icon-question-sign green"></i>
            <span class="menu-text">
                <spring:message code="global.navMenu.feedback" />
            </span>
        </a>
    </li>
</ul>
<script type="text/javascript">
    try {
        ace.settings.check('sidebar', 'collapsed')
    } catch (e) {
    }
</script>
</div>
